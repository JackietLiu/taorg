/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.entity.worker;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TdInsuranceType;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 在保人员查询Entity
 * @author jackiet
 * @version 2016-11-04
 */
public class TbInsurance extends DataEntity<TbInsurance> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 企业ID
	private String workerName;		// 工人姓名
	private String idNo;		// 身份证
	private TdInsuranceType insuranceTp;		// 保单类型
	private String feePer;		// 保费
	private String dtStart;		// 开始日期
	private String dtEnd;		// 结束日期
	private User userAudit;		// 确认人
	private String dtAudit;		// 确认日期
	private String statusAudit;		// 确认标志
	private String inQuit;		// 进保退保
	private String dtQuitInsurance;		// 退保日期
	private User userCreateQuit;		// 退保操作人
	private String dtQuitCreate;		// 退保操作日期
	private User userAuditQuit;		// 退保确认人
	private String dtAuditQuit;		// 退保确认日期
	private String statusAuditQuit;		// 退保确认标志
	private String entNo;//企业编号
	private String endFlag; //保险即将到期标志
    private String entId;

    
    
    
	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}

	private String insNo;//险种编号
	
	public String getEntNo() {
		return entNo;
	}

	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}

	public String getInsNo() {
		return insNo;
	}

	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}

	public TbInsurance() {
		super();
	}

	public TbInsurance(String id){
		super(id);
	}


	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=0, max=100, message="工人姓名长度必须介于 0 和 100 之间")
	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	
	@Length(min=0, max=100, message="身份证长度必须介于 0 和 100 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public TdInsuranceType getInsuranceTp() {
		return insuranceTp;
	}

	public void setInsuranceTp(TdInsuranceType insuranceTp) {
		this.insuranceTp = insuranceTp;
	}
	
	@Length(min=0, max=20, message="保费长度必须介于 0 和 20 之间")
	public String getFeePer() {
		return feePer;
	}

	public void setFeePer(String feePer) {
		this.feePer = feePer;
	}
	
	@Length(min=0, max=20, message="开始日期长度必须介于 0 和 20 之间")
	public String getDtStart() {
		return dtStart;
	}

	public void setDtStart(String dtStart) {
		this.dtStart = dtStart;
	}
	
	@Length(min=0, max=20, message="结束日期长度必须介于 0 和 20 之间")
	public String getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(String dtEnd) {
		this.dtEnd = dtEnd;
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
	
	@Length(min=0, max=1, message="进保退保长度必须介于 0 和 1 之间")
	public String getInQuit() {
		return inQuit;
	}

	public void setInQuit(String inQuit) {
		this.inQuit = inQuit;
	}
	
	@Length(min=0, max=1, message="退保日期长度必须介于 0 和 1 之间")
	public String getDtQuitInsurance() {
		return dtQuitInsurance;
	}

	public void setDtQuitInsurance(String dtQuitInsurance) {
		this.dtQuitInsurance = dtQuitInsurance;
	}
	
	public User getUserCreateQuit() {
		return userCreateQuit;
	}

	public void setUserCreateQuit(User userCreateQuit) {
		this.userCreateQuit = userCreateQuit;
	}
	
	@Length(min=0, max=20, message="退保操作日期长度必须介于 0 和 20 之间")
	public String getDtQuitCreate() {
		return dtQuitCreate;
	}

	public void setDtQuitCreate(String dtQuitCreate) {
		this.dtQuitCreate = dtQuitCreate;
	}
	
	public User getUserAuditQuit() {
		return userAuditQuit;
	}

	public void setUserAuditQuit(User userAuditQuit) {
		this.userAuditQuit = userAuditQuit;
	}
	
	@Length(min=0, max=20, message="退保确认日期长度必须介于 0 和 20 之间")
	public String getDtAuditQuit() {
		return dtAuditQuit;
	}

	public void setDtAuditQuit(String dtAuditQuit) {
		this.dtAuditQuit = dtAuditQuit;
	}
	
	@Length(min=0, max=1, message="退保确认标志长度必须介于 0 和 1 之间")
	public String getStatusAuditQuit() {
		return statusAuditQuit;
	}

	public void setStatusAuditQuit(String statusAuditQuit) {
		this.statusAuditQuit = statusAuditQuit;
	}
	
}