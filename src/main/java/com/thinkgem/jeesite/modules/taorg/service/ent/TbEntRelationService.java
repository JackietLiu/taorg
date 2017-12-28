/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.ent;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taorg.entity.ent.TbEntRelation;
import com.thinkgem.jeesite.modules.taorg.dao.ent.TbEntRelationDao;

/**
 * 企业项目施工队关系Service
 * @author Jackiet
 * @version 2016-12-19
 */
@Service
@Transactional(readOnly = true)
public class TbEntRelationService extends TreeService<TbEntRelationDao, TbEntRelation> {

	
	public TbEntRelation get(String id) {
		return super.get(id);
	}
	
	public List<TbEntRelation> findList(TbEntRelation tbEntRelation) {
		if (StringUtils.isNotBlank(tbEntRelation.getParentIds())){
			tbEntRelation.setParentIds(","+tbEntRelation.getParentIds()+",");
		}
		return super.findList(tbEntRelation);
	}
	public TbEntRelation findListForSearch(String allName) {
		return dao.findListForSearch(allName);
	}
	@Transactional(readOnly = false)
	public void save(TbEntRelation tbEntRelation) {	

		super.save(tbEntRelation);	           		
	}
	
	@Transactional(readOnly = false)
	public void delete(TbEntRelation tbEntRelation) {
		super.delete(tbEntRelation);
	}
	
}