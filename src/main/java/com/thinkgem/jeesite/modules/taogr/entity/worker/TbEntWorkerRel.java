/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.worker;

import java.util.List;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEnterpriseInfo;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerInfo;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 企业信息和职工关系Entity
 * @author louis
 * @version 2016-11-01
 */
public class TbEntWorkerRel extends DataEntity<TbEntWorkerRel> {
	
	private static final long serialVersionUID = 1L;
	private TbEnterpriseInfo ent;		// 企业ID
	private TbWorkerInfo worker;		// 职工ID
	private TbAbility profTyCd;		// 工种代码
	private TbWorkerAbility tbWorkerAbility; //工人技能	
	private TbInsuranceList tbInsuranceList; //保险人员列表
	private TdInsuranceType insuranceTpCd;   //保险类型
	private Office office;             // 企业ID
	private String entId;
	

	private String status;		// 企业与员工关系状态1正常2解除
	private String workid ;
	
	
	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	private List<String> strList ;
	
	public TbEntWorkerRel() {
		super();
	}

	public TbEntWorkerRel(String id){
		super(id);
	}

	public TbEnterpriseInfo getEnt() {
		return ent;
	}

	public void setEnt(TbEnterpriseInfo ent) {
		this.ent = ent;
	}
	
	public TbWorkerInfo getWorker() {
		return worker;
	}

	public void setWorker(TbWorkerInfo worker) {
		this.worker = worker;
	}
	
	@Length(min=0, max=1, message="企业与员工关系状态1正常2解除长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
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

	
}