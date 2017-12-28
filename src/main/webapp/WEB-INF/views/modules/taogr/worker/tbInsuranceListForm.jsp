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
					 var now = new Date().getTime();
					 
					 var s = new Date(dtStart).getTime(); 
					 var e = new Date(dtEnd).getTime(); 
							
						  if(now>s){
							  jBox.tip("开始日期必须在今天之后！");
							  $("#dtStart"). val('');
							  return
						  }
						  if(now>e){
							  jBox.tip("结束日期必须在今天之后！");
							  $("#dtEnd"). val('');
							  return
						  }
					 if(s>e){
						 jBox.tip("结束日期必须在大于开始时间！");
						  $("#dtEnd"). val('');
						  return
					 }
					 $("#inputForm").submit();
				}else{
					validator.focusInvalid();
				}
			});
		});
		function changeTp(){
			var tpId = $("#insuranceTp").val();
			if(tpId==''){
				$("#feePer").val("0");
			}else{
				$.ajax({
					type : 'post',
					url :  '${ctx}/taogr/worker/tdInsuranceType/getInSuranCeType',
					data : {
						id : tpId
					},
					dataType:'json',
					success : function(data) {
						var  tdInsuranceType = data.result;
						$("#feePer").val(tdInsuranceType.feePer/100);
					},
					error:function(data){
					}
				});
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taogr/worker/tbInsuranceList/">保单信息列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tbInsuranceList/form?id=${tbInsuranceList.id}">保单信息<shiro:hasPermission name="taogr:worker:tbInsuranceList:edit">${not empty tbInsuranceList.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:worker:tbInsuranceList:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbInsuranceList" action="${ctx}/taogr/worker/tbInsuranceList/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		
		<sys:message content="${message}"/>		
		<div class="container-fluid">	
		<div class="tab-pane active" id="panel-base">
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
				<label class="control-label"><font color="red">*</font>职工姓名：</label>
				<div class="controls">
					<form:input path="workerName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				</div>
			</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label"><font color="red">*</font>身份证：</label>
			<div class="controls">
				<form:input path="idNo" htmlEscape="false" maxlength="100" class="input-xlarge card required "/>
			</div>
			</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>保单类型：</label>
			<div class="controls">
				<select name="insuranceTp.id" id="insuranceTp" style="width:180px;" class="required" onchange="changeTp();">  
				<option value="" >--请选择--</option>
				<c:forEach items="${tdInsuranceTypeList }" var="tdInsuranceType" >
					<c:if test="${tbInsuranceList.id==null||tbInsuranceList.id=='' }">
					<option value="${tdInsuranceType.id }" >${tdInsuranceType.insName }</option>
					</c:if>
					<c:if test="${tbInsuranceList.id!=null&&tbInsuranceList.id!='' }">
					<option value="${tdInsuranceType.id }" <c:if test="${tdInsuranceType.id ==tbInsuranceList.insuranceTp.id }">selected</c:if> >${tdInsuranceType.insName }</option>
					</c:if>
				</c:forEach>
			    </select>
			</div>
		</div>
		</div>
		 <div class="span6">
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>保费单价（元）：</label>
			<div class="controls">
			<input type="text" name="feePer" id="feePer" value="${tbInsuranceList.feePer/100 }"  maxlength="20" class="input-xlarge alarmsec required"/>
			</div>
		</div>
		</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
		
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>开始日期（含当天）：</label>
			<div class="controls">
			<input id="dtStart"  name="dtStart" type="text"  maxlength="20"
								class="Wdate required"
								value="${tbInsuranceList.dtStart}"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		</div>
		<div class="span6">
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>结束日期（含当天）：</label>
			<div class="controls">
			<input id="dtEnd"  name="dtEnd" type="text"  maxlength="20"
								class="Wdate required"
								value="${tbInsuranceList.dtEnd}"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
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