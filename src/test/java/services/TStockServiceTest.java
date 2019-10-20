package services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.allstargh.ssm.pojo.Purchase;
import com.allstargh.ssm.pojo.TStock;
import com.allstargh.ssm.service.IAccountsService;
import com.allstargh.ssm.service.IPurchaseService;
import com.allstargh.ssm.service.IStcokSevice;
import com.allstargh.ssm.service.ex.SelfServiceException;

public class TStockServiceTest {
	private ApplicationContext applicationContext;
	private IStcokSevice iss;
	private IPurchaseService ips;
	private IAccountsService ias;

	@Before
	public void before() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-dao.xml", "spring/spring-service.xml" });
	}

	@Test
	public void insertOneTest() {
		iss = (IStcokSevice) applicationContext.getBean("stockServiceImpl");
		ips = (IPurchaseService) applicationContext.getBean("purchaseServiceImpl");

		Purchase p = ips.findPurchaseById(6, 52);
		System.err.println(p);

		TStock stock = new TStock();
		stock.setRemark("666*666");

		try {
			Integer row = iss.regEntry(p, "yier123");
			System.err.println("row-" + row);
		} catch (SelfServiceException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void selectWholeTest() {
		iss = (IStcokSevice) applicationContext.getBean("stockServiceImpl");
		ias = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		List<TStock> list = iss.findAll(57);

		try {
			for (TStock tStock : list) {
				System.err.println(tStock);
			}
		} catch (SelfServiceException e) {
			e.printStackTrace();
		}

	}

}