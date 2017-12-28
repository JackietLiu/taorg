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
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEntChargeC;
import com.thinkgem.jeesite.modules.taogr.service.ent.TbEntChargeCService;

/**
 * 企业费用变更明细Controller
 * @author louis
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/ent/tbEntChargeC")
public class TbEntChargeCController extends BaseController {

	@Autowired
	private TbEntChargeCService tbEntChargeCService;
	
	@ModelAttribute
	public TbEntChargeC get(@RequestParam(required=false) String id) {
		TbEntChargeC entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbEntChargeCService.get(id);
		}
		if (entity == null){
			entity = new TbEntChargeC();
		}
		return entity;
	}
	
	@RequiresPermissions("taogr:ent:tbEntChargeC:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbEntChargeC tbEntChargeC, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbEntChargeC> page = tbEntChargeCService.findPage(new Page<TbEntChargeC>(request, response), tbEntChargeC); 
		model.addAttribute("page", page);
		return "modules/taogr/ent/tbEntChargeCList";
	}

	@RequiresPermissions("taogr:ent:tbEntChargeC:view")
	@RequestMapping(value = "form")
	public String form(TbEntChargeC tbEntChargeC, Model model) {
		model.addAttribute("tbEntChargeC", tbEntChargeC);
		return "modules/taogr/ent/tbEntChargeCForm";
	}

	@RequiresPermissions("taogr:ent:tbEntChargeC:edit")
	@RequestMapping(value = "save")
	public String save(TbEntChargeC tbEntChargeC, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbEntChargeC)){
			return form(tbEntChargeC, model);
		}
		tbEntChargeCService.save(tbEntChargeC);
		addMessage(redirectAttributes, "保存费用变更明细成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEntChargeC/?repage";
	}
	
	@RequiresPermissions("taogr:ent:tbEntChargeC:edit")
	@RequestMapping(value = "delete")
	public String delete(TbEntChargeC tbEntChargeC, RedirectAttributes redirectAttributes) {
		tbEntChargeCService.delete(tbEntChargeC);
		addMessage(redirectAttributes, "删除费用变更明细成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEntChargeC/?repage";
	}
	
	@RequiresPermissions("taogr:ent:tbEntChargeC:edit")
	@RequestMapping(value = "toUpdateStatus")
	public String toUpdateStatus(TbEntChargeC tbEntChargeC, Model model, RedirectAttributes redirectAttributes) {
		tbEntChargeC.setAuditStatus("2");
		tbEntChargeC.setUserAudit(UserUtils.getUser());
		tbEntChargeC.setDtAudit(DateUtils.getDateTime());
		tbEntChargeCService.toUpdateStatus(tbEntChargeC); 
		addMessage(redirectAttributes, "确认费用变更成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEntChargeC/?repage&ent.id="+tbEntChargeC.getOffice().getId();
	}

}