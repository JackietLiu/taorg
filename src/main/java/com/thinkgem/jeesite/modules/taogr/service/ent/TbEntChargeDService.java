/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.service.ent;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeD;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeM;
import com.thinkgem.jeesite.modules.taogr.dao.ent.TbEntChargeDDao;

/**
 * 充值明细Service
 * @author louis
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class TbEntChargeDService extends CrudService<TbEntChargeDDao, TbEntChargeD> {

	@Autowired
	private TbEntChargeMService tbEntChargeMService;
	
	public TbEntChargeD get(String id) {
		return super.get(id);
	}
	
	public List<TbEntChargeD> findList(TbEntChargeD tbEntChargeD) {
		return super.findList(tbEntChargeD);
	}
	
	public Page<TbEntChargeD> findPage(Page<TbEntChargeD> page, TbEntChargeD tbEntChargeD) {
		return super.findPage(page, tbEntChargeD);
	}
	
	@Transactional(readOnly = false)
	public void save(TbEntChargeD tbEntChargeD) {
		
		
		//2修改充值总记录
		//①查询总记录表中是不是有企业对应的数据
		TbEntChargeM tbEntChargeM = tbEntChargeMService.getByEneId(tbEntChargeD.getOffice().getId());
		
		if(null==tbEntChargeM){
			tbEntChargeM = new TbEntChargeM();
			tbEntChargeM.setOffice(tbEntChargeD.getOffice());
			tbEntChargeM.setFee(tbEntChargeD.getFee());
			tbEntChargeM.setFeeTotal(tbEntChargeD.getFee());
			tbEntChargeMService.save(tbEntChargeM);
		}else{
			tbEntChargeM.setDFee(tbEntChargeD.getFee());//修改后明细金额
			//新增明细
			if(tbEntChargeD.getIsNewRecord()){
				tbEntChargeM.setOldDFee("0");
				
			}else{//修改明细
				tbEntChargeM.setOldDFee(tbEntChargeD.getOldFee());
			}
			tbEntChargeMService.updateM(tbEntChargeM);
		}
		//1保存明细
		super.save(tbEntChargeD);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbEntChargeD tbEntChargeD) {
		//①查询总记录表中是不是有企业对应的数据
		TbEntChargeM tbEntChargeM = tbEntChargeMService.getByEneId(tbEntChargeD.getOffice().getId());
		
		if(null==tbEntChargeM){
			
		}else{
			int f = Integer.parseInt(tbEntChargeM.getFee());//原余额
			int ft = Integer.parseInt(tbEntChargeM.getFeeTotal());//原总金额
			
			int cf = Integer.parseInt(tbEntChargeD.getFee());//充值金额
			
			BigDecimal a =new BigDecimal(f);
			BigDecimal c =new BigDecimal(ft);
			
			BigDecimal b =new BigDecimal(cf);
			
			tbEntChargeM.setFee(a.subtract(b)+"");
			tbEntChargeM.setFeeTotal(c.subtract(b)+"");
			tbEntChargeMService.save(tbEntChargeM);
		}
		super.delete(tbEntChargeD);
		
	}
	
	@Transactional(readOnly = false)
	public void toUpdateStatus(TbEntChargeD tbEntChargeD) {
		dao.toUpdateStatus(tbEntChargeD);
	}
	
}