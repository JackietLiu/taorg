/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.dao.ent;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeM;

/**
 * 充值汇总DAO接口
 * @author louis
 * @version 2016-10-31
 */
@MyBatisDao
public interface TbEntChargeMDao extends CrudDao<TbEntChargeM> {

	TbEntChargeM getByEneId(String entId);

	void updateM(TbEntChargeM tbEntChargeM);

	void updateMFeeByEnt(TbEntChargeM tbEntChargeM);

	void updateMAddFeeByEnt(TbEntChargeM tbEntChargeM);

	
}