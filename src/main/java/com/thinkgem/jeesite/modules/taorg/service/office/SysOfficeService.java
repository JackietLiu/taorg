/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.office;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taorg.entity.ent.TbEntRelation;
import com.thinkgem.jeesite.modules.taorg.entity.office.SysOffice;
import com.thinkgem.jeesite.modules.taorg.service.ent.TbEntRelationService;
import com.thinkgem.jeesite.modules.taorg.dao.office.SysOfficeDao;

/**
 * 企业/机构Service
 * @author Jackiet
 * @version 2017-01-26
 */
@Service
@Transactional(readOnly = true)
public class SysOfficeService extends TreeService<SysOfficeDao, SysOffice> {
	@Autowired
	private TbEntRelationService tbEntRelationService;
	
	public SysOffice get(String id) {
		return super.get(id);
	}
	
	public List<SysOffice> findList(SysOffice sysOffice) {
		if (StringUtils.isNotBlank(sysOffice.getParentIds())){
			sysOffice.setParentIds(","+sysOffice.getParentIds()+",");
		}
		return super.findList(sysOffice);
	}

	
	@Transactional(readOnly = false)
	public void save(SysOffice sysOffice) {
		super.save(sysOffice);
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系

		String parentId = sysOffice.getParentId();
		String entId = sysOffice.getId();		
		System.out.println("TbEnterpriseInfo_entId="+sysOffice.getId()+"----parentId="+sysOffice.getParentId());	
		tbEntRelation.setType("1");
		tbEntRelation.setUrl("taorg/office/sysOffice/form");
		tbEntRelation.setParentId(parentId);
		tbEntRelation.setAllId(entId);
		tbEntRelationService.save(tbEntRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysOffice sysOffice) {
		super.delete(sysOffice);
	}

	public List<SysOffice> findListByOfficeId(SysOffice sysOffice) {
		if (StringUtils.isNotBlank(sysOffice.getParentIds())){
			sysOffice.setParentIds(","+sysOffice.getParentIds()+",");
		}
		return dao.findListByOfficeId(sysOffice);
	
	}
	
}