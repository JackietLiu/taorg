/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.ent;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 企业费用变更明细Entity
 * @author louis
 * @version 2016-11-01
 */
public class TbEntChargeC extends DataEntity<TbEntChargeC> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 企业ID
	private String feeTotal;		// 金额
	private User userAudit;		// 确认人
	private String workerCount;		// 总工人数
	private String inQuit;		// 进保退保
	private String dtAudit;		// 确认日期
	private String beginDtAudit;		// 开始 确认日期
	private String endDtAudit;		// 结束 确认日期
	private String auditStatus ;//确认标志位
	
	public TbEntChargeC() {
		super();
	}

	public TbEntChargeC(String id){
		super(id);
	}

	
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=0, max=20, message="金额长度必须介于 0 和 20 之间")
	public String getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(String feeTotal) {
		this.feeTotal = feeTotal;
	}
	
	public User getUserAudit() {
		return userAudit;
	}

	public void setUserAudit(User userAudit) {
		this.userAudit = userAudit;
	}
	
	@Length(min=0, max=20, message="总工人数长度必须介于 0 和 20 之间")
	public String getWorkerCount() {
		return workerCount;
	}

	public void setWorkerCount(String workerCount) {
		this.workerCount = workerCount;
	}
	
	@Length(min=0, max=3, message="进保退保长度必须介于 0 和 3 之间")
	public String getInQuit() {
		return inQuit;
	}

	public void setInQuit(String inQuit) {
		this.inQuit = inQuit;
	}
	
	@Length(min=0, max=20, message="确认日期长度必须介于 0 和 20 之间")
	public String getDtAudit() {
		return dtAudit;
	}

	public void setDtAudit(String dtAudit) {
		this.dtAudit = dtAudit;
	}
	
	public String getBeginDtAudit() {
		return beginDtAudit;
	}

	public void setBeginDtAudit(String beginDtAudit) {
		this.beginDtAudit = beginDtAudit;
	}
	
	public String getEndDtAudit() {
		return endDtAudit;
	}

	public void setEndDtAudit(String endDtAudit) {
		this.endDtAudit = endDtAudit;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
		
}