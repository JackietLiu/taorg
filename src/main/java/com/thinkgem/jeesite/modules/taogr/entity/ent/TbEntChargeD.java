/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.ent;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 充值明细Entity
 * @author louis
 * @version 2016-10-31
 */
public class TbEntChargeD extends DataEntity<TbEntChargeD> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 企业ID
	private String fee;		// 余额
	private String feeTotal;		// 总充值额
	private String feeCertificateNo;		// 转账号/支票号
	private String feeImage;		// 截图
	private User userAudit;		// 确认人
	private String dtAudit;		// 确认日期
	private String statusAudit;		// 确认标志
	private String oldFee;//修改前的老余额
	private String oldFeeTotal;//修改前的老总充值金额
	
	public TbEntChargeD() {
		super();
	}

	public TbEntChargeD(String id){
		super(id);
	}


	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=0, max=20, message="余额长度必须介于 0 和 20 之间")
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
	
	@Length(min=0, max=20, message="总充值额长度必须介于 0 和 20 之间")
	public String getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(String feeTotal) {
		this.feeTotal = feeTotal;
	}
	
	@Length(min=0, max=200, message="转账号/支票号长度必须介于 0 和 200 之间")
	public String getFeeCertificateNo() {
		return feeCertificateNo;
	}

	public void setFeeCertificateNo(String feeCertificateNo) {
		this.feeCertificateNo = feeCertificateNo;
	}
	
	@Length(min=0, max=255, message="截图长度必须介于 0 和 255 之间")
	public String getFeeImage() {
		return feeImage;
	}

	public void setFeeImage(String feeImage) {
		this.feeImage = feeImage;
	}
	
	public User getUserAudit() {
		return userAudit;
	}

	public void setUserAudit(User userAudit) {
		this.userAudit = userAudit;
	}
	
	@Length(min=0, max=20, message="确认日期长度必须介于 0 和 20 之间")
	public String getDtAudit() {
		return dtAudit;
	}

	public void setDtAudit(String dtAudit) {
		this.dtAudit = dtAudit;
	}
	
	@Length(min=0, max=1, message="确认标志长度必须介于 0 和 1 之间")
	public String getStatusAudit() {
		return statusAudit;
	}

	public void setStatusAudit(String statusAudit) {
		this.statusAudit = statusAudit;
	}

	public String getOldFee() {
		return oldFee;
	}

	public void setOldFee(String oldFee) {
		this.oldFee = oldFee;
	}

	public String getOldFeeTotal() {
		return oldFeeTotal;
	}

	public void setOldFeeTotal(String oldFeeTotal) {
		this.oldFeeTotal = oldFeeTotal;
	}
	
}