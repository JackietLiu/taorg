/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.worker;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 保险类型信息Entity
 * @author louis
 * @version 2016-11-01
 */
public class TdInsuranceType extends DataEntity<TdInsuranceType> {
	
	private static final long serialVersionUID = 1L;
	private String insName;		// 保险名称
	private String insNameAbbr;		// 保险简称
	private String describeType;		// 简介
	private String buyMemo;		// 购买注意事项
	private String feePer;		// 保费
	private String isActive;		// 是否在用
	private String corpName;		// 销售公司
	private String insNo;//险种编号
	
	
	public String getInsNo() {
		return insNo;
	}

	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}

	public TdInsuranceType() {
		super();
	}

	public TdInsuranceType(String id){
		super(id);
	}

	@Length(min=0, max=255, message="保险名称长度必须介于 0 和 255 之间")
	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}
	
	@Length(min=0, max=255, message="保险简称长度必须介于 0 和 255 之间")
	public String getInsNameAbbr() {
		return insNameAbbr;
	}

	public void setInsNameAbbr(String insNameAbbr) {
		this.insNameAbbr = insNameAbbr;
	}
	
	
	public String getDescribeType() {
		return describeType;
	}

	public void setDescribeType(String describeType) {
		this.describeType = describeType;
	}

	public String getBuyMemo() {
		return buyMemo;
	}

	public void setBuyMemo(String buyMemo) {
		this.buyMemo = buyMemo;
	}
	
	@Length(min=0, max=20, message="保费长度必须介于 0 和 20 之间")
	public String getFeePer() {
		return feePer;
	}

	public void setFeePer(String feePer) {
		this.feePer = feePer;
	}
	
	@Length(min=0, max=2, message="是否在用长度必须介于 0 和 2 之间")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Length(min=0, max=100, message="销售公司长度必须介于 0 和 100 之间")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
}