/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerAbility;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TbWorkerAbilityDao;

/**
 * 工人技能信息Service
 * @author louis
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TbWorkerAbilityService extends CrudService<TbWorkerAbilityDao, TbWorkerAbility> {

	public TbWorkerAbility get(String id) {
		return super.get(id);
	}
	
	public List<TbWorkerAbility> findList(TbWorkerAbility tbWorkerAbility) {
		return super.findList(tbWorkerAbility);
	}
	
	public Page<TbWorkerAbility> findPage(Page<TbWorkerAbility> page, TbWorkerAbility tbWorkerAbility) {
		return super.findPage(page, tbWorkerAbility);
	}
	
	@Transactional(readOnly = false)
	public void save(TbWorkerAbility tbWorkerAbility) {
		super.save(tbWorkerAbility);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbWorkerAbility tbWorkerAbility) {
		super.delete(tbWorkerAbility);
	}
	
}