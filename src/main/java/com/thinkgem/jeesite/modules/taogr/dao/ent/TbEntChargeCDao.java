/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.dao.ent;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeC;

/**
 * 企业费用变更明细DAO接口
 * @author louis
 * @version 2016-11-01
 */
@MyBatisDao
public interface TbEntChargeCDao extends CrudDao<TbEntChargeC> {

	void toUpdateStatus(TbEntChargeC tbEntChargeC);
	
}