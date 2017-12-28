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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taorg.entity.worker.TbInsurance;
import com.thinkgem.jeesite.modules.taorg.service.worker.TbInsuranceService;

/**
 * 在保人员查询Controller
 * @author jackiet
 * @version 2016-11-04
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/worker/tbInsurance")
public class TbInsuranceController extends BaseController {

	@Autowired
	private TbInsuranceService tbInsuranceService;
	
	@ModelAttribute
	public TbInsurance get(@RequestParam(required=false) String id) {
		TbInsurance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbInsuranceService.get(id);
		}
		if (entity == null){
			entity = new TbInsurance();
		}
		return entity;
	}
	
	@RequiresPermissions("taorg:worker:tbInsurance:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbInsurance tbInsurance, HttpServletRequest request, HttpServletResponse response, Model model) {	
		User user = UserUtils.getUser();
		tbInsurance.setStatusAudit("2");
		tbInsurance.setStatusAuditQuit("1");
		tbInsurance.setInQuit("1");
		if("3".equals(user.getUserType())){//企业管理员
			//查询该公司下面的数据
			tbInsurance.setOffice(user.getCompany());
		}
		Page<TbInsurance> page = tbInsuranceService.findPage(new Page<TbInsurance>(request, response), tbInsurance); 
		model.addAttribute("page", page);	
		return "modules/taorg/worker/tbInsuranceList";
	}

	@RequiresPermissions("taorg:worker:tbInsurance:view")
	@RequestMapping(value = "form")
	public String form(TbInsurance tbInsurance, Model model) {
		model.addAttribute("tbInsuranceList", tbInsurance);
		//如何在此获取值

		return "modules/taorg/worker/tbInsuranceForm";
	}

	@RequiresPermissions("taorg:worker:tbInsurance:edit")
	@RequestMapping(value = "save")
	public String save(TbInsurance tbInsuranceList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbInsuranceList)){
			return form(tbInsuranceList, model);
		}
		tbInsuranceService.save(tbInsuranceList);
		addMessage(redirectAttributes, "保存在保人员成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/worker/tbInsurance/?repage";
	}
	
	@RequiresPermissions("taorg:worker:tbInsurance:edit")
	@RequestMapping(value = "delete")
	public String delete(TbInsurance tbInsurance, RedirectAttributes redirectAttributes) {
		tbInsuranceService.delete(tbInsurance);
		addMessage(redirectAttributes, "删除在保人员成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/worker/tbInsurance/?repage";
	}

}