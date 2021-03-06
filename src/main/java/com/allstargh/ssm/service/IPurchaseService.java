package com.allstargh.ssm.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.allstargh.ssm.pojo.PaginationII;
import com.allstargh.ssm.pojo.PagingTextII;
import com.allstargh.ssm.pojo.Purchase;
import com.allstargh.ssm.service.ex.SelfServiceException;

/**
 * 采购进货业务接口
 * 
 * @author gzh
 *
 */
public interface IPurchaseService {
	/**
	 * 
	 * @param uid
	 * @return
	 * @throws SelfServiceException
	 */
	abstract Map<Integer, Integer> getNumsByClassify(Integer uid) throws SelfServiceException;

	/**
	 * 在已批准的采购货物中按分类展示
	 * 
	 * @param usrid
	 * @param classify
	 * @param isAgree
	 * @return
	 * @throws SelfServiceException
	 */
	abstract List<Purchase> exhibitsListByClassifyAndIsAgree(Integer usrid, Integer classify, Integer isAgree)
			throws SelfServiceException;

	/**
	 * 审核员决定是否批准采购
	 * 
	 * @param usrid
	 * @param pid
	 * @param decide
	 * @return
	 * @throws SelfServiceException
	 */
	abstract Integer decidedPurchaseIsAgree(Integer usrid, Integer pid, Integer decide) throws SelfServiceException;

	/**
	 * SearchPurchasesByCondition
	 * 
	 * @param condition
	 * @param parameter
	 * @param usrname
	 * @return
	 * @throws SelfServiceException
	 */
	abstract List<Purchase> searchPurchasesByCondition(String condition, String parameter, String usrname)
			throws SelfServiceException;

	/**
	 * 获取待处理的入库队列
	 * 
	 * @param hasTakeGoods
	 * @param usrid
	 * @param isAgree
	 * @return
	 * @throws SelfServiceException
	 */
	abstract List<Purchase> getPrepareEnterQueue(Integer hasTakeGoods, Integer usrid, Integer isAgree)
			throws SelfServiceException;

	/**
	 * 仓管员获取已取货和已获批的审单们
	 * 
	 * @param hasTakeGoods
	 * @param usrid
	 * @param isAgree
	 * @return
	 * @throws SelfServiceException
	 */
	abstract List<Purchase> getPurchaseListByTakedAndAgreed(Integer hasTakeGoods, Integer usrid, Integer isAgree)
			throws SelfServiceException;

	/**
	 * 读取记录
	 * 
	 * @param usrid 采购经理ID
	 * @return
	 * @throws IOException
	 */
	String[] readOutputSubstanceLog(Integer usrid) throws IOException, SelfServiceException;

	/**
	 * 读取记录,分页
	 * 
	 * @param usrid
	 * @param pageth
	 * @param line
	 * @return
	 * @throws IOException
	 * @throws SelfServiceException
	 */
	abstract PagingTextII readSubstanceLogPaging(Integer usrid, Integer pageth, Integer line)
			throws IOException, SelfServiceException;

	/**
	 * 删除多份采购申请单,据ID们
	 * 
	 * @param purchaseIds
	 * @return
	 * @throws SelfServiceException
	 */
	Integer deleteMultiplesPurchaseAppByIds(Integer[] purchaseIds) throws SelfServiceException;

	/**
	 * 删除一份采购申请单
	 * 
	 * @param purchaseId
	 * @return
	 * @throws SelfServiceException
	 */
	Integer deleteSinglePurchaseAppById(Integer purchaseId) throws SelfServiceException;

	/**
	 * 修改申购单资料
	 * 
	 * @param operator
	 * @param purchase
	 * @return
	 * @throws SelfServiceException
	 */
	Integer editOnePurchaseById(String operator, Purchase purchase) throws SelfServiceException;

	/**
	 * 据ID而寻一份申请单数据
	 * 
	 * @param purchaseId
	 * @param usrid      在线人员ID
	 * @return
	 * @throws SelfServiceException
	 */
	Purchase findPurchaseById(Integer purchaseId, Integer usrid) throws SelfServiceException;

	/**
	 * 根据采购经理暂时其历史办理单子
	 * 
	 * @param operator
	 * @return
	 * @throws SelfServiceException
	 */
	List<Purchase> exhibitsPurchaseByOperator(String operator) throws SelfServiceException;

	/**
	 * 检查是否为激活状态下的采购专员
	 * 
	 * @param usrid
	 * @return
	 */
	String checkOperatorCompetence(Integer usrid, ModelMap model);

	/**
	 * 采购经理增加一份进货采购申请单
	 * 
	 * @param purchase
	 * @param usrname  采购经理之名
	 * @return
	 * @throws SelfServiceException
	 */
	Integer addOnePurchaseApplicationForm(Purchase purchase, String usrname) throws SelfServiceException;

	/**
	 * 以列表形式展示全部的进货采购申请单
	 * 
	 * @return
	 */
	List<Purchase> exhibitsAll();

}