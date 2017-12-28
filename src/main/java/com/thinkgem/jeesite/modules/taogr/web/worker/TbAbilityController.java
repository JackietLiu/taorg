/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.web.worker;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbAbility;
import com.thinkgem.jeesite.modules.taogr.service.worker.TbAbilityService;

/**
 * 工种Controller
 * @author louis
 * @version 2016-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/worker/tbAbility")
public class TbAbilityController extends BaseController {

	@Autowired
	private TbAbilityService tbAbilityService;
	
	@ModelAttribute
	public TbAbility get(@RequestParam(required=false) String id) {
		TbAbility entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbAbilityService.get(id);
		}
		if (entity == null){
			entity = new TbAbility();
		}
		return entity;
	}
	
	@RequiresPermissions("taogr:worker:tbAbility:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbAbility tbAbility, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TbAbility> list = tbAbilityService.findList(tbAbility); 
		model.addAttribute("list", list);
		return "modules/taogr/worker/tbAbilityList";
	}

	@RequiresPermissions("taogr:worker:tbAbility:view")
	@RequestMapping(value = "form")
	public String form(TbAbility tbAbility, Model model) {
		if (tbAbility.getParent()!=null && StringUtils.isNotBlank(tbAbility.getParent().getId())){
			tbAbility.setParent(tbAbilityService.get(tbAbility.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tbAbility.getId())){
				TbAbility tbAbilityChild = new TbAbility();
				tbAbilityChild.setParent(new TbAbility(tbAbility.getParent().getId()));
				List<TbAbility> list = tbAbilityService.findList(tbAbility); 
				if (list.size() > 0){
					tbAbility.setSort(list.get(list.size()-1).getSort());
					if (tbAbility.getSort() != null){
						tbAbility.setSort(tbAbility.getSort() + 30);
					}
				}
			}
		}
		if (tbAbility.getSort() == null){
			tbAbility.setSort(30);
		}
		model.addAttribute("tbAbility", tbAbility);
		return "modules/taogr/worker/tbAbilityForm";
	}

	@RequiresPermissions("taogr:worker:tbAbility:edit")
	@RequestMapping(value = "save")
	public String save(TbAbility tbAbility, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbAbility)){
			return form(tbAbility, model);
		}
		tbAbilityService.save(tbAbility);
		addMessage(redirectAttributes, "保存工种成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbAbility/?repage";
	}
	
	@RequiresPermissions("taogr:worker:tbAbility:edit")
	@RequestMapping(value = "delete")
	public String delete(TbAbility tbAbility, RedirectAttributes redirectAttributes) {
		tbAbilityService.delete(tbAbility);
		addMessage(redirectAttributes, "删除工种成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbAbility/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TbAbility> list = tbAbilityService.findList(new TbAbility());
		for (int i=0; i<list.size(); i++){
			TbAbility e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getAbName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}