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

import com.thinkgem.jeesite.common.autocom.AjaxUtil;
import com.thinkgem.jeesite.common.autocom.BaseResult;
import com.thinkgem.jeesite.common.autocom.ResultMgr;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TdInsuranceType;
import com.thinkgem.jeesite.modules.taogr.service.worker.TdInsuranceTypeService;

/**
 * 保险类型信息Controller
 * @author louis
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/worker/tdInsuranceType")
public class TdInsuranceTypeController extends BaseController {

	@Autowired
	private TdInsuranceTypeService tdInsuranceTypeService;
	
	@ModelAttribute
	public TdInsuranceType get(@RequestParam(required=false) String id) {
		TdInsuranceType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdInsuranceTypeService.get(id);
		}
		if (entity == null){
			entity = new TdInsuranceType();
		}
		return entity;
	}
	
	@RequiresPermissions("taogr:worker:tdInsuranceType:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdInsuranceType tdInsuranceType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdInsuranceType> page = tdInsuranceTypeService.findPage(new Page<TdInsuranceType>(request, response), tdInsuranceType); 
		model.addAttribute("page", page);
		return "modules/taogr/worker/tdInsuranceTypeList";
	}

	@RequiresPermissions("taogr:worker:tdInsuranceType:view")
	@RequestMapping(value = "form")
	public String form(TdInsuranceType tdInsuranceType, Model model) {
		model.addAttribute("tdInsuranceType", tdInsuranceType);
		return "modules/taogr/worker/tdInsuranceTypeForm";
	}

	@RequiresPermissions("taogr:worker:tdInsuranceType:edit")
	@RequestMapping(value = "save")
	public String save(TdInsuranceType tdInsuranceType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdInsuranceType)){
			return form(tdInsuranceType, model);
		}
		tdInsuranceTypeService.save(tdInsuranceType);
		addMessage(redirectAttributes, "保存保险类型信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tdInsuranceType/?repage";
	}
	
	@RequiresPermissions("taogr:worker:tdInsuranceType:edit")
	@RequestMapping(value = "delete")
	public String delete(TdInsuranceType tdInsuranceType, RedirectAttributes redirectAttributes) {
		tdInsuranceTypeService.delete(tdInsuranceType);
		addMessage(redirectAttributes, "删除保险类型信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tdInsuranceType/?repage";
	}
	
	@RequestMapping(value = {"getInSuranCeType"})
	public void getInSuranCeType(HttpServletRequest request, HttpServletResponse response, Model model) {
		try
        {
			String id = request.getParameter("id");
			TdInsuranceType tdInsuranceType = tdInsuranceTypeService.get(id);
			BaseResult lr = new BaseResult();
	   		lr.setSuccess(true);
	   		lr.setResult(tdInsuranceType);
	   	    ResultMgr rtMgr = new ResultMgr(lr, "json");
	   	    String re = rtMgr.convertResult();
            // 反馈到客户端
            AjaxUtil.sendResponseJsonText(response, re);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}
	
}