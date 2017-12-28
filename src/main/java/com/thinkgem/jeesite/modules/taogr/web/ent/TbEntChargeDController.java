/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.web.ent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeD;
import com.thinkgem.jeesite.modules.taogr.service.ent.TbEntChargeDService;

/**
 * 充值明细Controller
 * @author louis
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/ent/tbEntChargeD")
public class TbEntChargeDController extends BaseController {

	@Autowired
	private TbEntChargeDService tbEntChargeDService;
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public TbEntChargeD get(@RequestParam(required=false) String id) {
		TbEntChargeD entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbEntChargeDService.get(id);
		}
		if (entity == null){
			entity = new TbEntChargeD();
		}
		return entity;
	}
	
	@RequiresPermissions("taogr:ent:tbEntChargeD:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbEntChargeD tbEntChargeD, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbEntChargeD> page = tbEntChargeDService.findPage(new Page<TbEntChargeD>(request, response), tbEntChargeD); 
		model.addAttribute("page", page);
		return "modules/taogr/ent/tbEntChargeDList";
	}

	@RequiresPermissions("taogr:ent:tbEntChargeD:view")
	@RequestMapping(value = "form")
	public String form(TbEntChargeD tbEntChargeD, Model model) {
		if(tbEntChargeD!=null&&StringUtils.isBlank(tbEntChargeD.getId())&&!StringUtils.isBlank(tbEntChargeD.getOffice().getId())){
			Office office = officeService.get(tbEntChargeD.getOffice().getId());
			
			if(null!=office &&!StringUtils.isBlank(office.getName())){
				model.addAttribute("nameAdd", office.getName());
			}
		}
		model.addAttribute("tbEntChargeD", tbEntChargeD);
		return "modules/taogr/ent/tbEntChargeDForm";
	}
	@RequiresPermissions("taogr:ent:tbEntChargeD:view")
	@RequestMapping(value = "toAddChargeD")
	public String toAddChargeD(TbEntChargeD tbEntChargeD, Model model) {
		model.addAttribute("tbEntChargeD", tbEntChargeD);
		return "modules/taogr/ent/toChargeDForm";
	}

	@RequiresPermissions("taogr:ent:tbEntChargeD:edit")
	@RequestMapping(value = "save")
	public String save(TbEntChargeD tbEntChargeD, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbEntChargeD)){
			return form(tbEntChargeD, model);
		}
		System.out.println("---------------------");
		System.out.println(tbEntChargeD.getOffice().getId());
		if(null==tbEntChargeD.getOffice()||StringUtils.isBlank(tbEntChargeD.getOffice().getId())){
			addMessage(redirectAttributes, "充值不成功，请重新充值！");
			return "redirect:"+Global.getAdminPath()+"/taorg/office/sysOffice/?repage";
		}

		tbEntChargeDService.save(tbEntChargeD);
		addMessage(redirectAttributes, "保存充值明细成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEntChargeD/?repage&office.id="+tbEntChargeD.getOffice().getId();
	}
	
	@RequiresPermissions("taogr:ent:tbEntChargeD:edit")
	@RequestMapping(value = "delete")
	public String delete(TbEntChargeD tbEntChargeD, RedirectAttributes redirectAttributes) {
		tbEntChargeDService.delete(tbEntChargeD);
		addMessage(redirectAttributes, "删除充值明细成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEntChargeD/?repage&office.id="+tbEntChargeD.getOffice().getId();
	}

	@RequiresPermissions("taogr:ent:tbEntChargeD:edit")
	@RequestMapping(value = "toUpdateStatus")
	public String toUpdateStatus(TbEntChargeD tbEntChargeD, Model model, RedirectAttributes redirectAttributes) {
		tbEntChargeD.setStatusAudit("2");
		tbEntChargeD.setUserAudit(UserUtils.getUser());
		tbEntChargeD.setDtAudit(DateUtils.getDateTime());
		tbEntChargeDService.toUpdateStatus(tbEntChargeD);
		addMessage(redirectAttributes, "确认充值明细成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEntChargeD/?repage&office.id="+tbEntChargeD.getOffice().getId();
	}
}