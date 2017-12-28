<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保单信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			jQuery.validator.addMethod("alarmsec",function(value, element) {
				return this.optional(element) || /^[-+]?[0-9]{1,8}(\.[0-9]{0,2})?$/.test(value);
			},"请输入正确的金额，且整数位最多八位数,且只能保留小数点后二位");
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$("#btnSubmit").click(function(){
				if($("#inputForm").valid()){
					var dtStart = $("#dtStart").val();
					 var dtEnd = $("#dtEnd").val();
					 var  exit = $("#dtQuitInsurance").val();
					 
					 var oDate = new Date(); //实例一个时间对象；
					 var mouth = oDate.getMonth()+1;
					 var nows = oDate.getFullYear()+"-"+mouth+"-"+oDate.getDate();
					 
					 var s = new Date(dtStart).getTime(); 
					 var e = new Date(dtEnd).getTime(); 
					 var ex = new Date(exit).getTime(); 
					 
					 var now = new Date(nows).getTime(); 
					 
					 
						if(ex!=s&&ex!=e){
						if(ex<s){
							  jBox.tip("退保日期必须在【开始日期】之后（含当天）！");
							  $("#dtQuitInsurance"). val('');
							  return
						  }
						  /* if(ex>e){
							  jBox.tip("退保日期必须在【结束日期】之前（含当天）！");
							  $("#dtQuitInsurance"). val('');
							  return
						  } */
						}
			/* 			if(ex!=now){
							if(ex<now){
								jBox.tip("退保时间必须在今天之前！");
								  $("#dtQuitInsurance"). val('');
								  return
							}
						} */
					 $("#inputForm").submit();
				}else{
					validator.focusInvalid();
				}
			});
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taogr/worker/tbInsuranceList/">保单信息列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tbInsuranceList/form?id=${tbInsuranceList.id}">执行退保申请</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbInsuranceList" action="${ctx}/taogr/worker/tbInsuranceList/saveExit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		
		<sys:message content="${message}"/>		
		<div class="container-fluid">	
		<div class="tab-pane active" id="panel-base">
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
				<label class="control-label">职工姓名：</label>
				<div class="controls">
					<form:input path="workerName" htmlEscape="false" readonly="true" maxlength="100" class="input-xlarge required"/>
				</div>
			</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">身份证：</label>
			<div class="controls">
				<form:input path="idNo" htmlEscape="false" readonly="true" maxlength="100" class="input-xlarge card required "/>
			</div>
			</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
		<div class="control-group">
			<label class="control-label">保单类型：</label>
			<div class="controls">
			<input type="hidden" name="insuranceTp.id" value="${tbInsuranceList.insuranceTp.id }"/>
			<form:input path="insuranceTp.insName" htmlEscape="false" readonly="true" maxlength="100" class="input-xlarge required "/>
			</div>
		</div>
		</div>
		 <div class="span6">
		<div class="control-group">
			<label class="control-label">保费单价（元）：</label>
			<div class="controls">
			<input type="text" name="feePer" id="feePer" readonly="readonly" value="${tbInsuranceList.feePer/100 }"  maxlength="20" class="input-xlarge alarmsec required"/>
			</div>
		</div>
		</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
		
		<div class="control-group">
			<label class="control-label">开始日期（含当天）：</label>
			<div class="controls">
			<form:input path="dtStart" htmlEscape="false" readonly="true" maxlength="100" class="input-xlarge required "/>
			</div>
		</div>
		</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>退保日期：</label>
			<div class="controls">
				<input id="dtQuitInsurance"  name="dtQuitInsurance" type="text" maxlength="20"
								class="Wdate required"
								value="${tbInsuranceList.dtQuitInsurance}"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taogr:worker:tbInsuranceList:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		</div>
		</div>
	</form:form>
</body>
</html>