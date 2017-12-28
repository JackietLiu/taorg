/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.dao.worker;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerInfo;

/**
 * 工人信息DAO接口
 * @author louis
 * @version 2016-11-01
 */
@MyBatisDao
public interface TbWorkerInfoDao extends CrudDao<TbWorkerInfo> {

	TbWorkerInfo findWorkerByOther(TbWorkerInfo wi);
	
}