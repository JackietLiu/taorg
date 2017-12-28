/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.entity.worker;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.taorg.entity.pro.TbWorkProjects;

/**
 * 施工队Entity
 * @author Jackiet
 * @version 2016-12-20
 */
public class TbWorkerTeam extends DataEntity<TbWorkerTeam> {
	
	private static final long serialVersionUID = 1L;
	private TbWorkProjects projects;		// 项目id
	private String teamName;		// 队名称
	
	public TbWorkerTeam() {
		super();
	}

	public TbWorkerTeam(String id){
		super(id);
	}

	public TbWorkProjects getProjects() {
		return projects;
	}

	public void setProjects(TbWorkProjects projects) {
		this.projects = projects;
	}
	
	@Length(min=0, max=255, message="队名称长度必须介于 0 和 255 之间")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}