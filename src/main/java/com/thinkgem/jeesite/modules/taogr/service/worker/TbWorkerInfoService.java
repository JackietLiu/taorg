/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.worker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbEntWorkerRel;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerInfo;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TbWorkerInfoDao;

/**
 * 工人信息Service
 * @author louis
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TbWorkerInfoService extends CrudService<TbWorkerInfoDao, TbWorkerInfo> {

	@Autowired
	private TbEntWorkerRelService tbEntWorkerRelService;
	
	public TbWorkerInfo get(String id) {
		return super.get(id);
	}
	
	public List<TbWorkerInfo> findList(TbWorkerInfo tbWorkerInfo) {
		return super.findList(tbWorkerInfo);
	}
	
	public Page<TbWorkerInfo> findPage(Page<TbWorkerInfo> page, TbWorkerInfo tbWorkerInfo) {
		return super.findPage(page, tbWorkerInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TbWorkerInfo tbWorkerInfo) {
		//查询员工是否存在(根据身份证查询)
		String idNo = tbWorkerInfo.getIdNo();//身份证
		TbWorkerInfo wi = new TbWorkerInfo();
		String officeid = tbWorkerInfo.getOffice().getId();
		System.out.println("officeID"+officeid);
		wi.setIdNo(idNo);
	    //TbWorkerInfo workerInfo = dao.findWorkerByOther(wi);
		//保存企业和职工关系
		TbEntWorkerRel tbEntWorkerRel = new TbEntWorkerRel();//企业与职工关系
		super.save(tbWorkerInfo);
		tbEntWorkerRel.setWorker(tbWorkerInfo);
		//查询企业与员工是否存在关系，如果没关系则添加关系表，如果有关系则不需要添加
		List<TbEntWorkerRel> list = tbEntWorkerRelService.findList(tbEntWorkerRel);
		if(list!=null && list.size()>0){//员工已经是企业里面的
			System.out.println(list.size()+"list");
		}else{
			tbEntWorkerRel.setStatus("1");//正常
			tbEntWorkerRel.setEntId(officeid);
			tbEntWorkerRelService.save(tbEntWorkerRel);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbWorkerInfo tbWorkerInfo) {
		super.delete(tbWorkerInfo);
	}

	public TbWorkerInfo findWorkerByOther(TbWorkerInfo tbWorkerInfo) {
		return dao.findWorkerByOther(tbWorkerInfo);
	}
	
}