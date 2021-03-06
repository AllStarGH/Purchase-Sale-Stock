package com.allstargh.ssm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allstargh.ssm.controller.kits.ControllerUtils;
import com.allstargh.ssm.controller.kits.SaleControllerUtil;
import com.allstargh.ssm.json.ResponseResult;
import com.allstargh.ssm.pojo.Pagination;
import com.allstargh.ssm.pojo.PagingTextII;
import com.allstargh.ssm.pojo.TSale;
import com.allstargh.ssm.pojo.TStock;
import com.allstargh.ssm.service.ICommonReplenishService;
import com.allstargh.ssm.service.ISaleService;
import com.allstargh.ssm.service.IStcokSevice;
import com.allstargh.ssm.service.ex.SelfServiceException;

/**
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/SaleController")
public class SaleController extends ControllerUtils {
	@Autowired
	private ICommonReplenishService icrs;

	@Autowired
	private ISaleService iss;

	@Autowired
	private IStcokSevice iStcokSevice;

	/**
	 * 懒汉式
	 */
	SaleControllerUtil scu = SaleControllerUtil.getInstance();

	/**
	 * /stocker-manager/SaleController/viewLogHandler
	 * 
	 * @param session
	 * @param index
	 * @return
	 * @throws SelfServiceException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "viewLogHandler", method = RequestMethod.GET)
	public ResponseResult<PagingTextII> viewLogHandler(HttpSession session,
			@RequestParam(value = "pageIndex", defaultValue = "0") Integer index)
			throws SelfServiceException, IOException {
		System.err.println(this.getClass().getName() + ",index===");
		System.err.println(index);

		Integer uid = getUsridFromSession(session);

		PagingTextII log = iss.viewLog(uid, index, 8);

		return new ResponseResult<PagingTextII>(SUCCESS, log);
	}

	/**
	 * /stocker-manager/SaleController/submitCensorshipHandler
	 * 
	 * @param session
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submitCensorshipHandler", method = RequestMethod.GET)
	public ResponseResult<Integer> submitCensorshipHandler(HttpSession session, @RequestParam("sid") Integer sid) {
		System.err.println(this.getClass() + ",sid==" + sid);

		Integer uid = getUsridFromSession(session);
		String uname = getUsrnameFromSession(session);

		Integer affect = iss.submitCensorship(uid, sid);

		scu.submitCensorshipHandlerRecord(uname, affect, sid);

		return new ResponseResult<Integer>(SUCCESS, affect);
	}

	/**
	 * /stocker-manager/SaleController/revisionHandler
	 * 
	 * @param session
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "revisionHandler", method = RequestMethod.POST)
	public ResponseResult<Integer> revisionHandler(HttpSession session, TSale data) {
		Integer uid = getUsridFromSession(session);
		String uname = getUsrnameFromSession(session);

		System.err.println(data.toString());

		Integer effect = iss.revision(uid, data);

		// 写入文件
		scu.revisionHandlerRecord(uname, effect);

		return new ResponseResult<Integer>(SUCCESS, effect);
	}

	/**
	 * /stocker-manager/SaleController/searchSingleHandler?id=1
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "searchSingleHandler", method = RequestMethod.GET)
	public ResponseResult<TSale> searchSingleHandler(HttpSession session, @RequestParam("id") Integer id) {
		System.err.println("ID==" + id);

		Integer uid = getUsridFromSession(session);

		TSale sale = iss.searchSingle(uid, id);

		return new ResponseResult<TSale>(SUCCESS, sale);
	}

	/**
	 * /stocker-manager/SaleController/multiSearchSingleHandler?id=1
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "multiSearchSingleHandler", method = RequestMethod.GET)
	public ResponseResult<TSale> multiSearchSingleHandler(HttpSession session, @RequestParam("id") Integer id) {
		System.err.println(this.getClass().getName() + ",ID==");
		System.err.println(id);

		Integer uid = getUsridFromSession(session);

		TSale sale = iss.multiSearchSingle(uid, id);

		return new ResponseResult<TSale>(SUCCESS, sale);
	}

	/**
	 * /stocker-manager/SaleController/pagingDisplayHandler?pageth=1
	 * 
	 * @param session
	 * @param pageth
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "pagingDisplayHandler", method = RequestMethod.GET)
	public ResponseResult<Pagination<TSale>> pagingDisplayHandler(HttpSession session,
			@RequestParam(value = "pageth", defaultValue = "0") Integer pageth) {
		System.err.println("pageth==" + pageth);

		Integer uid = getUsridFromSession(session);

		Pagination<TSale> pagination = iss.pagingDisplay(pageth, 3, uid);

		return new ResponseResult<Pagination<TSale>>(SUCCESS, pagination);

	}

	/**
	 * /stocker-manager/SaleController/addHandler
	 * 
	 * @param session
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addHandler", method = RequestMethod.POST)
	public ResponseResult<Integer> addHandler(HttpSession session, TSale data) {
		System.err.println("data:" + data.toString());

		Integer uid = getUsridFromSession(session);

		Integer effect = iss.add(uid, data);

		String username = getUsrnameFromSession(session);
		scu.addHandlerRecord(username, effect);

		return new ResponseResult<Integer>(SUCCESS, effect);
	}

	/**
	 * /stocker-manager/SaleController/foundByStockTypeAreaHandler
	 * 
	 * @param session
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "foundByStockTypeAreaHandler", method = RequestMethod.GET)
	public ResponseResult<List<TStock>> foundByStockTypeAreaHandler(HttpSession session,
			@RequestParam("type") Integer type) {
		Integer uid = getUsridFromSession(session);

		System.err.println("type:" + type);

		List<TStock> list = iStcokSevice.foundByStockTypeArea(uid, type);

		return new ResponseResult<List<TStock>>(SUCCESS, list);

	}

}
