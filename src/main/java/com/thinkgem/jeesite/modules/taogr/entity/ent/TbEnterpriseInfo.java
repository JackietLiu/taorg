/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.ent;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 企业信息Entity
 * @author louis
 * @version 2016-11-24
 */
public class TbEnterpriseInfo extends TreeEntity<TbEnterpriseInfo> {
	
	private static final long serialVersionUID = 1L;
	private String entName;		// 企业名称
	private String entNo;		// 企业编号
	private String contactName;		// 联系人
	private String contactTitle;		// 联系人职务
	private String contactTel;		// 联系电话
	private String contactMail;		// 联系人邮箱
	private String entRep;		// 企业法人
	private String entAddress;		// 办公地址
	private String entMail;		// 企业邮箱
	private String isActive;		// 是否在用
	private String entType;		// 企业性质
	private String regAddress;		// 注册地址
	private String warnTp;		// 企业预警额度试方式
	private String warnValue;		// 企业预警额度值
/*	private TbEnterpriseInfo parent;		// 父级编号
	private String parentIds;		// 父级所有编号 
*/	private String fee;
	private String feeTotal;
	private User user;
	private String entId;
	private String logoPath;
	private String type; 	// 机构类型（1：公司；2：部门；3：小组）
	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	
	public TbEnterpriseInfo() {
		super();
	}

	public TbEnterpriseInfo(String id){
		super(id);
	}

	@Length(min=0, max=100, message="企业名称长度必须介于 0 和 100 之间")
	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}
	

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	@Length(min=0, max=100, message="企业编号长度必须介于 0 和 100 之间")
	public String getEntNo() {
		return entNo;
	}

	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Length(min=0, max=100, message="联系人职务长度必须介于 0 和 100 之间")
	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	
	@Length(min=0, max=100, message="联系人邮箱长度必须介于 0 和 100 之间")
	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}
	
	@Length(min=0, max=50, message="企业法人长度必须介于 0 和 50 之间")
	public String getEntRep() {
		return entRep;
	}

	public void setEntRep(String entRep) {
		this.entRep = entRep;
	}
	
	@Length(min=0, max=255, message="办公地址长度必须介于 0 和 255 之间")
	public String getEntAddress() {
		return entAddress;
	}

	public void setEntAddress(String entAddress) {
		this.entAddress = entAddress;
	}
	
	@Length(min=0, max=100, message="企业邮箱长度必须介于 0 和 100 之间")
	public String getEntMail() {
		return entMail;
	}

	public void setEntMail(String entMail) {
		this.entMail = entMail;
	}
	
	@Length(min=0, max=1, message="是否在用长度必须介于 0 和 1 之间")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Length(min=0, max=64, message="企业性质长度必须介于 0 和 64 之间")
	public String getEntType() {
		return entType;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}
	
	@Length(min=0, max=255, message="注册地址长度必须介于 0 和 255 之间")
	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	
	@Length(min=0, max=20, message="企业预警额度试方式长度必须介于 0 和 20 之间")
	public String getWarnTp() {
		return warnTp;
	}

	public void setWarnTp(String warnTp) {
		this.warnTp = warnTp;
	}
	
	@Length(min=0, max=20, message="企业预警额度值长度必须介于 0 和 20 之间")
	public String getWarnValue() {
		return warnValue;
	}

	public void setWarnValue(String warnValue) {
		this.warnValue = warnValue;
	}
	
	
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(String feeTotal) {
		this.feeTotal = feeTotal;
	}

	public TbEnterpriseInfo getParent() {
		return parent;
	}

	public void setParent(TbEnterpriseInfo parent) {
		this.parent = parent;
	}

/*	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}*/
}