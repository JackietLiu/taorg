/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.dao.pro;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taorg.entity.pro.TbWorkProjects;

/**
 * 工程项目DAO接口
 * @author Jackiet
 * @version 2016-12-14
 */
@MyBatisDao
public interface TbWorkProjectsDao extends TreeDao<TbWorkProjects> {

	List<TbWorkProjects> findProByEntId(TbWorkProjects ent);
	
}