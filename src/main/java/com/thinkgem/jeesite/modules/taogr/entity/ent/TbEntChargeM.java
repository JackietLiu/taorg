/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.ent;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 充值汇总Entity
 * @author louis
 * @version 2016-10-31
 */
public class TbEntChargeM extends DataEntity<TbEntChargeM> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 企业信息
	private String fee;		// 余额（分）
	private String feeTotal;		// 总充值额（分）
	private String DFee;//修改的明细余额
	private String oldDFee;//修改前的老明细金额
	private String oldFeeTotal;//修改前的老总充值金额
	
	public TbEntChargeM() {
		super();
	}

	public TbEntChargeM(String id){
		super(id);
	}


	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=0, max=20, message="余额（分）长度必须介于 0 和 20 之间")
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
	
	@Length(min=0, max=20, message="总充值额（分）长度必须介于 0 和 20 之间")
	public String getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(String feeTotal) {
		this.feeTotal = feeTotal;
	}


	public String getDFee() {
		return DFee;
	}

	public void setDFee(String dFee) {
		DFee = dFee;
	}

	public String getOldDFee() {
		return oldDFee;
	}

	public void setOldDFee(String oldDFee) {
		this.oldDFee = oldDFee;
	}

	public String getOldFeeTotal() {
		return oldFeeTotal;
	}

	public void setOldFeeTotal(String oldFeeTotal) {
		this.oldFeeTotal = oldFeeTotal;
	}
	
}