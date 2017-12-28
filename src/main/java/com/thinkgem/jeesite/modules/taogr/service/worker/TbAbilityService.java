/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbAbility;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TbAbilityDao;

/**
 * 工种Service
 * @author louis
 * @version 2016-11-03
 */
@Service
@Transactional(readOnly = true)
public class TbAbilityService extends TreeService<TbAbilityDao, TbAbility> {

	public TbAbility get(String id) {
		return super.get(id);
	}
	
	public List<TbAbility> findList(TbAbility tbAbility) {
		if (StringUtils.isNotBlank(tbAbility.getParentIds())){
			tbAbility.setParentIds(","+tbAbility.getParentIds()+",");
		}
		return super.findList(tbAbility);
	}
	
	@Transactional(readOnly = false)
	public void save(TbAbility tbAbility) {
		super.save(tbAbility);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbAbility tbAbility) {
		super.delete(tbAbility);
	}
	
}