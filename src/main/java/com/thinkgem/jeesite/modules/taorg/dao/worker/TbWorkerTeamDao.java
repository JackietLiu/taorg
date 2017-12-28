/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.dao.worker;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbWorkerTeam;

/**
 * 施工队DAO接口
 * @author Jackiet
 * @version 2016-12-20
 */
@MyBatisDao
public interface TbWorkerTeamDao extends CrudDao<TbWorkerTeam> {
	
}