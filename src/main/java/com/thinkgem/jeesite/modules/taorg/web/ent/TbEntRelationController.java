/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.web.ent;

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
import com.thinkgem.jeesite.modules.taorg.entity.ent.TbEntRelation;
import com.thinkgem.jeesite.modules.taorg.service.ent.TbEntRelationService;

/**
 * 企业项目施工队关系Controller
 * @author Jackiet
 * @version 2016-12-19
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/ent/tbEntRelation")
public class TbEntRelationController extends BaseController {

	@Autowired
	private TbEntRelationService tbEntRelationService;
	
	@ModelAttribute
	public TbEntRelation get(@RequestParam(required=false) String id) {
		TbEntRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbEntRelationService.get(id);
		}
		if (entity == null){
			entity = new TbEntRelation();
		}
		return entity;
	}
	@RequiresPermissions("taorg:ent:tbEntRelation:view")
	@RequestMapping(value = {""})
	public String index(TbEntRelation tbEntRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/taorg/ent/tbEntRelationIndex";
	}
	@RequiresPermissions("taorg:ent:tbEntRelation:view")
	@RequestMapping(value = {"list"})
	public String list(TbEntRelation tbEntRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TbEntRelation> list = tbEntRelationService.findList(tbEntRelation); 
		model.addAttribute("list", list);
		return "modules/taorg/ent/tbEntRelationList";
	}

	@RequiresPermissions("taorg:ent:tbEntRelation:view")
	@RequestMapping(value = "form")
	public String form(TbEntRelation tbEntRelation, Model model) {
		if (tbEntRelation.getParent()!=null && StringUtils.isNotBlank(tbEntRelation.getParentId())){
			tbEntRelation.setParent(tbEntRelationService.get(tbEntRelation.getParentId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tbEntRelation.getId())){
				TbEntRelation tbEntRelationChild = new TbEntRelation();
				tbEntRelationChild.setParent(new TbEntRelation(tbEntRelation.getParentId()));
				List<TbEntRelation> list = tbEntRelationService.findList(tbEntRelation); 
				if (list.size() > 0){
					tbEntRelation.setSort(list.get(list.size()-1).getSort());
					if (tbEntRelation.getSort() != null){
						tbEntRelation.setSort(tbEntRelation.getSort() + 30);
					}
				}
			}
		}
		if (tbEntRelation.getSort() == null){
			tbEntRelation.setSort(30);
		}
		model.addAttribute("tbEntRelation", tbEntRelation);
		return "modules/taorg/ent/tbEntRelationForm";
	}
	
	@RequiresPermissions("taorg:ent:tbEntRelation:edit")
	@RequestMapping(value = "save")
	public String save(TbEntRelation tbEntRelation, Model model, RedirectAttributes redirectAttributes) {
/*		if (!beanValidator(model, tbEntRelation)){
			return form(tbEntRelation, model);
		}*/
		tbEntRelationService.save(tbEntRelation);
		addMessage(redirectAttributes, "保存关系成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/ent/tbEntRelation/list?repage";
	}
	
	@RequiresPermissions("taorg:ent:tbEntRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(TbEntRelation tbEntRelation, RedirectAttributes redirectAttributes) {
		tbEntRelationService.delete(tbEntRelation);
		addMessage(redirectAttributes, "删除企项施关系成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/ent/tbEntRelation/list?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TbEntRelation> list = tbEntRelationService.findList(new TbEntRelation());
		for (int i=0; i<list.size(); i++){
			TbEntRelation e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getAllId());
				map.put("pId", e.getParentId());
				map.put("pName", e.getParentName());
				map.put("name", e.getAllName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}