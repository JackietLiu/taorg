/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.entity.ent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 企业项目施工队关系Entity
 * @author Jackiet
 * @version 2016-12-19
 */
public class TbEntRelation extends TreeEntity<TbEntRelation> {
	
	private static final long serialVersionUID = 1L;
	private TbEntRelation parent;		// 父节点
	private String allId;		// all_id
	private String parentName; //父节点名称
	private String allName;    //子节点名称
	private String parentId;
	private String type;
	private String url;
	public TbEntRelation() {
		super();
	}

	public TbEntRelation(String id){
		super(id);
	}

	@JsonBackReference
	public TbEntRelation getParent() {
		return parent;
	}

	public void setParent(TbEntRelation parent) {
		this.parent = parent;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getAllName() {
		return allName;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	@Length(min=0, max=50, message="all_id长度必须介于 0 和 50 之间")
	public String getAllId() {
		return allId;
	}

	public void setAllId(String allId) {
		this.allId = allId;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		//return parent != null && parent.getId() != null ? parent.getId() : "0";
		return parentId;
	}
}