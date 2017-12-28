/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.dao.worker;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbInsurance;

/**
 * 在保人员查询DAO接口
 * @author jackiet
 * @version 2016-11-04
 */
@MyBatisDao
public interface TbInsuranceDao extends CrudDao<TbInsurance> {
	
}