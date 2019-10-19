package com.allstargh.ssm.service.util;

import java.util.Date;
import java.util.HashMap;

import com.allstargh.ssm.pojo.TStock;
import com.allstargh.ssm.service.ex.SelfServiceException;
import com.allstargh.ssm.service.ex.ServiceExceptionEnum;

public class StockServiceUtil {
	private static StockServiceUtil ssu;

	private HashMap<String, String> map = new HashMap<String, String>();
	private HashMap<String, String> m1 = new HashMap<String, String>();

	private static final Object LOCK = new Object();

	private StockServiceUtil() {

	}

	/**
	 * 懒汉
	 * 
	 * @return
	 */
	public static StockServiceUtil getInstance() {
		if (ssu == null) {
			synchronized (LOCK) {// 同步锁,保持单线程
				if (ssu == null) {
					ssu = new StockServiceUtil();
				}
			}
		}
		return ssu;

	}

	/**
	 * 整理
	 * 
	 * @param m1
	 * @param stockOperator
	 * @param remark
	 * @return
	 * @throws SelfServiceException
	 */
	public TStock arrangement(HashMap<String, String> m1, String stockOperator, String remark)
			throws SelfServiceException {
		TStock t = new TStock();

		t.setPurchaseId(Integer.valueOf(m1.get("purchaseId")));

		t.setStockTypeArea((byte) 9);

		t.setStoreQuantity(Integer.valueOf(m1.get("quantity")));

		Float amountMoney = Float.parseFloat(m1.get("amountMoney"));
		int q = Integer.valueOf(m1.get("quantity")).intValue();
		float price = amountMoney / (float) q;

		t.setUnitPrice((long) price);

		t.setStoreCommodity(m1.get("commodity"));

		t.setEnterStockTime(new Date());

		t.setAgreeEnterStock(false);

		t.setRemark(remark);

		try {
			t.setStockOperator(stockOperator);
		} catch (SelfServiceException e) {
			e.printStackTrace();
			String description = ServiceExceptionEnum.OFFLINE_LOGIN.getDescription();
			throw new SelfServiceException(description);
		}

		System.out.println(t.toString());
		return t;

	}

	/**
	 * purchaseId=43&commodity=陶峰久和豆沙包&supplier=久和陶峰食物事务所&quantity=65&amountMoney=120.33&operator=user333&purchaseTime=Tue
	 * Oct 15 13:10:54 CST 2019&classify=1
	 * 
	 * @param string
	 * @return
	 */
	public HashMap<String, String> generateMap(String string) {
		String[] sp = string.split("&");

		m1 = stringPutsHashMap(sp[0]);
		m1 = stringPutsHashMap(sp[1]);
		m1 = stringPutsHashMap(sp[2]);
		m1 = stringPutsHashMap(sp[3]);
		m1 = stringPutsHashMap(sp[4]);
		m1 = stringPutsHashMap(sp[5]);
		m1 = stringPutsHashMap(sp[6]);
		m1 = stringPutsHashMap(sp[7]);

		System.out.println(m1);
		return m1;

	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public HashMap<String, String> stringPutsHashMap(String str) {
		String[] strs = {};
		strs = str.split("=");

		if (strs.length < 2) {
			map.put(strs[0], "impossible");
			return map;
		}

		map.put(strs[0], strs[1]);
		return map;
	}

}