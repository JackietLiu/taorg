/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.ent;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeM;
import com.thinkgem.jeesite.modules.taogr.dao.ent.TbEntChargeMDao;

/**
 * 充值汇总Service
 * @author louis
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class TbEntChargeMService extends CrudService<TbEntChargeMDao, TbEntChargeM> {

	public TbEntChargeM get(String id) {
		return super.get(id);
	}
	
	public List<TbEntChargeM> findList(TbEntChargeM tbEntChargeM) {
		return super.findList(tbEntChargeM);
	}
	
	public Page<TbEntChargeM> findPage(Page<TbEntChargeM> page, TbEntChargeM tbEntChargeM) {
		return super.findPage(page, tbEntChargeM);
	}
	
	@Transactional(readOnly = false)
	public void save(TbEntChargeM tbEntChargeM) {
		super.save(tbEntChargeM);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbEntChargeM tbEntChargeM) {
		super.delete(tbEntChargeM);
	}

	public TbEntChargeM getByEneId(String entId) {
		return dao.getByEneId(entId);
	}

	public void updateM(TbEntChargeM tbEntChargeM) {
		dao.updateM(tbEntChargeM);
	}

	@Transactional(readOnly = false)
	public void updateMFeeByEnt(TbEntChargeM tbEntChargeM) {
		dao.updateMFeeByEnt(tbEntChargeM);
	}

	public void updateMAddFeeByEnt(TbEntChargeM tbEntChargeM) {
		dao.updateMAddFeeByEnt(tbEntChargeM);
	}
	
}