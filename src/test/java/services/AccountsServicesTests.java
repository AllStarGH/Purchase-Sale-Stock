package services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.allstargh.ssm.pojo.Accounts;
import com.allstargh.ssm.pojo.PagingTextII;
import com.allstargh.ssm.service.IAccountsService;
import com.allstargh.ssm.service.ex.SelfServiceException;

public class AccountsServicesTests {
	private ApplicationContext applicationContext;
	private IAccountsService accountsService;

	@Before
	public void initialize() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-dao.xml", "spring/spring-service.xml" });
	}

	@Test
	public void testLog() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		try {
			PagingTextII log = accountsService.readSubstanceLog(51, 7, 8);

			System.err.println(log);
		} catch (SelfServiceException s) {
			System.out.println(s.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		try {
			HashMap<Integer, String> hashMap = accountsService.obtainIDAndNames(51);

			for (Map.Entry<Integer, String> e : hashMap.entrySet()) {
				System.err.println(e.getKey() + " : " + e.getValue());
			}

		} catch (SelfServiceException s) {
			System.out.println(s.getMessage());
		}
	}

	@Test
	public void testReg() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");
		Accounts accounts = new Accounts();
		accounts.setUsrname("");
		accounts.setPhone("1889940007");
		accounts.setRegionDepartment(96);
		accounts.setCompetence(3);

		try {
			Integer i = accountsService.registerRole(accounts);
			System.out.println("i  :" + i);
		} catch (SelfServiceException s) {
			System.out.println(s.getMessage());
		}
	}

	@Test
	public void testDeleteAccount() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");
		try {
			Integer integer = accountsService.earseAnAccount(5);
			System.out.println("row:" + integer);
		} catch (SelfServiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testLoginAccount() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		HttpSession session = null;
		try {
			Accounts login = accountsService.login("mana", "666", session);
			System.out.println("ok," + login.hashCode());
		} catch (SelfServiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testAlterAccount() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		try {
			Integer affect = accountsService.alterAccountProfile("", "56788-sp", 5, 100, 20);
			System.out.println("affect: " + affect);
		} catch (SelfServiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testResetKeywordAccount() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");
		Integer[] ids = { 7, 11, 12 };
		Integer affects = accountsService.multipleResetPwd(ids);
		System.out.println("\\+" + affects);
	}

	@Test
	public void testSearchByRD() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		List<Accounts> list = accountsService.gainByRegionDepartment("鸦岭");
		for (Accounts accounts : list) {
			System.out.println(accounts.toString());
		}
	}

	@Test
	public void testSearchByCompetence() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		List<Accounts> list = accountsService.gainByCompetence("仓库主管");
		for (Accounts accounts : list) {
			System.out.println(accounts.toString());
		}
	}

	@Test
	public void testReadSubstance() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		try {
			List<String> list = accountsService.readSubstanceFromLog(null);
			System.out.println(list.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SelfServiceException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testUpdateLockWord() {
		accountsService = (IAccountsService) applicationContext.getBean("accountsServiceImpl");

		try {
			Integer effect = accountsService.revisePassword("3333", "3210", 33);
			System.out.println("effect:" + effect);
		} catch (SelfServiceException e) {
			System.out.println(e.getMessage());
		}
	}

}
