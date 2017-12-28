/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.ent;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeC;
import com.thinkgem.jeesite.modules.taogr.dao.ent.TbEntChargeCDao;

/**
 * 企业费用变更明细Service
 * @author louis
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TbEntChargeCService extends CrudService<TbEntChargeCDao, TbEntChargeC> {

	public TbEntChargeC get(String id) {
		return super.get(id);
	}
	
	public List<TbEntChargeC> findList(TbEntChargeC tbEntChargeC) {
		return super.findList(tbEntChargeC);
	}
	
	public Page<TbEntChargeC> findPage(Page<TbEntChargeC> page, TbEntChargeC tbEntChargeC) {
		return super.findPage(page, tbEntChargeC);
	}
	
	@Transactional(readOnly = false)
	public void save(TbEntChargeC tbEntChargeC) {
		super.save(tbEntChargeC);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbEntChargeC tbEntChargeC) {
		super.delete(tbEntChargeC);
	}
	@Transactional(readOnly = false)
	public void toUpdateStatus(TbEntChargeC tbEntChargeC) {
		dao.toUpdateStatus(tbEntChargeC);
	}
	
}