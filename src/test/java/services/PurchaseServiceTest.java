package services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.allstargh.ssm.controller.kits.PurchaseControllerUtil;
import com.allstargh.ssm.mapper.PurchaseMapper;
import com.allstargh.ssm.pojo.Purchase;
import com.allstargh.ssm.service.IPurchaseService;
import com.allstargh.ssm.service.ex.SelfServiceException;
import com.allstargh.ssm.service.util.PurchaseServiceUtil;

public class PurchaseServiceTest {
	private ApplicationContext applicationContext;
	private IPurchaseService ips;

	@Before
	public void before() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-dao.xml", "spring/spring-service.xml" });
	}

	@Test
	public void countNumTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		try {
			Map<Integer, Integer> map = ips.getNumsByClassify(58);

			for (Map.Entry<Integer, Integer> iterable_element : map.entrySet()) {
				System.err.println(iterable_element.getKey() + "," + iterable_element.getValue());
			}
		} catch (SelfServiceException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	@Test
	public void exhibitionTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		try {
			List<Purchase> list = ips.exhibitsListByClassifyAndIsAgree(55, 2, 1);

			for (Purchase purchase : list) {
				System.err.println(purchase.toString());
			}

		} catch (SelfServiceException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getLocalizedMessage());
		}
	}

	@Test
	public void sevenSelectTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		try {
			List<Purchase> list = ips.searchPurchasesByCondition("0", "之", "p666");

			for (Purchase purchase : list) {
				System.err.println(purchase.toString());
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());

		}

	}

	@Test
	public void selectEnterTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		try {
			List<Purchase> list = ips.getPrepareEnterQueue(1, 54, 1);

			for (Purchase purchase : list) {
				System.err.println(purchase);
			}
		} catch (SelfServiceException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void readOutputTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		try {
			String[] strArr = ips.readOutputSubstanceLog(54);

			for (int i = 0; i < strArr.length; i++) {
				System.out.println(strArr[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void editOneTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		Purchase p = new Purchase();

		p.setPurchaseId(5);
		p.setCommodity("road and trip");
		p.setSupplier("underwood");
		p.setQuantity(200);
		p.setAmountMoney(236.54f);
		p.setPaymentMethod(0);
		p.sethasTakeGoods(0);
		p.setPurchaseTime(new Date());

		try {
			Integer affect = ips.editOnePurchaseById("p6666", p);
			System.err.println(affect);

		} catch (SelfServiceException e) {
			System.err.println(e.getMessage());

		}
	}

	@Test
	public void addOneTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		Purchase p = new Purchase();

		p.setCommodity("暗影之道");
		p.setAmountMoney(1000.36f);
		p.setPaymentMethod(0);
		p.setSupplier("Deutschland");
		p.setQuantity(10);
		p.sethasTakeGoods(1);

		try {
			Integer effect = ips.addOnePurchaseApplicationForm(p, "admin33");
			System.out.println("effect-" + effect);
		} catch (SelfServiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void exhibitionAllTest() {
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		List<Purchase> list = ips.exhibitsAll();

		for (Purchase purchase : list) {
			System.out.println(purchase.toString());
		}
	}

}