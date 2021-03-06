package com.allstargh.ssm.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 账号数据表
 */
public class Accounts {
	private Integer usrid;
	private String usrname;

	/**
	 * 区域部门
	 */
	private Integer regionDepartment;

	/**
	 * 权限
	 */
	private Integer competence;

	/**
	 * 激活态
	 */
	private Integer activeStatus;
	private String phone;

	/**
	 * 注册时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regTime;

	/**
	 * 改动时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedTime;
	
	private String password;
	private String salt;

	public Integer getUsrid() {
		return usrid;
	}

	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public Integer getRegionDepartment() {
		return regionDepartment;
	}

	public void setRegionDepartment(Integer regionDepartment) {
		this.regionDepartment = regionDepartment;
	}

	public Integer getCompetence() {
		return competence;
	}

	public void setCompetence(Integer competence) {
		this.competence = competence;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Accounts{" + "usrid=" + usrid + ", usrname='" + usrname + '\'' + ", regionDepartment="
				+ regionDepartment + ", competence=" + competence + ", activeStatus=" + activeStatus + ", phone='"
				+ phone + '\'' + ", regTime=" + regTime + ", modifiedTime=" + modifiedTime + ", password='" + password
				+ '\'' + ", salt='" + salt + '\'' + '}';
	}
}
