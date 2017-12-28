/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.entity.loginlog;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 登录日志Entity
 * @author Jackiet
 * @version 2016-12-29
 */
public class TbLoginLog extends DataEntity<TbLoginLog> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String times;		// 登录次数
	private String lastLoginDate;		// 最后一次登录时间
	private String officeId;		// 登录人所属企业
	private String userId;		// 登录人id
	private String officeName;		// 企业名称
	private String loginIp;    // 登录ip
	
	public TbLoginLog() {
		super();
	}

	public TbLoginLog(String id){
		super(id);
	}

	
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Length(min=0, max=50, message="登录名长度必须介于 0 和 50 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=10, message="登录次数长度必须介于 0 和 10 之间")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
	@Length(min=0, max=20, message="最后一次登录时间长度必须介于 0 和 20 之间")
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	@Length(min=0, max=50, message="登录人id长度必须介于 0 和 50 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	

	
}