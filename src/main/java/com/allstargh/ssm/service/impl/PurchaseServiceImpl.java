package com.allstargh.ssm.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.allstargh.ssm.controller.kits.ControllerUtils;
import com.allstargh.ssm.controller.kits.PurchaseControllerUtil;
import com.allstargh.ssm.mapper.AccountsMapper;
import com.allstargh.ssm.mapper.PurchaseMapper;
import com.allstargh.ssm.pojo.Accounts;
import com.allstargh.ssm.pojo.PagingTextII;
import com.allstargh.ssm.pojo.Purchase;
import com.allstargh.ssm.service.ICommonReplenishService;
import com.allstargh.ssm.service.IPurchaseService;
import com.allstargh.ssm.service.ex.SelfServiceException;
import com.allstargh.ssm.service.ex.ServiceExceptionEnum;
import com.allstargh.ssm.service.util.PurchaseServiceUtil;
import com.allstargh.ssm.util.SegmentReadTextII;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
	@Autowired
	private PurchaseMapper pm;

	@Autowired
	private AccountsMapper am;

	@Autowired
	private ICommonReplenishService ics;

	/**
	 * 服务层工具类
	 */
	PurchaseServiceUtil psu = PurchaseServiceUtil.getInstance();

	/**
	 * 控制层工具类
	 */
	PurchaseControllerUtil pcu = PurchaseControllerUtil.getInstance();

	@Override
	public Integer addOnePurchaseApplicationForm(Purchase purchase, String usrname) throws SelfServiceException {
		// 经办人ID是否为空
		if (usrname == null || "".equals(usrname.trim())) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		// 默认未获批:0
		purchase.setIsAgree(0);

		// 默认时间,now
		purchase.setPurchaseTime(new Date());

		// 经手人:usrname
		purchase.setOperator(usrname);

		// 默认未支付:0
		purchase.setIsPay(0);

		// execute
		int affect = pm.insertIntoNewRow(purchase);

		return affect;
	}

	@Override
	public List<Purchase> exhibitsAll() {
		return pm.selectWhole();
	}

	@Override
	public String checkOperatorCompetence(Integer usrid, ModelMap model) {
		Accounts acc = am.selectAccountByUsrid(usrid);
		Integer competence = acc.getCompetence();
		Integer status = acc.getActiveStatus();

		if (competence != 2) {
			model.addAttribute("info", "您没有相应权限,不是该部门人员,无权入此模块");
			return "Transfer";
		}

		if (status == 0) {
			model.addAttribute("information", "您的账号已被注销,请联系系统管理员处理");
			return "Transfer";
		}

		// 返回"上一级目录/页面名"
		return "PurchaseDir/PurchaseWorkable";
	}

	@Override
	public List<Purchase> exhibitsPurchaseByOperator(String operator) throws SelfServiceException {
		if (operator == null || "".equals(operator)) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		List<Purchase> list = pm.selectWholeByOperator(operator);

		return list;
	}

	@Override
	public Purchase findPurchaseById(Integer purchaseId, Integer usrid) throws SelfServiceException {
		if (purchaseId == null || "".equals(purchaseId)) {
			String description = ServiceExceptionEnum.COMMIT_HAS_NULL.getDescription();
			throw new SelfServiceException(description);
		}

		if (usrid == null || "".equals(usrid)) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		Accounts a = am.selectAccountByUsrid(usrid);
		Integer competence = a.getCompetence();
		if (competence != 2 && competence != 4 && competence != 1) {
			String description = ServiceExceptionEnum.COMPETENCE_DISLOCATION.getDescription();
			throw new SelfServiceException(description);
		}

		Purchase purchase = pm.selectByPrimaryKey(purchaseId);
		return purchase;

	}

	@Override
	public Integer editOnePurchaseById(String operator, Purchase purchase) throws SelfServiceException {
		// 经办人不在线
		if (operator == null || "".equals(operator)) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		// 查询在线者资料
		Accounts accounts = am.selectByUname(operator);

		// 在线者不是采购专员
		if (accounts.getCompetence() != 2) {
			String description = ServiceExceptionEnum.COMPETENCE_DISLOCATION.getDescription();
			throw new SelfServiceException(description);
		}

		// 查询原单
		Purchase p = pm.selectByPrimaryKey(purchase.getPurchaseId());

		// 该申购单已获批,无法再改
		if (p.getIsAgree() == 1) {
			String description = ServiceExceptionEnum.OVER_DEADLINE.getDescription();
			throw new SelfServiceException(description);
		}

		purchase.setPurchaseTime(new Date());

		Integer row = pm.updatePurchaseByPurchaseId(purchase);

		return row;
	}

	@Override
	public Integer deleteSinglePurchaseAppById(Integer purchaseId) throws SelfServiceException {
		Integer affect = pm.deleteByPrimaryKey(purchaseId);

		if (affect != 1) {
			String description = ServiceExceptionEnum.SYSTEM_BUSY.getDescription();
			throw new SelfServiceException(description);
		}

		return affect;
	}

	@Override
	public Integer deleteMultiplesPurchaseAppByIds(Integer[] purchaseIds) throws SelfServiceException {
		Integer affects = pm.deleteMultipleRowsByIds(purchaseIds);

		if (affects != purchaseIds.length) {
			String description = ServiceExceptionEnum.SYSTEM_BUSY.getDescription();
			throw new SelfServiceException(description);
		}

		return affects;
	}

	@Override
	public String[] readOutputSubstanceLog(Integer usrid) throws IOException, SelfServiceException {
		if (usrid == null) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		Accounts accounts = am.selectAccountByUsrid(usrid);
		System.out.println(accounts);

		if (accounts.getCompetence() != 2) {
			String description = ServiceExceptionEnum.COMPETENCE_DISLOCATION.getDescription();
			throw new SelfServiceException(description);
		}

		String uri = ControllerUtils.ENGINE_DAILY_PATH;
		uri += PurchaseControllerUtil.PURCHASE_FILE_NAME;

		Path path = Paths.get(uri);

		String s = new String();
		try {
			byte[] bytes = Files.readAllBytes(path);
			s = new String(bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] ss = s.split("\n|\r");

		// 如已超限则排空
		if (ss.length > 12 * 1024) {
			System.err.println(this.getClass().getSimpleName() + ",超限");
			psu.cleanSubstance(uri);
		}

		return ss;
	}

	@Override
	public List<Purchase> getPurchaseListByTakedAndAgreed(Integer hasTakeGoods, Integer usrid, Integer isAgree)
			throws SelfServiceException {
		Accounts accounts = am.selectAccountByUsrid(usrid);

		if (accounts == null) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		if (accounts.getCompetence() != 4) {
			String description = ServiceExceptionEnum.COMPETENCE_DISLOCATION.getDescription();
			throw new SelfServiceException(description);
		}

		List<Purchase> list = pm.selectByHasTakeAndAgree(hasTakeGoods, isAgree);
		return list;
	}

	@Override
	public List<Purchase> getPrepareEnterQueue(Integer hasTakeGoods, Integer usrid, Integer isAgree)
			throws SelfServiceException {
		Accounts accounts = am.selectAccountByUsrid(usrid);

		if (accounts == null) {
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		if (accounts.getCompetence() != 4) {
			String description = ServiceExceptionEnum.COMPETENCE_DISLOCATION.getDescription();
			throw new SelfServiceException(description);
		}

		List<Purchase> list = pm.selectEnterQueue(hasTakeGoods, isAgree);

		return list;
	}

	@Override
	public List<Purchase> searchPurchasesByCondition(String condition, String parameter, String usrname)
			throws SelfServiceException {
		// 查找账号
		Accounts account = am.selectByUname(usrname);

		boolean checkAccount = ics.checkForAccount(account, 2);

		List<Purchase> list = new ArrayList<Purchase>();

		Integer parseInt = null;

		/*
		 * 如果Condition==1或处于3~7,parameter便需要解析为integer
		 */
		switch (condition) {
		case 0 + "":
			list = pm.selectByVagueCommodityAndOperator(parameter, usrname);
			break;

		case 1 + "":
			parseInt = Integer.parseInt(parameter);
			Purchase purchase = pm.selectByPurchaseIdAndOperator(usrname, parseInt);
			list.add(purchase);
			break;

		case 2 + "":
			list = pm.selectByVagueSupplierAndOperator(parameter, usrname);
			break;

		case 3 + "":
			parseInt = Integer.parseInt(parameter);
			list = pm.selectByIsPayAndOperator(usrname, parseInt);
			break;

		case 4 + "":
			parseInt = Integer.parseInt(parameter);
			list = pm.selectByPaymentMethodAndOperator(usrname, parseInt);
			break;

		case 5 + "":
			parseInt = Integer.parseInt(parameter);
			list = pm.selectByHasTakeGoodsAndOperator(usrname, parseInt);
			break;

		case 6 + "":
			parseInt = Integer.parseInt(parameter);
			list = pm.selectByClassifyAndOperator(usrname, parseInt);
			break;

		case 7 + "":
			parseInt = Integer.parseInt(parameter);
			list = pm.selectByIsAgreeAndOperator(usrname, parseInt);
			break;

		}

		if (list != null && checkAccount == true) {
			return list;
		}

		return null;
	}

	@Override
	public Integer decidedPurchaseIsAgree(Integer usrid, Integer pid, Integer decide) throws SelfServiceException {
		Integer affects = null;

		Accounts account = ics.checkForAccount(usrid, 1);

		Integer[] pids = { pid };

		switch (decide) {
		case 0:
			affects = pm.updateMultipleRowByPids(pids, 2);
			break;

		case 1:
			affects = pm.updateMultipleRowByPids(pids, 1);
			break;
		}

		return affects;
	}

	@Override
	public List<Purchase> exhibitsListByClassifyAndIsAgree(Integer usrid, Integer classify, Integer isAgree)
			throws SelfServiceException {
		Accounts account = ics.checkForAccount(usrid, 4);

		List<Purchase> list = pm.selectByClassifyAndIsAgree(classify, isAgree);

		return list;
	}

	@Override
	public Map<Integer, Integer> getNumsByClassify(Integer uid) throws SelfServiceException {
		Integer[] competences = { 4, 2 };

		Accounts account = ics.checkForAccount(uid, competences);

		Map<Integer, Integer> map = pm.countPurchaseIdGroupByClassify();

		return map;
	}

	@Override
	public PagingTextII readSubstanceLogPaging(Integer usrid, Integer pageth, Integer line)
			throws IOException, SelfServiceException {
		Integer[] competences = { 2 };

		Accounts account = ics.checkForAccount(usrid, competences);

		StringBuffer b = new StringBuffer(ControllerUtils.ENGINE_DAILY_PATH);
		
		String filePath = b.append(PurchaseControllerUtil.PURCHASE_FILE_NAME).toString();

		ics.checkTextOutOfCapacity(filePath, 60 * 1024);

		SegmentReadTextII textII = new SegmentReadTextII(line, filePath);
		
		PagingTextII pagingTextII = textII.packaging(pageth);

		return pagingTextII;
	}

}