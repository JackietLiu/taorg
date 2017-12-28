/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbEntWorkerRel;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TbEntWorkerRelDao;

/**
 * 企业信息和职工关系Service
 * @author louis
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TbEntWorkerRelService extends CrudService<TbEntWorkerRelDao, TbEntWorkerRel> {

	public TbEntWorkerRel get(String id) {
		return super.get(id);
	}
	
	public List<TbEntWorkerRel> findList(TbEntWorkerRel tbEntWorkerRel) {
		return super.findList(tbEntWorkerRel);
	}
	
	public Page<TbEntWorkerRel> findPage(Page<TbEntWorkerRel> page, TbEntWorkerRel tbEntWorkerRel) {
		page.setPageSize(500);
		return super.findPage(page, tbEntWorkerRel);
	}
	
	@Transactional(readOnly = false)
	public void save(TbEntWorkerRel tbEntWorkerRel) {
		super.save(tbEntWorkerRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbEntWorkerRel tbEntWorkerRel) {
		super.delete(tbEntWorkerRel);
	}

	@Transactional(readOnly = false)
	public void updateStatus(TbEntWorkerRel tbEntWorkerRel) {
		dao.updateStatus(tbEntWorkerRel);
	}

	public List<TbEntWorkerRel> findListForBuy(TbEntWorkerRel tbEntWorkerRel) {
		return dao.findListForBuy(tbEntWorkerRel);
	}
	public List<TbEntWorkerRel> findListForQuit(TbEntWorkerRel tbEntWorkerRel) {
		return dao.findListForQuit(tbEntWorkerRel);
	}
}