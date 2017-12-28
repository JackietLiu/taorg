/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.dao.registe;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taorg.entity.registe.SysUser;

/**
 * 注册功能DAO接口
 * @author Jackiet
 * @version 2017-01-03
 */
@MyBatisDao
public interface SysUserDao extends CrudDao<SysUser> {
	
}