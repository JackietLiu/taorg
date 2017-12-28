/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.web.pro;

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
import com.thinkgem.jeesite.modules.taorg.entity.pro.TbWorkProjects;
import com.thinkgem.jeesite.modules.taorg.service.pro.TbWorkProjectsService;

/**
 * 工程项目Controller
 * @author Jackiet
 * @version 2016-12-14
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/pro/tbWorkProjects")
public class TbWorkProjectsController extends BaseController {

	@Autowired
	private TbWorkProjectsService tbWorkProjectsService;
	
	@ModelAttribute
	public TbWorkProjects get(@RequestParam(required=false) String id) {
		TbWorkProjects entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbWorkProjectsService.get(id);
		}
		if (entity == null){
			entity = new TbWorkProjects();
		}
		return entity;
	}
	
/*	@RequiresPermissions("taorg:pro:tbWorkProjects:view")
	@RequestMapping(value = {""})
	public String index(TbWorkProjects tbWorkProjects, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/taorg/pro/tbWorkProjectsIndex";
	}*/
	@RequiresPermissions("taorg:pro:tbWorkProjects:view")
	@RequestMapping(value = {"list",""})
	public String list(TbWorkProjects tbWorkProjects, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TbWorkProjects> list = tbWorkProjectsService.findList(tbWorkProjects); 
		model.addAttribute("list", list);
		return "modules/taorg/pro/tbWorkProjectsList";
	}

	@RequiresPermissions("taorg:pro:tbWorkProjects:view")
	@RequestMapping(value = "form")
	public String form(TbWorkProjects tbWorkProjects, Model model) {
		if (tbWorkProjects.getParent()!=null && StringUtils.isNotBlank(tbWorkProjects.getParent().getId())){
			tbWorkProjects.setParent(tbWorkProjectsService.get(tbWorkProjects.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tbWorkProjects.getId())){
				TbWorkProjects tbWorkProjectsChild = new TbWorkProjects();
				tbWorkProjectsChild.setParent(new TbWorkProjects(tbWorkProjects.getParent().getId()));
				List<TbWorkProjects> list = tbWorkProjectsService.findList(tbWorkProjects); 
				if (list.size() > 0){
					tbWorkProjects.setSort(list.get(list.size()-1).getSort());
					if (tbWorkProjects.getSort() != null){
						tbWorkProjects.setSort(tbWorkProjects.getSort() + 30);
					}
				}
			}
		}
		if (tbWorkProjects.getSort() == null){
			tbWorkProjects.setSort(30);
		}
		model.addAttribute("tbWorkProjects", tbWorkProjects);
		return "modules/taorg/pro/tbWorkProjectsForm";
	}

	@RequiresPermissions("taorg:pro:tbWorkProjects:edit")
	@RequestMapping(value = "save")
	public String save(TbWorkProjects tbWorkProjects, Model model, RedirectAttributes redirectAttributes) {
/*		if (!beanValidator(model, tbWorkProjects)){
			return form(tbWorkProjects, model);
		}*/
		tbWorkProjects.setParent(new TbWorkProjects("0"));
		tbWorkProjectsService.save(tbWorkProjects);
		addMessage(redirectAttributes, "保存工程项目成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/pro/tbWorkProjects/list?repage";
		//return "redirect:"+Global.getAdminPath()+"/taorg/ent/tbEntRelation/list?repage";
	}
	
	@RequiresPermissions("taorg:pro:tbWorkProjects:edit")
	@RequestMapping(value = "delete")
	public String delete(TbWorkProjects tbWorkProjects, RedirectAttributes redirectAttributes) {
		tbWorkProjectsService.delete(tbWorkProjects);
		addMessage(redirectAttributes, "删除工程项目成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/pro/tbWorkProjects/list?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TbWorkProjects> list = tbWorkProjectsService.findList(new TbWorkProjects());
		for (int i=0; i<list.size(); i++){
			TbWorkProjects e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getProName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}