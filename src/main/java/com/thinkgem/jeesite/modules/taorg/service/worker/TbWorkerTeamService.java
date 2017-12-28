/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.worker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taorg.entity.ent.TbEntRelation;
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbWorkerTeam;
import com.thinkgem.jeesite.modules.taorg.service.ent.TbEntRelationService;
import com.thinkgem.jeesite.modules.taorg.dao.worker.TbWorkerTeamDao;

/**
 * 施工队Service
 * @author Jackiet
 * @version 2016-12-20
 */
@Service
@Transactional(readOnly = true)
public class TbWorkerTeamService extends CrudService<TbWorkerTeamDao, TbWorkerTeam> {
    @Autowired
	private TbEntRelationService tbEntRelationService;

	public TbWorkerTeam get(String id) {
		return super.get(id);
	}
	
	public List<TbWorkerTeam> findList(TbWorkerTeam tbWorkerTeam) {
		return super.findList(tbWorkerTeam);
	}
	
	public Page<TbWorkerTeam> findPage(Page<TbWorkerTeam> page, TbWorkerTeam tbWorkerTeam) {
		return super.findPage(page, tbWorkerTeam);
	}
	
	@Transactional(readOnly = false)
	public void save(TbWorkerTeam tbWorkerTeam) {
		super.save(tbWorkerTeam);
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系
		String proId="";
		if ("".equals(tbWorkerTeam.getProjects().getId()) ||"null".equals( tbWorkerTeam.getProjects().getId())) {
			proId="0";
			tbEntRelation.setParentId(proId);
			System.out.println("TbWorkProjects_proId="+proId);	
		}else{
			proId=tbWorkerTeam.getProjects().getId();
			tbEntRelation.setParentId(proId);
			System.out.println("TbWorkProjects_proId="+proId);	
		}
		tbEntRelation.setType("3");
		       tbEntRelation.setUrl("taorg/worker/tbWorkerTeam/form");
			   tbEntRelation.setAllId(tbWorkerTeam.getId());
			   tbEntRelationService.save(tbEntRelation);
	
		
			  
			
	}
	
	@Transactional(readOnly = false)
	public void delete(TbWorkerTeam tbWorkerTeam) {
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系
		super.delete(tbWorkerTeam);
		tbEntRelation.setAllId(tbWorkerTeam.getId());
		tbEntRelationService.delete(tbEntRelation);
	}
	
}