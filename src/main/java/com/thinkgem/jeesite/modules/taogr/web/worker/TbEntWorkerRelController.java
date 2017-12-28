/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.web.worker;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbEntWorkerRel;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TdInsuranceType;
import com.thinkgem.jeesite.modules.taogr.service.worker.TbEntWorkerRelService;
import com.thinkgem.jeesite.modules.taogr.service.worker.TdInsuranceTypeService;

/**
 * 企业信息和职工关系Controller
 * @author louis
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/worker/tbEntWorkerRel")
public class TbEntWorkerRelController extends BaseController {

	@Autowired
	private TbEntWorkerRelService tbEntWorkerRelService;

	@Autowired
	private TdInsuranceTypeService tdInsuranceTypeService;
	
	@ModelAttribute
	public TbEntWorkerRel get(@RequestParam(required=false) String id) {
		TbEntWorkerRel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbEntWorkerRelService.get(id);
		}
		if (entity == null){
			entity = new TbEntWorkerRel();
		}
		return entity;
	}
	
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbEntWorkerRel tbEntWorkerRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询该企业下面的所有员工
		User user = UserUtils.getUser();
		if(StringUtils.isBlank(user.getUserType())){
			//查询不到任何数据
			Office info = new Office();
			info.setId("0");
			tbEntWorkerRel.setOffice(info);
		}else if("1".equals(user.getUserType())){//系统管理员
			 //查询到所有数据
		}else if("2".equals(user.getUserType())){//平台管理员
			//查询到所有数据
		}else if("3".equals(user.getUserType())){//企业管理员
			//查询该公司下面的数据
			tbEntWorkerRel.setOffice(user.getCompany());
		}else if("4".equals(user.getUserType())){//普通员工
			//只能查看自己的信息
			
		}else{//其他都差不多数据
			Office info = new Office();
			info.setId("0");
			tbEntWorkerRel.setOffice(info);
		}
		
		
		Page<TbEntWorkerRel> page = tbEntWorkerRelService.findPage(new Page<TbEntWorkerRel>(request, response), tbEntWorkerRel); 
		model.addAttribute("page", page);
		return "modules/taogr/worker/tbEntWorkerRelList";
	}

	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = "form")
	public String form(TbEntWorkerRel tbEntWorkerRel, Model model) {
		model.addAttribute("tbEntWorkerRel", tbEntWorkerRel);
		return "modules/taogr/worker/tbEntWorkerRelForm";
	}
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = "index")
	public String index(TbEntWorkerRel tbWorkerInfo, Model model) {

		return "modules/taogr/ent/searchIndex";
	}
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = "abilityIndex")
	public String abilityIndex(TbEntWorkerRel tbWorkerInfo, Model model) {

		return "modules/taogr/ent/searchAbility";
	}
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:edit")
	@RequestMapping(value = "save")
	public String save(TbEntWorkerRel tbEntWorkerRel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbEntWorkerRel)){
			return form(tbEntWorkerRel, model);
		}
		tbEntWorkerRelService.save(tbEntWorkerRel);
		addMessage(redirectAttributes, "保存企业和职工关系成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbEntWorkerRel/list?repage";
	}
	
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:edit")
	@RequestMapping(value = "delete")
	public String delete(TbEntWorkerRel tbEntWorkerRel, RedirectAttributes redirectAttributes) {
		tbEntWorkerRelService.delete(tbEntWorkerRel);
		addMessage(redirectAttributes, "删除企业和职工关系成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbEntWorkerRel/list?repage";
	}
	
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(TbEntWorkerRel tbEntWorkerRel, RedirectAttributes redirectAttributes) {
		System.out.println(tbEntWorkerRel.getStatus());
		if("1".equals(tbEntWorkerRel.getStatus())){
			tbEntWorkerRel.setStatus("2");
			addMessage(redirectAttributes, "解除企业和职工关系成功");
		}else if("2".equals(tbEntWorkerRel.getStatus())){
			tbEntWorkerRel.setStatus("1");
			addMessage(redirectAttributes, "恢复企业和职工关系成功");
		}
		tbEntWorkerRelService.updateStatus(tbEntWorkerRel);
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbEntWorkerRel/list?repage";
	}

	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = "listByWorkerId")
	public String listByWorkerId(TbEntWorkerRel tbEntWorkerRel, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String workids = request.getParameter("workids");
		List<TbEntWorkerRel> tbEntWorkerRelList = new ArrayList<TbEntWorkerRel>();
		if(!StringUtils.isBlank(workids)){
			String [] workidss = workids.split(",");
			List<String> strList = new ArrayList<String>();
			for(String workid : workidss){
				if(!StringUtils.isBlank(workid)){
					//System.out.println(workid);
					strList.add(workid);
				}
			}
			tbEntWorkerRel.setStrList(strList);
			System.out.println(strList); 
			//查询该企业下面的所有员工
/*			User user = UserUtils.getUser();
			tbEntWorkerRel.setOffice(user.getCompany());*/
			
			tbEntWorkerRelList = tbEntWorkerRelService.findListForBuy(tbEntWorkerRel);
			
			List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(new TdInsuranceType());
			model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		}
		model.addAttribute("tbEntWorkerRelList", tbEntWorkerRelList);
		return "modules/taogr/worker/tbEntWorkerRelListForIns";
	}
	@RequiresPermissions("taogr:worker:tbEntWorkerRel:view")
	@RequestMapping(value = "listByWorkerIds")
	public String listByWorkerIds(TbEntWorkerRel tbEntWorkerRel, HttpServletRequest request,
			HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
/*		if (tbInsuranceList.getId() == null) {
			addMessage(redirectAttributes, "已过滤未购买保险 ");
		}*/		
		String workids = request.getParameter("workids");
		List<TbEntWorkerRel> tbEntWorkerRelList = new ArrayList<TbEntWorkerRel>();
		if(!StringUtils.isBlank(workids)){
			String [] workidss = workids.split(",");
			List<String> strList = new ArrayList<String>();
			for(String workid : workidss){
				if(!StringUtils.isBlank(workid)){
					strList.add(workid);
				}
			}
			tbEntWorkerRel.setStrList(strList);
			//查询该企业下面的所有员工
			User user = UserUtils.getUser();
			tbEntWorkerRel.setOffice(user.getCompany());
			tbEntWorkerRelList = tbEntWorkerRelService.findListForQuit(tbEntWorkerRel);
			
			List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(new TdInsuranceType());
			model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		}
		model.addAttribute("tbEntWorkerRelList", tbEntWorkerRelList);
		return "modules/taogr/worker/tbEntWorkerRelListForQuit";
	}
	
}