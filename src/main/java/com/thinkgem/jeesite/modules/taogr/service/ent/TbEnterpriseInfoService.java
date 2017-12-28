/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.ent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEnterpriseInfo;
import com.thinkgem.jeesite.modules.taorg.entity.ent.TbEntRelation;
import com.thinkgem.jeesite.modules.taorg.service.ent.TbEntRelationService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taogr.dao.ent.TbEnterpriseInfoDao;

/**
 * 企业信息Service
 * @author louis
 * @version 2016-11-24
 */
@Service
@Transactional(readOnly = true)
public class TbEnterpriseInfoService extends TreeService<TbEnterpriseInfoDao, TbEnterpriseInfo> {
	@Autowired
	private TbEntRelationService tbEntRelationService;
	
	public TbEnterpriseInfo get(String id) {
		return super.get(id);
	}
	public List<TbEnterpriseInfo> findListByEntId(TbEnterpriseInfo tbEnterpriseInfo){
		if (StringUtils.isNotBlank(tbEnterpriseInfo.getParentIds())){
			tbEnterpriseInfo.setParentIds(","+tbEnterpriseInfo.getParentIds()+",");
		}
		return dao.findListByEntId(tbEnterpriseInfo);
	}
	public List<TbEnterpriseInfo> findList(TbEnterpriseInfo tbEnterpriseInfo) {
		if (StringUtils.isNotBlank(tbEnterpriseInfo.getParentIds())){
			tbEnterpriseInfo.setParentIds(","+tbEnterpriseInfo.getParentIds()+",");
		}
	
		return super.findList(tbEnterpriseInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TbEnterpriseInfo tbEnterpriseInfo) {
		super.save(tbEnterpriseInfo);
	    Office office = new Office();
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系
		String entId=tbEnterpriseInfo.getId();		
		System.out.println("TbEnterpriseInfo_entId="+entId);
		office.setId(entId);
		
		  tbEntRelation.setType("1");
		  tbEntRelation.setUrl("taogr/ent/tbEntpriseInfo/form");
			   tbEntRelation.setParentId(tbEnterpriseInfo.getParentId());
			   tbEntRelation.setAllId(tbEnterpriseInfo.getId());
			   tbEntRelationService.save(tbEntRelation);

	}
	
	@Transactional(readOnly = false)
	public void delete(TbEnterpriseInfo tbEnterpriseInfo) {
		TbEntRelation tbEntRelation = new TbEntRelation();//企业项目施工队关系
		super.delete(tbEnterpriseInfo);
		tbEntRelation.setAllId(tbEnterpriseInfo.getId());
		tbEntRelationService.delete(tbEntRelation);
	}
	public List<TbEnterpriseInfo> findAllList(TbEnterpriseInfo tbEnterpriseInfo) {
		return dao.findAllList(tbEnterpriseInfo);
	}
}