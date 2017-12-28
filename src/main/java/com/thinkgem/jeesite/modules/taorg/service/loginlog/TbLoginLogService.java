/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.loginlog;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taorg.entity.loginlog.TbLoginLog;
import com.thinkgem.jeesite.modules.taorg.dao.loginlog.TbLoginLogDao;

/**
 * 登录日志Service
 * @author Jackiet
 * @version 2016-12-29
 */
@Service
@Transactional(readOnly = true)
public class TbLoginLogService extends CrudService<TbLoginLogDao, TbLoginLog> {

	public TbLoginLog get(String id) {
		return super.get(id);
	}
	
	public List<TbLoginLog> findList(TbLoginLog tbLoginLog) {
		return super.findList(tbLoginLog);
	}
	
	public Page<TbLoginLog> findPage(Page<TbLoginLog> page, TbLoginLog tbLoginLog) {
		return super.findPage(page, tbLoginLog);
	}
	
	@Transactional(readOnly = false)
	public void save(TbLoginLog tbLoginLog) {
		super.save(tbLoginLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbLoginLog tbLoginLog) {
		super.delete(tbLoginLog);
	}
	
}