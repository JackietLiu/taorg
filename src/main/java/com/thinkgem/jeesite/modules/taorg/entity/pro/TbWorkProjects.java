/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.entity.pro;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbWorkerTeam;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 工程项目Entity
 * @author Jackiet
 * @version 2016-12-14
 */
public class TbWorkProjects extends TreeEntity<TbWorkProjects> {
	
	private static final long serialVersionUID = 1L;
	private String proName;		// 项目名称
	private String address;		// 项目地址
	private String principal;		// 项目负责人
	private String manager;		// 项目经理
	private String remark;		// 备注
	private Office office;		// 所属企业
	private TbWorkProjects parent;		// 父项目
/*	private String parentIds;*/		// 所有父项目
	private String teamName;		// 施工队名称
	private String type;          //类型：1.项目2.施工队
	private TbWorkerTeam workerTeam; //施工队
	
	public TbWorkProjects() {
		super();
	}

	public TbWorkProjects(String id){
		super(id);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=255, message="项目名称长度必须介于 0 和 255 之间")
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	
	@Length(min=0, max=255, message="项目地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=25, message="项目负责人长度必须介于 0 和 25 之间")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	@Length(min=0, max=25, message="项目经理长度必须介于 0 和 25 之间")
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@JsonBackReference
	public TbWorkProjects getParent() {
		return parent;
	}

	public void setParent(TbWorkProjects parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=50, message="施工队名称长度必须介于 0 和 50 之间")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	

	public TbWorkerTeam getWorkerTeam() {
		return workerTeam;
	}

	public void setWorkerTeam(TbWorkerTeam workerTeam) {
		this.workerTeam = workerTeam;
	}
	
}