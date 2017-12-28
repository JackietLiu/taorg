/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.taogr.web.worker;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import com.thinkgem.jeesite.modules.sys.service.*;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IsNumber;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TbInsuranceList;
import com.thinkgem.jeesite.modules.taogr.entity.worker.TdInsuranceType;
import com.thinkgem.jeesite.modules.taogr.service.worker.TbInsuranceListService;
import com.thinkgem.jeesite.modules.taogr.service.worker.TdInsuranceTypeService;

/**
 * 保单信息Controller
 * @author louis
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/taogr/worker/tbInsuranceList")
public class TbInsuranceListController extends BaseController {

	@Autowired
	private TbInsuranceListService tbInsuranceListService;

	@Autowired
	private TdInsuranceTypeService tdInsuranceTypeService;

	@Autowired
	private OfficeService officeService;
	@ModelAttribute
	public TbInsuranceList get(@RequestParam(required=false) String id) {
		TbInsuranceList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbInsuranceListService.get(id);
		}
		if (entity == null){
			entity = new TbInsuranceList();
		}
		return entity;
	}

	@RequiresPermissions("taogr:worker:tbInsuranceList:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbInsuranceList tbInsuranceList, HttpServletRequest request, HttpServletResponse response, Model model) {

		//查询该企业下面的所有员工
		User user = UserUtils.getUser();
		if(StringUtils.isBlank(user.getUserType())){
			//查询不到任何数据
			Office info = new Office();
			info.setId("0");
			tbInsuranceList.setOffice(info);
		}else if("1".equals(user.getUserType())){//系统管理员
			//查询到所有数据
		}else if("2".equals(user.getUserType())){//平台管理员
			//查询该公司下面的数据
		}else if("3".equals(user.getUserType())){//企业管理员
			//查询该公司下面的数据
			tbInsuranceList.setOffice(user.getOffice());
		}else{//其他都差不多数据
			Office info = new Office();
			info.setId("0");
			tbInsuranceList.setOffice(info);
		}

		Page<TbInsuranceList> page = tbInsuranceListService.findPage(new Page<TbInsuranceList>(request, response), tbInsuranceList); 
		//险种
		TdInsuranceType ins = new TdInsuranceType();
		ins.setIsActive("1");
		List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(ins);

		model.addAttribute("page", page);
		model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		return "modules/taogr/worker/tbInsuranceListList";
	}

	/**
	 * 未确认的进保单列表
	 * @param tbInsuranceList
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:view")
	@RequestMapping(value = "noConfirmList")
	public String noConfirmList(TbInsuranceList tbInsuranceList, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询该企业下面的所有员工
		User user = UserUtils.getUser();
		if(StringUtils.isBlank(user.getUserType())){
			//查询不到任何数据
			Office info = new Office();
			info.setId("0");
			tbInsuranceList.setOffice(info);
		}else if("1".equals(user.getUserType())){//系统管理员
			//查询到所有数据
		}else if("2".equals(user.getUserType())){//平台管理员
			//查询该公司下面的数据
		}else if("3".equals(user.getUserType())){//企业管理员
			//查询该公司下面的数据
			tbInsuranceList.setOffice(user.getOffice());
		}else{//其他都差不多数据
			Office info = new Office();
			info.setId("0");
			tbInsuranceList.setOffice(info);
		}
		tbInsuranceList.setStatusAudit("1");//未确认保单
		tbInsuranceList.setStatusAuditQuit("1");//退保未确认

		Page<TbInsuranceList> page = tbInsuranceListService.findPage(new Page<TbInsuranceList>(request, response), tbInsuranceList); 
		//险种
		TdInsuranceType ins = new TdInsuranceType();
		ins.setIsActive("1");
		List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(ins);

		model.addAttribute("page", page);
		model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		return "modules/taogr/worker/tbInsuranceListNoConList";
	}

	/**
	 * 未确认的退保单列表
	 * @param tbInsuranceList
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:view")
	@RequestMapping(value = "noConfirmExitList")
	public String noConfirmExitList(TbInsuranceList tbInsuranceList, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询该企业下面的所有员工
		User user = UserUtils.getUser();
		if(StringUtils.isBlank(user.getUserType())){
			//查询不到任何数据
			Office info = new Office();
			info.setId("0");
			tbInsuranceList.setOffice(info);
		}else if("1".equals(user.getUserType())){//系统管理员
			//查询到所有数据
		}else if("2".equals(user.getUserType())){//平台管理员
			//查询该公司下面的数据
		}else if("3".equals(user.getUserType())){//企业管理员
			//查询该公司下面的数据
			tbInsuranceList.setOffice(user.getOffice());
		}else{//其他都差不多数据
			Office info = new Office();
			info.setId("0");
			tbInsuranceList.setOffice(info);
		}
		tbInsuranceList.setInQuit("2");//退保
		tbInsuranceList.setStatusAuditQuit("1");//退保未确认

		Page<TbInsuranceList> page = tbInsuranceListService.findPage(new Page<TbInsuranceList>(request, response), tbInsuranceList); 
		//险种
		TdInsuranceType ins = new TdInsuranceType();
		ins.setIsActive("1");
		List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(ins);

		model.addAttribute("page", page);
		model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		return "modules/taogr/worker/tbInsuranceListNoConExitList";
	}

	@RequiresPermissions("taogr:worker:tbInsuranceList:view")
	@RequestMapping(value = "form")
	public String form(TbInsuranceList tbInsuranceList, Model model) {
		TdInsuranceType ins = new TdInsuranceType();
		ins.setIsActive("1");
		List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(ins);
		model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		model.addAttribute("tbInsuranceList", tbInsuranceList);
		return "modules/taogr/worker/tbInsuranceListForm";
	}
	/* *
	 * 进入退保申请页面
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:outApply")
	@RequestMapping(value = "formExit")
	public String formExit(TbInsuranceList tbInsuranceList, Model model) {
		TdInsuranceType ins = new TdInsuranceType();
		ins.setIsActive("1");
		List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(ins);
		model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		model.addAttribute("tbInsuranceList", tbInsuranceList);
		return "modules/taogr/worker/tbInsuranceListFormExit";
	}

	@RequiresPermissions("taogr:worker:tbInsuranceList:edit")
	@RequestMapping(value = "save")
	public String save(TbInsuranceList tbInsuranceList, Model model, RedirectAttributes redirectAttributes,HttpRequest request) {
		if (!beanValidator(model, tbInsuranceList)){
			return form(tbInsuranceList, model);
		}
		if (tbInsuranceList.getIsNewRecord()){//添加时，需要保存企业ID
			tbInsuranceList.setOffice(UserUtils.getUser().getCompany());

		}
		tbInsuranceListService.save(tbInsuranceList);
		addMessage(redirectAttributes, "保存保单信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/?repage";
	}
	/* *
	 * 退保申请操作
	 * @param tbInsuranceList
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:edit")
	@RequestMapping(value = "saveExit")
	public String saveExit(TbInsuranceList tbInsuranceList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbInsuranceList)){
			return formExit(tbInsuranceList, model);
		}
		tbInsuranceList.setInQuit("2");
		tbInsuranceList.setUserCreateQuit(UserUtils.getUser());
		tbInsuranceList.setDtQuitCreate(DateUtils.getDateTime());
		tbInsuranceListService.save(tbInsuranceList);
		addMessage(redirectAttributes, "保存保单信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/?repage";
	}

	@RequiresPermissions("taogr:worker:tbInsuranceList:edit")
	@RequestMapping(value = "delete")
	public String delete(TbInsuranceList tbInsuranceList, RedirectAttributes redirectAttributes) {
		tbInsuranceListService.delete(tbInsuranceList);
		addMessage(redirectAttributes, "删除保单信息成功");
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/?repage";
	}

	@RequiresPermissions("taogr:worker:tbInsuranceList:confirmIn")
	@RequestMapping(value = "updateStatusAudit")
	public String updateStatusAudit(TbInsuranceList tbInsuranceList, RedirectAttributes redirectAttributes) {
		if("2".equals(tbInsuranceList.getStatusAudit())){
			tbInsuranceList.setUserAudit(UserUtils.getUser());//确认人
			tbInsuranceList.setDtAudit(DateUtils.getDateTime());//确认时间
			tbInsuranceList.setDtEffect(DateUtils.getDate()+"  00:00:00");//生效时间

			addMessage(redirectAttributes, "确认保单成功！");
		}
		tbInsuranceListService.updateStatusAudit(tbInsuranceList);
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/noConfirmList?repage";
	}
	/**
	 * 批量确认保单
	 * @param tbInsuranceList
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:confirmIn")
	@RequestMapping(value = "updateStatusAuditBatch")
	public String updateStatusAuditBatch( RedirectAttributes redirectAttributes ,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		if(StringUtils.isNotBlank(ids)){
			TbInsuranceList tbInsuranceList = new TbInsuranceList();
			tbInsuranceList.setUserAudit(UserUtils.getUser());//确认人
			tbInsuranceList.setDtAudit(DateUtils.getDateTime());//确认时间
			tbInsuranceList.setDtStart(DateUtils.getDate());    //生效日期
			tbInsuranceList.setStatusAudit("2");
			for(String id : ids.split(",")){
				tbInsuranceList.setId(id);
				tbInsuranceListService.updateStatusAudit(tbInsuranceList);
			}
			addMessage(redirectAttributes, "确认保单成功！");
		}else{
			addMessage(redirectAttributes, "请选择数据后，执行确认保单！");
		}

		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/noConfirmList?repage";
	}

	@RequiresPermissions("taogr:worker:tbInsuranceList:confirmOut")
	@RequestMapping(value = "updateStatusAuditQuit")
	public String updateStatusAuditQuit(TbInsuranceList tbInsuranceList, RedirectAttributes redirectAttributes) {
		if("2".equals(tbInsuranceList.getStatusAuditQuit())){		
			tbInsuranceList.setUserAuditQuit(UserUtils.getUser());			
			tbInsuranceList.setDtAuditQuit(DateUtils.getDate());
			addMessage(redirectAttributes, "确认保单成功！");
		}
		tbInsuranceListService.updateStatusAuditQuit(tbInsuranceList);
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/noConfirmExitList?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:export")
	@RequestMapping(value = "export", method=RequestMethod.POST)
	public String export(TbInsuranceList tbInsuranceList,HttpServletResponse response, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		try {
			String fileName = "保单数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";   
			// 查询数据  
			//List<TbInsuranceList> list = Lists.newArrayList();
			
			tbInsuranceList.setOffice(tbInsuranceList.getOffice());
			Page<TbInsuranceList> page = tbInsuranceListService.findPage(new Page<TbInsuranceList>(request, response,-1), tbInsuranceList); 
			new ExportExcel("保单数据", TbInsuranceList.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;  
		} catch (Exception e) {  
			//addMessage("导出用户失败！失败信息："+e.getMessage());  
			addMessage(redirectAttributes, "导出保单失败！失败信息："+e.getMessage());
		}  
		return "modules/taogr/worker/tbInsuranceListList";
	}
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:export")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TbInsuranceList> list = ei.getDataList(TbInsuranceList.class);
			for (TbInsuranceList ins : list){
				try{
					String idNo = ins.getIdNo();
					if(StringUtils.isBlank(idNo)){
						failureMsg.append("<br/>职工身份证 "+ins.getIdNo()+" 没填。 ");
						failureNum++;
					}else{
						if(IsNumber.isNumber(idNo)){//身份证数字转换
							BigDecimal db = new BigDecimal(idNo);
							String str = db.toPlainString();
							ins.setIdNo(str);
						}
						//保费
						String feePer = ins.getFeePer();
						if(!StringUtils.isBlank(feePer)){
							if(IsNumber.isNumber(feePer)){
								BigDecimal db = new BigDecimal(feePer);
								String str = db.toPlainString();
								ins.setFeePer(str);
							}
						}
						//查询数据是不是已经保存到数据了
						List<TbInsuranceList> insList = tbInsuranceListService.findListForImport(ins);
						if(insList!=null&&insList.size()>0){
							failureMsg.append("<br/>职工 "+ins.getWorkerName()+" 已经导入 ");
							failureNum++;
						}else{
							//根据险种no，查询险种
							String insNo = ins.getInsNo();
							TdInsuranceType tdInsuranceType  = new TdInsuranceType();
							tdInsuranceType.setInsNo(insNo);
							TdInsuranceType insType = tdInsuranceTypeService.findInsTypeByNo(insNo);
							if(null!=insType){
								ins.setInsuranceTp(insType);
							}
							//企业编号
							ins.setOffice(UserUtils.getUser().getOffice());
							tbInsuranceListService.save(ins);//保存
							successNum++;
						}
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>职工 "+ins.getWorkerName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>职工 "+ins.getWorkerName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条保单，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条保单"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入保单失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/taogr/worker/tbInsuranceList/?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("taogr:worker:tbInsuranceList:export")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "insuranceDemo.xlsx";
			List<TbInsuranceList> list = Lists.newArrayList(); 

			TbInsuranceList tbInsuranceList = new TbInsuranceList();
			tbInsuranceList.setWorkerName("张三");
			tbInsuranceList.setIdNo("431381198901176911");

			tbInsuranceList.setInsNo("XZ0001");
			tbInsuranceList.setFeePer("5");
			tbInsuranceList.setDtStart("2016-11-04");
			tbInsuranceList.setDtEnd("2016-12-04");

			list.add(tbInsuranceList);
			new ExportExcel("保单数据", TbInsuranceList.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 批量选取员工买保单
	 * @param tbInsuranceList
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveIns")
	@ResponseBody
	public Map<String,Object> saveIns(Model model,
			RedirectAttributes redirectAttributes ,HttpServletRequest request) {
		String workerIds = request.getParameter("workerIds");
		String workerNames = request.getParameter("workerNames");
		String idNos = request.getParameter("idNos");
		String insuranceTps = request.getParameter("insuranceTps");
		String feePers = request.getParameter("feePers");
		String sDates = request.getParameter("sDates");
		String eDates = request.getParameter("eDates");
		String entIds = request.getParameter("entIds");

		for(String workid : workerIds.split(",")){
			TbInsuranceList tbInsuranceList = new TbInsuranceList();

			for(String entId : entIds.split(",")){//企业
				if(StringUtils.isNoneBlank(entId)){
					if(entId.split("\\|")[0].equals(workid)){
						String[] strs = entId.split("\\|") ;
						if(strs.length>1){
							String str = entId.split("\\|")[1];
							tbInsuranceList.setEntId(str);
						}

					}
				}
			}
			for(String wName : workerNames.split(",")){//职工姓名
				if(StringUtils.isNoneBlank(wName)){
					if(wName.split("\\|")[0].equals(workid)){
						String[] strs = wName.split("\\|") ;
						if(strs.length>1){
							String str = wName.split("\\|")[1];
							tbInsuranceList.setWorkerName(str);
						}

					}
				}
			}

			for(String idNo : idNos.split(",")){//职工身份证
				if(StringUtils.isNoneBlank(idNo)){
					if(idNo.split("\\|")[0].equals(workid)){
						String[] strs = idNo.split("\\|") ;
						if(strs.length>1){
							String str = idNo.split("\\|")[1];
							tbInsuranceList.setIdNo(str);
						}

					}
				}
			}

			for(String insTp : insuranceTps.split(",")){//险种
				if(StringUtils.isNoneBlank(insTp)){
					if(insTp.split("\\|")[0].equals(workid)){
						String[] strs = insTp.split("\\|") ;
						if(strs.length>1){
							String str = insTp.split("\\|")[1];
							TdInsuranceType insuranceTp = new TdInsuranceType();
							insuranceTp.setId(str);
							tbInsuranceList.setInsuranceTp(insuranceTp);
						}

					}
				}
			}

			for(String feePer : feePers.split(",")){//保费
				if(StringUtils.isNoneBlank(feePer)){
					if(feePer.split("\\|")[0].equals(workid)){
						String[] strs = feePer.split("\\|") ;
						if(strs.length>1){
							String str = feePer.split("\\|")[1];
							tbInsuranceList.setFeePer(str);
						}

					}
				}
			}

			for(String sDate : sDates.split(",")){//开始日期
				if(StringUtils.isNoneBlank(sDate)){
					if(sDate.split("\\|")[0].equals(workid)){
						String[] strs = sDate.split("\\|") ;
						if(strs.length>1){
							String str = sDate.split("\\|")[1];
							tbInsuranceList.setDtStart(str);
						}

					}
				}
			}

			for(String eDate : eDates.split(",")){//结束日期
				if(StringUtils.isNoneBlank(eDate)){
					if(eDate.split("\\|")[0].equals(workid)){
						String[] strs = eDate.split("\\|") ;
						if(strs.length>1){
							String str = eDate.split("\\|")[1];
							tbInsuranceList.setDtEnd(str);
							tbInsuranceList.setDtQuitInsurance(str);
						}

					}
				}
			}
			tbInsuranceListService.save(tbInsuranceList);
		}

		Map<String,Object> result = Maps.newHashMap();
		result.put("success", true);
		return result;
	}
	/**
	 * 批量选取员工退保
	 * @param tbInsuranceList
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveQuit")
	@ResponseBody
	public Map<String,Object> saveQuit(Model model,
			RedirectAttributes redirectAttributes ,HttpServletRequest request) {
		String workerIds = request.getParameter("workerIds");
		String workerNames = request.getParameter("workerNames");
		String idNos = request.getParameter("idNos");
		/*		String insuranceTps = request.getParameter("insuranceTps");
		String feePers = request.getParameter("feePers");
		String sDates = request.getParameter("sDates");*/
		String eDates = request.getParameter("eDates");

		for(String workid : workerIds.split(",")){
			TbInsuranceList tbInsuranceList = new TbInsuranceList();
			tbInsuranceList.setOffice(UserUtils.getUser().getOffice());//企业

			for(String wName : workerNames.split(",")){//职工姓名
				if(StringUtils.isNoneBlank(wName)){
					if(wName.split("\\|")[0].equals(workid)){
						String[] strs = wName.split("\\|") ;
						if(strs.length>1){
							String str = wName.split("\\|")[1];
							tbInsuranceList.setWorkerName(str);
						}

					}
				}
			}

			for(String idNo : idNos.split(",")){//职工身份证
				if(StringUtils.isNoneBlank(idNo)){
					if(idNo.split("\\|")[0].equals(workid)){
						String[] strs = idNo.split("\\|") ;
						if(strs.length>1){
							String str = idNo.split("\\|")[1];
							tbInsuranceList.setIdNo(str);
						}

					}
				}
			}

			for(String eDate : eDates.split(",")){//结束日期
				if(StringUtils.isNoneBlank(eDate)){
					if(eDate.split("\\|")[0].equals(workid)){
						String[] strs = eDate.split("\\|") ;
						if(strs.length>1){
							String str = eDate.split("\\|")[1];
							tbInsuranceList.setDtEnd(str);
						}

					}
				}
			}
			tbInsuranceListService.save(tbInsuranceList);
		}

		Map<String,Object> result = Maps.newHashMap();
		result.put("success", true);
		return result;
	}
	/**
	 * 保单详情
	 * @param tbInsuranceList
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(TbInsuranceList tbInsuranceList, Model model) {
		TdInsuranceType ins = new TdInsuranceType();
		ins.setIsActive("1");
		List<TdInsuranceType> tdInsuranceTypeList = tdInsuranceTypeService.findAll(ins);
		model.addAttribute("tdInsuranceTypeList", tdInsuranceTypeList);
		model.addAttribute("tbInsuranceList", tbInsuranceList);
		return "modules/taogr/worker/tbInsuranceListDetail";
	}

}