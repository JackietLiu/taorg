/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.web.office;

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
import com.thinkgem.jeesite.modules.taorg.entity.office.SysOffice;
import com.thinkgem.jeesite.modules.taorg.service.office.SysOfficeService;

/**
 * 企业/机构Controller
 * @author Jackiet
 * @version 2017-01-26
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/office/sysOffice")
public class SysOfficeController extends BaseController {

	@Autowired
	private SysOfficeService sysOfficeService;
	
	@ModelAttribute
	public SysOffice get(@RequestParam(required=false) String id) {
		SysOffice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysOfficeService.get(id);
		}
		if (entity == null){
			entity = new SysOffice();
		}
		return entity;
	}
	
	@RequiresPermissions("taorg:office:sysOffice:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysOffice sysOffice, HttpServletRequest request, HttpServletResponse response, Model model) {
		//List<SysOffice> list = sysOfficeService.findList(sysOffice); 
		  User user = UserUtils.getUser();
	      String type = user.getUserType();
	      String offType = user.getOffice().getType();
	      
		  String id = null;
			if ("1".equals(offType)) {
				id = user.getOffice().getId();
			}else if ("2".equals(offType)) {
				id = user.getOffice().getParentId();
			}else{
				id = user.getOffice().getParentId();
			}
		 
		  List<SysOffice> oList = null;
		  if ("3".equals(type)) {

			sysOffice.setCompanyId(id);
			oList = sysOfficeService.findListByOfficeId(sysOffice);
		} else if ("2".equals(type)) {	
			sysOffice.setCompanyId(id);
			oList = sysOfficeService.findListByOfficeId(sysOffice);
		}else {
			oList = sysOfficeService.findList(sysOffice);
		}
		  
		model.addAttribute("list", oList);
		return "modules/taorg/office/sysOfficeList";
	}

	@RequiresPermissions("taorg:office:sysOffice:view")
	@RequestMapping(value = "form")
	public String form(SysOffice sysOffice, Model model) {
		if (sysOffice.getParent()!=null && StringUtils.isNotBlank(sysOffice.getParent().getId())){
			sysOffice.setParent(sysOfficeService.get(sysOffice.getParent().getId()));
	/*		// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sysOffice.getId())){
				SysOffice sysOfficeChild = new SysOffice();
				sysOfficeChild.setParent(new SysOffice(sysOffice.getParent().getId()));
				List<SysOffice> list = sysOfficeService.findList(sysOffice); 
				if (list.size() > 0){
					sysOffice.setSort(list.get(list.size()-1).getSort());
					if (sysOffice.getSort() != null){
						sysOffice.setSort(sysOffice.getSort() + 30);
					}
				}
			}*/
		}
		if (sysOffice.getSort() == null){
			sysOffice.setSort(30);
		}
		model.addAttribute("sysOffice", sysOffice);
		return "modules/taorg/office/sysOfficeForm";
	}

	@RequiresPermissions("taorg:office:sysOffice:edit")
	@RequestMapping(value = "save")
	public String save(SysOffice sysOffice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysOffice)){
			return form(sysOffice, model);
		}
		if (StringUtils.isBlank(sysOffice.getId())){
			SysOffice sysOfficeChild = new SysOffice();
			sysOfficeChild.setParent(new SysOffice(sysOffice.getParent().getId()));
			List<SysOffice> list = sysOfficeService.findList(sysOffice); 
			if (list.size() > 0){
				sysOffice.setSort(list.get(list.size()-1).getSort());
				if (sysOffice.getSort() != null){
					sysOffice.setSort(sysOffice.getSort() + 30);
				}
			}
		}
	
	if (sysOffice.getSort() == null){
		sysOffice.setSort(30);
	}
		sysOfficeService.save(sysOffice);
		addMessage(redirectAttributes, "保存企业/部门成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/office/sysOffice/?repage";
	}
	
	@RequiresPermissions("taorg:office:sysOffice:edit")
	@RequestMapping(value = "delete")
	public String delete(SysOffice sysOffice, RedirectAttributes redirectAttributes) {
		sysOfficeService.delete(sysOffice);
		addMessage(redirectAttributes, "删除企业/部门成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/office/sysOffice/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SysOffice> list = sysOfficeService.findList(new SysOffice());
		for (int i=0; i<list.size(); i++){
			SysOffice e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}