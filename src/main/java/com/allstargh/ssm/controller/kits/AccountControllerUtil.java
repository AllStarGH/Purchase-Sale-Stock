package com.allstargh.ssm.controller.kits;

import javax.servlet.http.HttpSession;

import com.allstargh.ssm.pojo.Accounts;

/**
 * 账号管理工具补充类
 * 
 * @author admin
 *
 */
public class AccountControllerUtil extends ControllerUtils {
	private static AccountControllerUtil instance;

	// 锁
	private static final Object LOCK = new Object();

	/**
	 * 懒汉式
	 */
	public static AccountControllerUtil getInstance() {
		if (instance == null) {
			// 决定是否需要锁定
			synchronized (LOCK) {
				if (instance == null) {
					instance = new AccountControllerUtil();
				}
			}
		}

		return instance;
	}

	/**
	 * 记录账户模块活动记录的TXT文件之完整路径
	 */
	public static final String ACCOUNT_FILE_URI = ENGINE_DAILY_PATH + "Account-Log.txt";

	/**
	 * 将注册记录写入TXT
	 * 
	 * @param acc
	 * @param session
	 * @param row
	 */
	public void inputRegRecordsToTxt(Accounts acc, Integer row, HttpSession session) {
		String string = "";
		string += p_tag_prefix;

		try {
			string += acc.getUsrname() + " 于 " + now_time + "注册成功,权限代号为" + acc.getCompetence() + ",地区部门为"
					+ acc.getRegionDepartment() + ",执行者:" + session.getAttribute("usrname").toString() + LINE_SEPARATOR;
		} catch (Exception e) {
			string += acc.getUsrname() + " 于 " + now_time + "试图注册账号,权限代号为" + acc.getCompetence() + ",地区部门为"
					+ acc.getRegionDepartment() + LINE_SEPARATOR;
		}

		if (row == 1) {
			textWriter(string, ACCOUNT_FILE_URI);
		}

	}

	/**
	 * 将所有登录者之名全部记下(无论是否进入)
	 * 
	 * @param usrname
	 */
	public void inputAllLoginRecords(String usrname) {
		String string = "";
		string += p_tag_prefix;

		string += usrname + "尝试登录本系统" + ",时间:" + now_time + LINE_SEPARATOR;

		if (usrname != null) {
			textWriter(string, ACCOUNT_FILE_URI);
		}

	}

	/**
	 * 将登录成功的记录写入TXT
	 * 
	 * @param acc
	 * @param usrname
	 * @param session
	 */
	public void inputSuccessLoginRecords(Accounts acc, String usrname, HttpSession session) {
		String string = "";
		string += p_tag_prefix;

		string += "地区部门:" + acc.getRegionDepartment() + ",权限:" + acc.getCompetence() + ",用户 " + usrname + " 登录成功"
				+ ",时间:" + now_time + LINE_SEPARATOR;

		if (session.getAttribute("usrname").toString() != null) {
			textWriter(string, ACCOUNT_FILE_URI);
		}

	}

	/**
	 * 多选注销记录
	 * 
	 * @param affects
	 * @param session
	 * @param usrids
	 */
	public void multipleCancelRecords(Integer affects, HttpSession session, Integer[] usrids) {
		String string = "";
		string += p_tag_prefix;

		String usridStr = geneateUsridStr(usrids);

		string += usrids.length + "位用户:ID为{ " + usridStr + "}提交注销请求,其中" + affects + "位用户于" + now_time + "完成注销,"
				+ "执行人为: " + session.getAttribute("usrname").toString() + LINE_SEPARATOR;

		textWriter(string, ACCOUNT_FILE_URI);

	}

	/**
	 * 多选激活记录
	 * 
	 * @param affects
	 * @param session
	 * @param usrids
	 */
	public void multipleActiveRecords(Integer affects, HttpSession session, Integer[] usrids) {
		String string = "";
		string += p_tag_prefix;

		String usridStr = geneateUsridStr(usrids);

		string += usrids.length + "位用户:ID为{ " + usridStr + "}提交激活请求,其中" + affects + "位用户于" + now_time + "完成激活,"
				+ "执行人为: " + session.getAttribute("usrname").toString() + LINE_SEPARATOR;

		textWriter(string, ACCOUNT_FILE_URI);
	}

	/**
	 * 多选重置密码记录
	 * 
	 * @param affects
	 * @param session
	 * @param usrids
	 */
	public void multipleResetRecords(Integer affects, HttpSession session, Integer[] usrids) {
		String string = "";
		string += p_tag_prefix;

		String usridStr = geneateUsridStr(usrids);

		string += usrids.length + "位用户:ID为{ " + usridStr + "}提交重置密码请求,其中" + affects + "位用户于" + now_time + "完成密码重置,"
				+ "执行人为: " + session.getAttribute("usrname").toString() + LINE_SEPARATOR;

		textWriter(string, ACCOUNT_FILE_URI);
	}

	/**
	 * 修改账号资料记录
	 * 
	 * @param usrid
	 * @param session
	 * @param affects
	 * @param usrname
	 * @param phone
	 * @param competence
	 * @param regionDepartment
	 */
	public void executModifiyRecords(Integer usrid, HttpSession session, Integer affects, String usrname, String phone,
			Integer competence, Integer regionDepartment) {
		String string = "";
		string += p_tag_prefix;

		string += "ID为" + usrid + "的账号于" + now_time + "修改了资料,其新用户名为" + usrname + ",新电话为" + phone + ",新权限码为" + competence
				+ ",新地区部门为" + regionDepartment + ",执行人:" + session.getAttribute("usrname").toString() + LINE_SEPARATOR;

		if (affects == 1) {
			textWriter(string, ACCOUNT_FILE_URI);
		}
	}

	/**
	 * 删除账户记录
	 * 
	 * @param usrid
	 * @param code
	 * @param session
	 */
	public void earseAnAccountRecords(Integer usrid, Integer code, HttpSession session) {
		String string = "";
		string += p_tag_prefix;

		string += "ID为 " + usrid + "的账户于 " + now_time + "被删除,执行者为: " + session.getAttribute("usrname").toString()
				+ LINE_SEPARATOR;

		if (code == 1) {
			textWriter(string, ACCOUNT_FILE_URI);
		}
	}

	/**
	 * 修改密码记录
	 * 
	 * @param uid
	 * @param row
	 */
	public void revisePasswordHandlerRecord(Integer uid, Integer row) {
		String string = "";
		string += p_tag_prefix;

		string += "ID为 " + uid + " 的账号于" + now_time + "修改了密码" + LINE_SEPARATOR;

		if (row == 1) {
			textWriter(string, ACCOUNT_FILE_URI);
		}

	}

	/**
	 * 修改基本资料记录
	 * 
	 * @param uid
	 * @param uname
	 * @param phone
	 * @param affect
	 */
	public void reviseBaseProfileHandlerRecord(Integer uid, String uname, String phone, Integer affect) {
		String string = "";
		string += p_tag_prefix;

		string += "ID为 " + uid + " 的账户于" + now_time + "修改了基本资料,新用户名为:" + uname + ",新电话为:" + phone + LINE_SEPARATOR;

		if (affect == 1) {
			textWriter(string, ACCOUNT_FILE_URI);
		}

	}

	/**
	 * 返回1到多个ID的字符串
	 * 
	 * @param usrids
	 * @return
	 */
	public String geneateUsridStr(Integer[] usrids) {
		String useridStr = "";

		for (int i = 0; i < usrids.length; i++) {
			useridStr += Integer.valueOf(usrids[i]) + ",";
		}

		return useridStr;
	}

}
