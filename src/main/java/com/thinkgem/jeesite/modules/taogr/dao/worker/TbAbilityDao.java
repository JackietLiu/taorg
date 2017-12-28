/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.dao.worker;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbAbility;

/**
 * 工种DAO接口
 * @author louis
 * @version 2016-11-03
 */
@MyBatisDao
public interface TbAbilityDao extends TreeDao<TbAbility> {
	
}