/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taorg.web.registe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.RoleDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.ent.TbEnterpriseInfo;
import com.thinkgem.jeesite.modules.taogr.service.ent.TbEnterpriseInfoService;
import com.thinkgem.jeesite.modules.taorg.entity.registe.SysUser;
import com.thinkgem.jeesite.modules.taorg.service.registe.SysUserService;

/**
 * 注册功能Controller
 * @author Jackiet
 * @version 2017-01-03
 */
@Controller
@RequestMapping(value = "${adminPath}/taorg/registe/sysUser")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TbEnterpriseInfoService tbEnterpriseInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private RoleDao roleDao;

	
	
	@ModelAttribute
	public SysUser get(@RequestParam(required=false) String id) {
		SysUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysUserService.get(id);
		}
		if (entity == null){
			entity = new SysUser();
		}
		return entity;
	}
	
/*	@RequiresPermissions("taorg:registe:sysUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysUser sysUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysUser> page = sysUserService.findPage(new Page<SysUser>(request, response), sysUser); 
		model.addAttribute("page", page);
		return "modules/taorg/registe/sysUserList";
	}*/

	/*@RequiresPermissions("taorg:registe:sysUser:view")*/
	@RequestMapping(value = "form")
	public String form(SysUser user, Model model) {
		model.addAttribute("user", user);
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		TbEnterpriseInfo tbEnterpriseInfo = new TbEnterpriseInfo();
		List<TbEnterpriseInfo> listEnt = tbEnterpriseInfoService.findAllList(tbEnterpriseInfo);
		
		List<Office> listOffice = officeDao.findAllList(new Office());
			model.addAttribute("listOffice", listOffice);
		   
		/*List<> listEnt = tbEnterpriseInfoService.findAllList(tbEnterpriseInfo);*/
		
		model.addAttribute("user", user);
		model.addAttribute("listEnt", listEnt);
		model.addAttribute("allRoles", roleDao.findAllList(new Role()));		
		return "modules/taorg/registe/sysUserForm";
	}

/*	@RequiresPermissions("taorg:registe:sysUser:edit")*/
	/*	@RequestMapping(value = "save")
	public String save(SysUser sysUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysUser)){
			return form(sysUser, model);
		}
		sysUser.setLoginDate(DateUtils.parseDate(new Date()));
		sysUser.setLoginFlag("0");
		sysUserService.save(sysUser);
		addMessage(redirectAttributes, "保存注册信息成功");
		return "redirect:"+Global.getAdminPath();
	}*/

	@RequestMapping(value = "save")
	public String save(SysUser user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		TbEnterpriseInfo tbEnterpriseInfo = new TbEnterpriseInfo();
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		user.setUserType("4");
		user.setLoginFlag("1");
		sysUserService.save(user);
		// 角色数据有效性验证，过滤不在授权内的角色
/*		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		System.out.println(user.getRoleList());
		// 保存用户信息
		systemService.saveUser(user);*/
		if (user.getLogoPath() != null && user.getLogoPath() != "") {

	        String logoPath = user.getLogoPath();
	        tbEnterpriseInfo.setId(user.getId());
			tbEnterpriseInfo.setLogoPath(logoPath);
//			tbEnterpriseInfoService.save(tbEnterpriseInfo);	
		}
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		// addMessage(redirectAttributes, "用户'" + user.getLoginName() + "'注册成功,请在审核后使用该账户登录");
		return "redirect:" + adminPath + "/login";
	}

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody

	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	
	
/*	@RequiresPermissions("taorg:registe:sysUser:edit")*/
	@RequestMapping(value = "delete")
	public String delete(SysUser sysUser, RedirectAttributes redirectAttributes) {
		sysUserService.delete(sysUser);
		addMessage(redirectAttributes, "删除注册信息成功");
		return "redirect:"+Global.getAdminPath()+"/taorg/registe/sysUser/list?repage";
	}

}