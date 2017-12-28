/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.worker;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeC;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeM;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbEntWorkerRel;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbInsuranceList;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerInfo;
import com.thinkgem.jeesite.modules.taogr.service.ent.TbEntChargeCService;
import com.thinkgem.jeesite.modules.taogr.service.ent.TbEntChargeMService;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TbInsuranceListDao;
import com.thinkgem.jeesite.modules.taogr.dao.worker.TbWorkerInfoDao;

/**
 * 保单信息Service
 * @author louis
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TbInsuranceListService extends CrudService<TbInsuranceListDao, TbInsuranceList> {

	@Autowired
	private TbWorkerInfoDao tbWorkerInfoDao;
	@Autowired
	private TbWorkerInfoService tTbWorkerInfoService;
	@Autowired
	private TbEntWorkerRelService tbEntWorkerRelService;
	
	@Autowired
	private TbEntChargeCService tbEntChargeCService;
	
	@Autowired
	private TbEntChargeMService tbEntChargeMService;
	
	public TbInsuranceList get(String id) {
		return super.get(id);
	}
	
	public List<TbInsuranceList> findList(TbInsuranceList tbInsuranceList) {
		return super.findList(tbInsuranceList);
	}
	
	public Page<TbInsuranceList> findPage(Page<TbInsuranceList> page, TbInsuranceList tbInsuranceList) {
		return super.findPage(page, tbInsuranceList);
	}
	
	@Transactional(readOnly = false)
	public void save(TbInsuranceList tbInsuranceList) {
		//查询员工是否存在(根据身份证查询)
		String idNo = tbInsuranceList.getIdNo();//身份证
		TbWorkerInfo wi = new TbWorkerInfo();
		wi.setIdNo(idNo);
		TbWorkerInfo workerInfo = tbWorkerInfoDao.findWorkerByOther(wi);
		//保存企业和职工关系
		TbEntWorkerRel tbEntWorkerRel = new TbEntWorkerRel();//企业与职工关系
		if(null==workerInfo){
			TbWorkerInfo tbWorkerInfo = new TbWorkerInfo();
			tbWorkerInfo.setIdNo(idNo);
			tbWorkerInfo.setNameOnce(tbInsuranceList.getWorkerName());
			tbWorkerInfo.setIsActive("1");
			tTbWorkerInfoService.save(tbWorkerInfo);
			
			tbEntWorkerRel.setWorker(tbWorkerInfo);
		}else{
			tbEntWorkerRel.setWorker(workerInfo);
		}
		//查询企业与员工是否存在关系，如果没关系则添加关系表，如果有关系则不需要添加
		tbEntWorkerRel.setEnt(UserUtils.getUser().getEnt());
		List<TbEntWorkerRel> list = tbEntWorkerRelService.findList(tbEntWorkerRel);
		if(list!=null&&list.size()>0){//员工已经是企业里面的
			for(TbEntWorkerRel wr:list ){
				if(wr.getStatus().equals("2")){
					tbEntWorkerRel.setStatus("1");//正常
					tbEntWorkerRelService.save(tbEntWorkerRel);
				}
			}
		}else{
			tbEntWorkerRel.setStatus("1");//正常
			tbEntWorkerRelService.save(tbEntWorkerRel);
		}
		
		super.save(tbInsuranceList);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbInsuranceList tbInsuranceList) {
		super.delete(tbInsuranceList);
	}
	@Transactional(readOnly = false)
	public void updateStatusAudit(TbInsuranceList tbInsuranceList) {
		//1.确认保单
		dao.updateStatusAudit(tbInsuranceList);
		//2.扣除企业里面的费用
		TbInsuranceList ins = dao.get(tbInsuranceList.getId());//查询保单信息
		
		int days = ins.getDays();//购买天数
		String feePer = ins.getFeePer();
		int feeUnit = Integer.parseInt(feePer);//购买单价
		
		BigDecimal a =new BigDecimal(days);
		BigDecimal b =new BigDecimal(feeUnit);
		
		BigDecimal totalFee = a.multiply(b);//小计保费
			//充值总表（扣除余额）
		TbEntChargeM tbEntChargeM  = new TbEntChargeM();
		tbEntChargeM.setOffice(ins.getOffice());
		tbEntChargeM.setFee(totalFee.toString());
		tbEntChargeM.setUpdateBy(UserUtils.getUser());
		tbEntChargeM.setUpdateDate(new Date());
		tbEntChargeMService.updateMFeeByEnt(tbEntChargeM);
			//充值明细表（操作保留）
		TbEntChargeC chargeC =  new TbEntChargeC();
		chargeC.setOffice(ins.getOffice());//企业
		chargeC.setFeeTotal(totalFee.toString());//执行金额
		chargeC.setWorkerCount("1");//人数
		chargeC.setInQuit("1");//进
		chargeC.setAuditStatus("1");//未确认
		tbEntChargeCService.save(chargeC);
		
	}
	@Transactional(readOnly = false)
	public void updateStatusAuditQuit(TbInsuranceList tbInsuranceList) {
		//1.确认退保
		dao.updateStatusAuditQuit(tbInsuranceList);
		//2.扣除企业里面的费用
		TbInsuranceList ins = dao.get(tbInsuranceList.getId());//查询保单信息
		
		long day = 0 ;//天数
		String feePer = ins.getFeePer();
		int feeUnit = Integer.parseInt(feePer);//购买单价
		
		String tDate = ins.getDtQuitInsurance();//退保日期
		String eDate = ins.getDtEnd();//结束日期
		
		try {
			Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(eDate);
			Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse(tDate);
			day = (date1.getTime()-date2.getTime())/(24*60*60*1000)>0 ? (date1.getTime()-date2.getTime())/(24*60*60*1000):
				(date2.getTime()-date1.getTime())/(24*60*60*1000);
			day = day + 1 ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
		BigDecimal a =new BigDecimal(day);
		BigDecimal b =new BigDecimal(feeUnit);
		
		BigDecimal totalFee = a.multiply(b);//小计保费
			//充值总表（添加余额）
		TbEntChargeM tbEntChargeM  = new TbEntChargeM();
		tbEntChargeM.setOffice(ins.getOffice());
		tbEntChargeM.setFee(totalFee.abs().toString());
		tbEntChargeM.setUpdateBy(UserUtils.getUser());
		tbEntChargeM.setUpdateDate(new Date());
		tbEntChargeMService.updateMAddFeeByEnt(tbEntChargeM);
		
			//充值明细表（操作保留）
		TbEntChargeC chargeC =  new TbEntChargeC();
		chargeC.setOffice(ins.getOffice());//企业
		chargeC.setFeeTotal(totalFee.toString());//执行金额
		chargeC.setWorkerCount("1");//人数
		chargeC.setInQuit("2");//退
		chargeC.setAuditStatus("1");//未确认
		tbEntChargeCService.save(chargeC);
	}

	public List<TbInsuranceList> findListForImport(TbInsuranceList tbInsuranceList) {
		return dao.findListForImport(tbInsuranceList);
	}
	
}