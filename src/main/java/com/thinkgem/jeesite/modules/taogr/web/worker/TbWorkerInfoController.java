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
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbWorkerInfo;
import com.thinkgem.jeesite.modules.taogr.service.worker.TbWorkerInfoService;

/**
 * 工人信息Controller
 * @author louis
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/worker/tbWorkerInfo")
public class TbWorkerInfoController extends BaseController {

	@Autowired
	private TbWorkerInfoService tbWorkerInfoService;
	
	@ModelAttribute
	public TbWorkerInfo get(@RequestParam(required=false) String id) {
		TbWorkerInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbWorkerInfoService.get(id);
		}
		if (entity == null){
			entity = new TbWorkerInfo();
		}
		return entity;
	}
	
/*	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = {""})
	public String index(TbWorkerInfo tbWorkerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/taogr/ent/searchIndex";
	}*/
	@RequiresPermissions("taogr:worker:tbWorkerInfo:view")
	@RequestMapping(value = {"list",""})
	public String list(TbWorkerInfo tbWorkerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbWorkerInfo> page = tbWorkerInfoService.findPage(new Page<TbWorkerInfo>(request, response), tbWorkerInfo); 
		model.addAttribute("page", page);
		return "modules/taogr/worker/tbWorkerInfoList";
	}

	@RequiresPermissions("taogr:worker:tbWorkerInfo:view")
	@RequestMapping(value = "form")
	public String form(TbWorkerInfo tbWorkerInfo, Model model) {
		model.addAttribute("tbWorkerInfo", tbWorkerInfo);
		return "modules/taogr/worker/tbWorkerInfoForm";
	}

	@RequiresPermissions("taogr:worker:tbWorkerInfo:edit")
	@RequestMapping(value = "save")
	public String save(TbWorkerInfo tbWorkerInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbWorkerInfo)){
			return form(tbWorkerInfo, model);
		}
		tbWorkerInfoService.save(tbWorkerInfo);
		addMessage(redirectAttributes, "保存工人信息成功");
		//return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbWorkerInfo/?repage";
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbEntWorkerRel/?repage";
	}
	
	@RequiresPermissions("taogr:worker:tbWorkerInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TbWorkerInfo tbWorkerInfo, RedirectAttributes redirectAttributes) {
		tbWorkerInfoService.delete(tbWorkerInfo);
		addMessage(redirectAttributes, "删除工人信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbWorkerInfo/?repage";
	}

}