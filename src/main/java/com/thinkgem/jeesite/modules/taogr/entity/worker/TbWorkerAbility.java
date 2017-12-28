/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.worker;

import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerInfo;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 工人技能信息Entity
 * @author louis
 * @version 2016-11-01
 */
public class TbWorkerAbility extends DataEntity<TbWorkerAbility> {
	
	private static final long serialVersionUID = 1L;
	private TbWorkerInfo worker;		// 工人
	private TbAbility profTyCd;		// 工种代码
	private String isAuth;		// 是否有证书
	private String authNo;		// 证书编号
	private String authImage;		// 证书图片
	private String isActive;		// 是否在用
	
	public TbWorkerAbility() {
		super();
	}

	public TbWorkerAbility(String id){
		super(id);
	}

	public TbWorkerInfo getWorker() {
		return worker;
	}

	public void setWorker(TbWorkerInfo worker) {
		this.worker = worker;
	}
	
	
	public TbAbility getProfTyCd() {
		return profTyCd;
	}

	public void setProfTyCd(TbAbility profTyCd) {
		this.profTyCd = profTyCd;
	}

	@Length(min=0, max=2, message="是否有证书长度必须介于 0 和 2 之间")
	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	
	@Length(min=0, max=100, message="证书编号长度必须介于 0 和 100 之间")
	public String getAuthNo() {
		return authNo;
	}

	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	
	@Length(min=0, max=200, message="证书图片长度必须介于 0 和 200 之间")
	public String getAuthImage() {
		return authImage;
	}

	public void setAuthImage(String authImage) {
		this.authImage = authImage;
	}
	
	@Length(min=0, max=2, message="是否在用长度必须介于 0 和 2 之间")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}