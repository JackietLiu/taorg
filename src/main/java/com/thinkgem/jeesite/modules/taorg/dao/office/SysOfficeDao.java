/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.dao.office;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.taorg.entity.office.SysOffice;

/**
 * 企业/机构DAO接口
 * @author Jackiet
 * @version 2017-01-26
 */
@MyBatisDao
public interface SysOfficeDao extends TreeDao<SysOffice> {
	
	List<SysOffice> findListByOfficeId(SysOffice sysOffice);
}