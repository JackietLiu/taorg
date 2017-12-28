/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.entity.worker;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 工种Entity
 * @author louis
 * @version 2016-11-03
 */
public class TbAbility extends TreeEntity<TbAbility> {
	
	private static final long serialVersionUID = 1L;
	private String abName;		// 工种名称
	private TbAbility parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	
	public TbAbility() {
		super();
	}

	public TbAbility(String id){
		super(id);
	}

	@Length(min=0, max=100, message="工种名称长度必须介于 0 和 100 之间")
	public String getAbName() {
		return abName;
	}

	public void setAbName(String abName) {
		this.abName = abName;
	}
	
	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public TbAbility getParent() {
		return parent;
	}

	public void setParent(TbAbility parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}