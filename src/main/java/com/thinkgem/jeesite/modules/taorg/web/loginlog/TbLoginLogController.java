/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.web.loginlog;

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
import com.thinkgem.jeesite.modules.taorg.entity.loginlog.TbLoginLog;
import com.thinkgem.jeesite.modules.taorg.service.loginlog.TbLoginLogService;

/**
 * 登录日志Controller
 * @author Jackiet
 * @version 2016-12-29
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/loginlog/tbLoginLog")
public class TbLoginLogController extends BaseController {

	@Autowired
	private TbLoginLogService tbLoginLogService;
	
	@ModelAttribute
	public TbLoginLog get(@RequestParam(required=false) String id) {
		TbLoginLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbLoginLogService.get(id);
		}
		if (entity == null){
			entity = new TbLoginLog();
		}
		return entity;
	}
	
	@RequiresPermissions("taorg:loginlog:tbLoginLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbLoginLog tbLoginLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbLoginLog> page = tbLoginLogService.findPage(new Page<TbLoginLog>(request, response), tbLoginLog); 
		model.addAttribute("page", page);
		return "modules/taorg/loginlog/tbLoginLogList";
	}

	@RequiresPermissions("taorg:loginlog:tbLoginLog:view")
	@RequestMapping(value = "form")
	public String form(TbLoginLog tbLoginLog, Model model) {
		model.addAttribute("tbLoginLog", tbLoginLog);
		return "modules/taorg/loginlog/tbLoginLogForm";
	}

	@RequiresPermissions("taorg:loginlog:tbLoginLog:edit")
	@RequestMapping(value = "save")
	public String save(TbLoginLog tbLoginLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbLoginLog)){
			return form(tbLoginLog, model);
		}
		tbLoginLogService.save(tbLoginLog);
		addMessage(redirectAttributes, "保存日志成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/loginlog/tbLoginLog/?repage";
	}
	
	@RequiresPermissions("taorg:loginlog:tbLoginLog:edit")
	@RequestMapping(value = "delete")
	public String delete(TbLoginLog tbLoginLog, RedirectAttributes redirectAttributes) {
		tbLoginLogService.delete(tbLoginLog);
		addMessage(redirectAttributes, "删除日志成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/loginlog/tbLoginLog/?repage";
	}

}