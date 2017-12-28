/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.service.registe;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taorg.entity.registe.SysUser;
import com.thinkgem.jeesite.modules.taorg.dao.registe.SysUserDao;

/**
 * 注册功能Service
 * @author Jackiet
 * @version 2017-01-03
 */
@Service
@Transactional(readOnly = true)
public class SysUserService extends CrudService<SysUserDao, SysUser> {

	public SysUser get(String id) {
		return super.get(id);
	}
	
	public List<SysUser> findList(SysUser sysUser) {
		return super.findList(sysUser);
	}
	
	public Page<SysUser> findPage(Page<SysUser> page, SysUser sysUser) {
		return super.findPage(page, sysUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SysUser sysUser) {
		super.save(sysUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysUser sysUser) {
		super.delete(sysUser);
	}
	
}