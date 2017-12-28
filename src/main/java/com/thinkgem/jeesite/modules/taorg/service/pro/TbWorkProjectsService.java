/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.pro;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taorg.entity.ent.TbEntRelation;
import com.thinkgem.jeesite.modules.taorg.entity.pro.TbWorkProjects;
import com.thinkgem.jeesite.modules.taorg.service.ent.TbEntRelationService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taorg.dao.pro.TbWorkProjectsDao;

/**
 * 工程项目Service
 * @author Jackiet
 * @version 2016-12-14
 */
@Service
@Transactional(readOnly = true)
public class TbWorkProjectsService extends TreeService<TbWorkProjectsDao, TbWorkProjects> {
	@Autowired
	private TbEntRelationService tbEntRelationService;
	
	private TbWorkProjectsDao workProjects;
	
	public TbWorkProjects get(String id) {
		return super.get(id);
	}
	
	public List<TbWorkProjects> findList(TbWorkProjects tbWorkProjects) {
		if (StringUtils.isNotBlank(tbWorkProjects.getParentIds())){
			tbWorkProjects.setParentIds(","+tbWorkProjects.getParentIds()+",");
		}
		return super.findList(tbWorkProjects);
	}
	public List<TbWorkProjects> findProByEntId(String entId) {
		List<TbWorkProjects> list = null;
	    
			TbWorkProjects ent = new TbWorkProjects();
			ent.setOffice(new Office(entId));
			list = workProjects.findProByEntId(ent);

		return list;
	}
	@Transactional(readOnly = false)
	public void save(TbWorkProjects tbWorkProjects) {
		super.save(tbWorkProjects);
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系
		String entId="";	
		if ("".equals(tbWorkProjects.getOffice().getId()) ||"null".equals( tbWorkProjects.getOffice().getId())) {
			  entId="0";
			  tbEntRelation.setParentId(entId);
			  System.out.println("TbWorkProjects_entId="+entId);
		} else {
			entId=tbWorkProjects.getOffice().getId();
			tbEntRelation.setParentId(entId);
			System.out.println("TbWorkProjects_entId="+entId);
		}
		    tbEntRelation.setType("2");
		    tbEntRelation.setUrl("taorg/pro/tbWorkProjects/form");
			tbEntRelation.setAllId(tbWorkProjects.getId());
			tbEntRelationService.save(tbEntRelation);
	
	}
	
	@Transactional(readOnly = false)
	public void delete(TbWorkProjects tbWorkProjects) {
/*		String aId= tbWorkProjects.getId();*/
		super.delete(tbWorkProjects);
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系		
		tbEntRelation.setAllId(tbWorkProjects.getId());
	    tbEntRelationService.delete(tbEntRelation);
	
		
	}
	
}