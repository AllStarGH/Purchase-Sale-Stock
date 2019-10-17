package com.allstargh.ssm.service;

import com.allstargh.ssm.pojo.Purchase;
import com.allstargh.ssm.service.ex.SelfServiceException;

/**
 * 仓储
 * 
 * @author admin
 *
 */
public interface IStcokSevice {
	/**
	 * 登记入库
	 * 
	 * @param purchase
	 * @param stockOperator
	 * @return
	 * @throws SelfServiceException
	 */
	Integer regEntry(Purchase purchase, String stockOperator) throws SelfServiceException;
}
