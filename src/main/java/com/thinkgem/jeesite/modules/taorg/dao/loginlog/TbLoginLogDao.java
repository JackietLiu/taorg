/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.dao.loginlog;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taorg.entity.loginlog.TbLoginLog;

/**
 * 登录日志DAO接口
 * @author Jackiet
 * @version 2016-12-29
 */
@MyBatisDao
public interface TbLoginLogDao extends CrudDao<TbLoginLog> {
	
}