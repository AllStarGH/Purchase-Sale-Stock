package services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.allstargh.ssm.pojo.AssociativeEntity;
import com.allstargh.ssm.pojo.PaginationII;
import com.allstargh.ssm.service.IOutStockService;
import com.allstargh.ssm.service.ex.SelfServiceException;

public class ApprovalServiceTest {
	private ApplicationContext applicationContext;
	private IOutStockService ioss;

	@Before
	public void initialize() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-dao.xml", "spring/spring-service.xml" });
	}

	@Test
	public void selectTest() {
		ioss = (IOutStockService) applicationContext.getBean("outStockServiceImpl");

		try {
			PaginationII<List<AssociativeEntity>> paginationII = ioss.exhibitionQueuePlus(54, 3, 1, 1, 4);

			System.err.println(paginationII);
		} catch (SelfServiceException e) {
			System.err.println(e.getMessage());
		}

	}

}
