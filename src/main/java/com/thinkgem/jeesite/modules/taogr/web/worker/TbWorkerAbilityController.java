/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.web.worker;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerAbility;
import com.thinkgem.jeesite.modules.taogr.service.worker.TbWorkerAbilityService;

/**
 * 工人技能信息Controller
 * @author louis
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/worker/tbWorkerAbility")
public class TbWorkerAbilityController extends BaseController {

	@Autowired
	private TbWorkerAbilityService tbWorkerAbilityService;
	
	@ModelAttribute
	public TbWorkerAbility get(@RequestParam(required=false) String id) {
		TbWorkerAbility entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbWorkerAbilityService.get(id);
		}
		if (entity == null){
			entity = new TbWorkerAbility();
		}
		return entity;
	}
	
	@RequiresPermissions("taogr:worker:tbWorkerAbility:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbWorkerAbility tbWorkerAbility, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbWorkerAbility> page = tbWorkerAbilityService.findPage(new Page<TbWorkerAbility>(request, response), tbWorkerAbility); 
		model.addAttribute("page", page);
		return "modules/taogr/worker/tbWorkerAbilityList";
	}

	@RequiresPermissions("taogr:worker:tbWorkerAbility:view")
	@RequestMapping(value = "form")
	public String form(TbWorkerAbility tbWorkerAbility, Model model) {
		model.addAttribute("tbWorkerAbility", tbWorkerAbility);
		return "modules/taogr/worker/tbWorkerAbilityForm";
	}

	@RequiresPermissions("taogr:worker:tbWorkerAbility:edit")
	@RequestMapping(value = "save")
	public String save(TbWorkerAbility tbWorkerAbility, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbWorkerAbility)){
			return form(tbWorkerAbility, model);
		}
		if("1".equals(tbWorkerAbility.getIsAuth())){//如果没有证书
			tbWorkerAbility.setAuthNo("");
			tbWorkerAbility.setAuthImage("");
		}
		tbWorkerAbilityService.save(tbWorkerAbility);
		addMessage(redirectAttributes, "保存工人技能信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbWorkerAbility/?repage&worker.id="+tbWorkerAbility.getWorker().getId();
	}
	
	@RequiresPermissions("taogr:worker:tbWorkerAbility:edit")
	@RequestMapping(value = "delete")
	public String delete(TbWorkerAbility tbWorkerAbility, RedirectAttributes redirectAttributes) {
		tbWorkerAbilityService.delete(tbWorkerAbility);
		addMessage(redirectAttributes, "删除工人技能信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbWorkerAbility/?repage&worker.id="+tbWorkerAbility.getWorker().getId();
	}

}