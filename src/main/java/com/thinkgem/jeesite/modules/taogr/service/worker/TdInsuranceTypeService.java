/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TdInsuranceType;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TdInsuranceTypeDao;

/**
 * 保险类型信息Service
 * @author louis
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TdInsuranceTypeService extends CrudService<TdInsuranceTypeDao, TdInsuranceType> {

	public TdInsuranceType get(String id) {
		return super.get(id);
	}
	
	public List<TdInsuranceType> findList(TdInsuranceType tdInsuranceType) {
		return super.findList(tdInsuranceType);
	}
	
	public Page<TdInsuranceType> findPage(Page<TdInsuranceType> page, TdInsuranceType tdInsuranceType) {
		return super.findPage(page, tdInsuranceType);
	}
	
	@Transactional(readOnly = false)
	public void save(TdInsuranceType tdInsuranceType) {
		super.save(tdInsuranceType);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdInsuranceType tdInsuranceType) {
		super.delete(tdInsuranceType);
	}

	public List<TdInsuranceType> findAll(TdInsuranceType tdInsuranceType) {
		return dao.findAllList(tdInsuranceType);
	}

	public TdInsuranceType findInsTypeByNo(String insNo) {
		return dao.findInsTypeByNo(insNo);
	}
	
}