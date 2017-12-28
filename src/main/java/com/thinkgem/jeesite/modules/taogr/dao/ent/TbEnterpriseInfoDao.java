/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.dao.ent;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEnterpriseInfo;

/**
 * 企业信息DAO接口
 * @author louis
 * @version 2016-11-24
 */
@MyBatisDao
public interface TbEnterpriseInfoDao extends TreeDao<TbEnterpriseInfo> {

	List<TbEnterpriseInfo> findListByEntId(TbEnterpriseInfo tbEnterpriseInfo);

	
	
}