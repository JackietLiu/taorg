/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.web.ent;

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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEnterpriseInfo;
import com.thinkgem.jeesite.modules.taogr.service.ent.TbEnterpriseInfoService;


/**
 * 企业信息Controller
 * @author louis
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/ent/tbEnterpriseInfo")
public class TbEnterpriseInfoController extends BaseController {

	@Autowired
	private TbEnterpriseInfoService tbEnterpriseInfoService;
	@ModelAttribute
	public TbEnterpriseInfo get(@RequestParam(required=false) String id) {
		TbEnterpriseInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbEnterpriseInfoService.get(id);
		}
		if (entity == null){
			entity = new TbEnterpriseInfo();
		}
		return entity;
	}

/*	@RequiresPermissions("taogr:ent:tbEnterpriseInfo:view")
	@RequestMapping(value = {""})
	public String index(TbEnterpriseInfo tbEnterpriseInfo, Model model) {

		return "modules/taorg/pro/tbWorkProjectsIndex";
	}*/
	
	@RequiresPermissions("taogr:ent:tbEnterpriseInfo:view")
	@RequestMapping(value = {"list",""})
	public String list(TbEnterpriseInfo tbEnterpriseInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		//控制当前用户所在企业只能访问企业及以下部门
         User user = UserUtils.getUser();
         String type = user.getUserType();
         String id = user.getEnt().getId();
         List<TbEnterpriseInfo> list = null;
         System.out.println("userType=="+type+"id=="+id);
         if ("3".equals(type)) {
            
            tbEnterpriseInfo.setEntId(id);
		    list = tbEnterpriseInfoService.findListByEntId(tbEnterpriseInfo);
		} else if ("2".equals(type)) {
			
			 tbEnterpriseInfo.setEntId(id);
			    list = tbEnterpriseInfoService.findListByEntId(tbEnterpriseInfo);		
		}else {
			list = tbEnterpriseInfoService.findList(tbEnterpriseInfo);
		}
       
		model.addAttribute("list", list);
		return "modules/taogr/ent/tbEnterpriseInfoList";
	}

	@RequiresPermissions("taogr:ent:tbEnterpriseInfo:view")
	@RequestMapping(value = "form")
	public String form(TbEnterpriseInfo tbEnterpriseInfo, Model model) {
		if (tbEnterpriseInfo.getParent()!=null && StringUtils.isNotBlank(tbEnterpriseInfo.getParent().getId())){
			tbEnterpriseInfo.setParent(tbEnterpriseInfoService.get(tbEnterpriseInfo.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tbEnterpriseInfo.getId())){
				TbEnterpriseInfo tbEnterpriseInfoChild = new TbEnterpriseInfo();
				tbEnterpriseInfoChild.setParent(new TbEnterpriseInfo(tbEnterpriseInfo.getParent().getId()));
				List<TbEnterpriseInfo> list = tbEnterpriseInfoService.findList(tbEnterpriseInfo); 
				if (list.size() > 0){
					tbEnterpriseInfo.setSort(list.get(list.size()-1).getSort());
					if (tbEnterpriseInfo.getSort() != null){
						tbEnterpriseInfo.setSort(tbEnterpriseInfo.getSort() + 30);
					}
				}
			}
		}
		if (tbEnterpriseInfo.getSort() == null){
			tbEnterpriseInfo.setSort(30);
		}
		model.addAttribute("tbEnterpriseInfo", tbEnterpriseInfo);
		return "modules/taogr/ent/tbEnterpriseInfoForm";
	}

	@RequiresPermissions("taogr:ent:tbEnterpriseInfo:edit")
	@RequestMapping(value = "save")
	public String save(TbEnterpriseInfo tbEnterpriseInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbEnterpriseInfo)){
			return form(tbEnterpriseInfo, model);
		}
		tbEnterpriseInfoService.save(tbEnterpriseInfo);
        
		addMessage(redirectAttributes, "保存企业信息成功");				
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEnterpriseInfo/?repage";
	}
	
	@RequiresPermissions("taogr:ent:tbEnterpriseInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TbEnterpriseInfo tbEnterpriseInfo, RedirectAttributes redirectAttributes) {
		tbEnterpriseInfoService.delete(tbEnterpriseInfo);
		addMessage(redirectAttributes, "删除企业信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/ent/tbEnterpriseInfo/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TbEnterpriseInfo> list = tbEnterpriseInfoService.findList(new TbEnterpriseInfo());
		for (int i=0; i<list.size(); i++){
			TbEnterpriseInfo e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId",e.getParentId());
				map.put("pIds",e.getParentIds());
				map.put("name", e.getEntName());
				if(type !=null && "3".equals(type)) {
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}