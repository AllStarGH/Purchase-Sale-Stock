package com.allstargh.ssm.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allstargh.ssm.controller.kits.ControllerUtils;
import com.allstargh.ssm.controller.kits.StockControllerUtil;
import com.allstargh.ssm.mapper.AccountsMapper;
import com.allstargh.ssm.mapper.PurchaseMapper;
import com.allstargh.ssm.mapper.TStockDAO;
import com.allstargh.ssm.pojo.Accounts;
import com.allstargh.ssm.pojo.PaginationII;
import com.allstargh.ssm.pojo.PagingText;
import com.allstargh.ssm.pojo.Purchase;
import com.allstargh.ssm.pojo.TStock;
import com.allstargh.ssm.pojo.TStockExample;
import com.allstargh.ssm.pojo.TStockExample.Criteria;
import com.allstargh.ssm.service.ICommonReplenishService;
import com.allstargh.ssm.service.IStcokSevice;
import com.allstargh.ssm.service.ex.SelfServiceException;
import com.allstargh.ssm.service.ex.ServiceExceptionEnum;
import com.allstargh.ssm.service.util.PurchaseServiceUtil;
import com.allstargh.ssm.service.util.StockServiceUtil;
import com.allstargh.ssm.util.PaginationsSupply;
import com.allstargh.ssm.util.SegmentReadText;

@Service
public class StockServiceImpl implements IStcokSevice {
	@Autowired
	private AccountsMapper amp;

	@Autowired
	private PurchaseMapper pm;

	@Autowired
	private ICommonReplenishService ics;

	@Autowired
	private TStockDAO tsd;

	/**
	 * StockServiceUtil
	 */
	StockServiceUtil ins = StockServiceUtil.getInstance();

	/**
	 * PurchaseServiceUtil
	 */
	PurchaseServiceUtil psu = PurchaseServiceUtil.getInstance();

	@Override
	public Integer regEntry(Purchase purchase, String stockOperator) throws SelfServiceException {
		/*
		 * 装配仓储实体对象
		 */
		TStock stock = new TStock();

		stock.setPurchaseId(purchase.getPurchaseId());

		String classify = purchase.getClassify().toString();
		byte b = Byte.parseByte(classify);
		stock.setStockTypeArea(b);

		Integer quantity = purchase.getQuantity();
		stock.setStoreQuantity(quantity);

		Float amountMoney = purchase.getAmountMoney();

		int q = quantity.intValue();
		float price = amountMoney / (float) q;
		stock.setUnitPrice((long) price);

		stock.setStoreCommodity(purchase.getCommodity());

		try {
			stock.setStockOperator(stockOperator);
		} catch (SelfServiceException e) {
			e.printStackTrace();
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		stock.setEnterStockTime(new Date());

		stock.setAgreeEnterStock(true);

		int row = tsd.insert(stock);

		return row;
	}

	@Override
	public Integer regToExternal(String purchase, String stockOperator, String remark) throws SelfServiceException {

		HashMap<String, String> hashMap = ins.generateMap(purchase);
		TStock stock = ins.arrangement(hashMap, stockOperator, remark);
		System.err.println(stock);

		int row = tsd.insert(stock);

		return row;
	}

	@Override
	public List<TStock> findAll(Integer uid) throws SelfServiceException {
		Accounts acc = amp.selectAccountByUsrid(uid);

		List<TStock> list = tsd.selectAllRows();

		try {
			if (acc == null || "".equals(acc)) {
				System.err.println("是谁?");
				list = null;

			} else if (acc.getActiveStatus() == 0) {
				String description = ServiceExceptionEnum.CANCELED_ACCOUNT.getDescription();
				System.err.println(description);
				list = null;

			} else if (acc.getCompetence() != 4) {
				String description = ServiceExceptionEnum.COMPETENCE_DISLOCATION.getDescription();
				System.err.println(description);
				list = null;

			}

		} catch (SelfServiceException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public TStock findTStockByPurchaseId(Integer purchaseId, Integer usrid) throws SelfServiceException {
		Accounts accounts = amp.selectAccountByUsrid(usrid);
		ins.checkStockerState(accounts);

		TStock tStock = tsd.selectByPurchaseId(purchaseId);
		return tStock;
	}

	@Override
	public Integer modifiedStoreGood(Integer usrid, String tStock) throws SelfServiceException {
		Accounts accounts = amp.selectAccountByUsrid(usrid);
		ins.checkStockerState(accounts);

		HashMap<String, String> map = ins.generateMapImprove(tStock);
		TStock tStock2 = ins.arrangement(map);

		tStock2.setLastestModifier(accounts.getUsrname());
		tStock2.setLastestModifiedTime(new Date());

		Integer affect = tsd.updateStoreGoodByPurchase(tStock2);

		return affect;
	}

	@Override
	public List<TStock> foundByTypeArea(Integer usrid, Byte areaOrder) throws SelfServiceException {
		Accounts ac = amp.selectAccountByUsrid(usrid);

		boolean b = ics.checkForAccount(ac, 4);

		if (b) {
			List<TStock> list = tsd.selectByPurchaseStockTypeArea(areaOrder);
			return list;
		}

		return null;
	}

	@Override
	public String[] readDailyLog(Integer uid) throws SelfServiceException, IOException {
		Accounts account = ics.checkForAccount(uid, 4);

		StringBuilder builder = new StringBuilder(ControllerUtils.ENGINE_DAILY_PATH);
		String path = builder.append(StockControllerUtil.DAILY_FILE_NAME).toString();

		Path path1 = Paths.get(path);

		String string = new String();
		try {
			byte[] bytes = Files.readAllBytes(path1);
			string = new String(bytes);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] split = string.split("\n|\r");

		// 如已超限则排空
		if (split.length > 12 * 1024) {
			System.err.println(this.getClass().getSimpleName() + ",超限");
			psu.cleanSubstance(path);
		}

		return split;
	}

	@Override
	public List<TStock> foundByStockTypeArea(Integer uid, Integer type) throws SelfServiceException {
		TStockExample example = new TStockExample();
		Criteria criteria = example.createCriteria();

		Integer[] competences = { 3, 4 };

		Accounts account = ics.checkForAccount(uid, competences);

		byte bt = (byte) type.intValue();

		example.setDistinct(false);
		example.setOrderByClause("lastest_modified_time asc");

		criteria.andStockTypeAreaEqualTo(bt);

		List<TStock> list = tsd.selectByExample(example);

		return list;
	}

	@Override
	public Integer getStoreQuantityByID(Integer uid, Integer sid) throws SelfServiceException {
		Integer[] competences = { 3, 4 };

		Accounts account = ics.checkForAccount(uid, competences);

		long id = sid.longValue();
		TStock stock = tsd.selectByPrimaryKey(id);

		Integer quantity = stock.getStoreQuantity();

		return quantity;
	}

	@Override
	public PagingText readDailyLog(Integer uid, Integer pageNum) throws SelfServiceException, IOException {
		Accounts account = ics.checkForAccount(uid, 4);

		SegmentReadText seg = new SegmentReadText();

		StringBuilder builder = new StringBuilder(ControllerUtils.ENGINE_DAILY_PATH);

		String path = builder.append(StockControllerUtil.DAILY_FILE_NAME).toString();

		ics.checkTextOutOfCapacity(path, 12 * 1024);

		PagingText text = seg.packaging(path, pageNum);

		return text;
	}

	@Override
	public TStock getStoreById(Integer sid, Integer usrid) throws SelfServiceException {
		Integer[] competences = { 1, 4 };

		Accounts account = ics.checkForAccount(usrid, competences);

		Long id = (long) sid.intValue();

		TStock tStock = tsd.selectByPrimaryKey(id);

		return tStock;
	}

	@Override
	public Integer deleteByMultiID(Integer uid, Long[] ids) throws SelfServiceException {
		Accounts account = ics.checkForAccount(uid, 4);

		ArrayList<Long> list = new ArrayList<Long>();

		TStockExample example = new TStockExample();

		Criteria criteria = example.createCriteria();

		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}

		criteria.andIdIn(list);

		int rows = tsd.deleteByExample(example);

		return rows;
	}

	@Override
	public PaginationII<List<TStock>> findAllLimits(Integer pageth, Integer lines, Integer usrid)
			throws SelfServiceException {
		Accounts account = ics.checkForAccount(usrid, 4);

		PaginationII<List<TStock>> paginationII = new PaginationII<List<TStock>>();

		PaginationsSupply supply = new PaginationsSupply();

		List<TStock> allRows = tsd.selectAllRows();
		
		List<TStock> rowsPaginations = tsd.selectAllRowsPaginations(pageth * lines, lines);

		int allpages = supply.getAllpages(allRows.size(), lines);
		
		Boolean[] booleans = supply.judgePrevOrNext(pageth, allpages);

		paginationII.setCurrentPageth(pageth);
		paginationII.setData(rowsPaginations);
		paginationII.setHasNextPage(booleans[1]);
		paginationII.setHasPreviousPage(booleans[0]);
		paginationII.setRows(lines);
		paginationII.setTotalPages(allpages);

		return paginationII;
	}

}
