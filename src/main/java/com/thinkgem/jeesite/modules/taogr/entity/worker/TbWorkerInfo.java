/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.worker;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEnterpriseInfo;

/**
 * 工人信息Entity
 * @author louis
 * @version 2016-12-06
 */
public class TbWorkerInfo extends DataEntity<TbWorkerInfo> {
	
	private static final long serialVersionUID = 1L;
	private String workerCode;		// 注册名
	private String py;		// 拼音
	private String nameOnce;		// 曾用名
	private String isActive;		// 是否在用
	private String idNo;		// 身份证
	private String address;		// 住址
	private String jg;		// 籍贯
	private String nowAddress;		// 现居地址
	private String workerImage;		// 头像
	private String phoneNumber;   //手机号码
	private String sex;
	private TbEnterpriseInfo ent;		// 企业ID
	private TbAbility profTyCd;		// 工种代码
	private TbWorkerAbility tbWorkerAbility; //工人技能	
	private TbInsuranceList tbInsuranceList; //保险人员列表
	private TdInsuranceType insuranceTpCd;   //保险类型
	private TbEntWorkerRel tbEntWorkerRel;
	private Office office;
	
	
	public TbEntWorkerRel getTbEntWorkerRel() {
		return tbEntWorkerRel;
	}

	public void setTbEntWorkerRel(TbEntWorkerRel tbEntWorkerRel) {
		this.tbEntWorkerRel = tbEntWorkerRel;
	}

	public TbEnterpriseInfo getEnt() {
		return ent;
	}

	public void setEnt(TbEnterpriseInfo ent) {
		this.ent = ent;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public TbAbility getProfTyCd() {
		return profTyCd;
	}

	public void setProfTyCd(TbAbility profTyCd) {
		this.profTyCd = profTyCd;
	}

	public TbWorkerAbility getTbWorkerAbility() {
		return tbWorkerAbility;
	}

	public void setTbWorkerAbility(TbWorkerAbility tbWorkerAbility) {
		this.tbWorkerAbility = tbWorkerAbility;
	}

	public TbInsuranceList getTbInsuranceList() {
		return tbInsuranceList;
	}

	public void setTbInsuranceList(TbInsuranceList tbInsuranceList) {
		this.tbInsuranceList = tbInsuranceList;
	}

	public TdInsuranceType getInsuranceTpCd() {
		return insuranceTpCd;
	}

	public void setInsuranceTpCd(TdInsuranceType insuranceTpCd) {
		this.insuranceTpCd = insuranceTpCd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public TbWorkerInfo() {
		super();
	}

	public TbWorkerInfo(String id){
		super(id);
	}

	@Length(min=0, max=100, message="注册名长度必须介于 0 和 100 之间")
	public String getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}
	
	
	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}
	
	@Length(min=0, max=100, message="曾用名长度必须介于 0 和 100 之间")
	public String getNameOnce() {
		return nameOnce;
	}

	public void setNameOnce(String nameOnce) {
		this.nameOnce = nameOnce;
	}
	
	@Length(min=0, max=1, message="是否在用长度必须介于 0 和 1 之间")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Length(min=0, max=100, message="身份证长度必须介于 0 和 100 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	@Length(min=0, max=255, message="住址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="籍贯长度必须介于 0 和 50 之间")
	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}
	
	@Length(min=0, max=255, message="现居地址长度必须介于 0 和 255 之间")
	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	
	@Length(min=0, max=255, message="worker_image长度必须介于 0 和 255 之间")
	public String getWorkerImage() {
		return workerImage;
	}

	public void setWorkerImage(String workerImage) {
		this.workerImage = workerImage;
	}
	
}