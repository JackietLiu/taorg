/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.dao.worker;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TdInsuranceType;

/**
 * 保险类型信息DAO接口
 * @author louis
 * @version 2016-11-01
 */
@MyBatisDao
public interface TdInsuranceTypeDao extends CrudDao<TdInsuranceType> {

	TdInsuranceType findInsTypeByNo(String insNo);
	
}