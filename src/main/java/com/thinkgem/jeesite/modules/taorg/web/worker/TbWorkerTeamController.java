/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.web.worker;

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
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbWorkerTeam;
import com.thinkgem.jeesite.modules.taorg.service.worker.TbWorkerTeamService;

/**
 * 施工队Controller
 * @author Jackiet
 * @version 2016-12-20
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/worker/tbWorkerTeam")
public class TbWorkerTeamController extends BaseController {

	@Autowired
	private TbWorkerTeamService tbWorkerTeamService;
	
	@ModelAttribute
	public TbWorkerTeam get(@RequestParam(required=false) String id) {
		TbWorkerTeam entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbWorkerTeamService.get(id);
		}
		if (entity == null){
			entity = new TbWorkerTeam();
		}
		return entity;
	}
	
	@RequiresPermissions("taorg:worker:tbWorkerTeam:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbWorkerTeam tbWorkerTeam, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbWorkerTeam> page = tbWorkerTeamService.findPage(new Page<TbWorkerTeam>(request, response), tbWorkerTeam); 
		model.addAttribute("page", page);
		return "modules/taorg/worker/tbWorkerTeamList";
	}

	@RequiresPermissions("taorg:worker:tbWorkerTeam:view")
	@RequestMapping(value = "form")
	public String form(TbWorkerTeam tbWorkerTeam, Model model) {
		model.addAttribute("tbWorkerTeam", tbWorkerTeam);
		return "modules/taorg/worker/tbWorkerTeamForm";
	}

	@RequiresPermissions("taorg:worker:tbWorkerTeam:edit")
	@RequestMapping(value = "save")
	public String save(TbWorkerTeam tbWorkerTeam, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbWorkerTeam)){
			return form(tbWorkerTeam, model);
		}
		tbWorkerTeamService.save(tbWorkerTeam);
		addMessage(redirectAttributes, "保存施工队成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/worker/tbWorkerTeam/?repage";
	}
	
	@RequiresPermissions("taorg:worker:tbWorkerTeam:edit")
	@RequestMapping(value = "delete")
	public String delete(TbWorkerTeam tbWorkerTeam, RedirectAttributes redirectAttributes) {
		tbWorkerTeamService.delete(tbWorkerTeam);
		addMessage(redirectAttributes, "删除施工队成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/worker/tbWorkerTeam/?repage";
	}

}