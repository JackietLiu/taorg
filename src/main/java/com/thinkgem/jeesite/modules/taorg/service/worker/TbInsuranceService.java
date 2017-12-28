/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbInsurance;
import com.thinkgem.jeesite.modules.taorg.dao.worker.TbInsuranceDao;

/**
 * 在保人员查询Service
 * @author jackiet
 * @version 2016-11-04
 */
@Service
@Transactional(readOnly = true)
public class TbInsuranceService extends CrudService<TbInsuranceDao, TbInsurance> {

	public TbInsurance get(String id) {
		return super.get(id);
	}
	
	public List<TbInsurance> findList(TbInsurance tbInsurance) {
		return super.findList(tbInsurance);
	}
	
	public Page<TbInsurance> findPage(Page<TbInsurance> page, TbInsurance tbInsurance) {
		return super.findPage(page, tbInsurance);
	}
	
	@Transactional(readOnly = false)
	public void save(TbInsurance tbInsurance) {
		super.save(tbInsurance);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbInsurance tbInsurance) {
		super.delete(tbInsurance);
	}
	
}