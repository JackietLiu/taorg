<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保险类型信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taogr/worker/tdInsuranceType/">保险类型信息列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tdInsuranceType/form?id=${tdInsuranceType.id}">保险类型信息<shiro:hasPermission name="taogr:worker:tdInsuranceType:edit">${not empty tdInsuranceType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:worker:tdInsuranceType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tdInsuranceType" action="${ctx}/taogr/worker/tdInsuranceType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>保险名称：</label>
			<div class="controls">
				<form:input path="insName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<c:if test="${tdInsuranceType.id!=null&&tdInsuranceType.id!='' }">
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>保险编号：</label>
			<div class="controls">
				<form:input path="insNo" htmlEscape="false" maxlength="255" readonly="true" class="input-xlarge required"/>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">保险简称：</label>
			<div class="controls">
				<form:input path="insNameAbbr" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:input path="describeType" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购买注意事项：</label>
			<div class="controls">
				<form:input path="buyMemo" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>保费：</label>
			<div class="controls">
				<input type="text" name="feePer" value="${tdInsuranceType.feePer/100 }" maxlength="20" class="input-xlarge alarmsec required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否在用：</label>
			<div class="controls">
				<form:radiobuttons path="isActive" items="${fns:getDictList('is_active')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售公司：</label>
			<div class="controls">
				<form:input path="corpName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taogr:worker:tdInsuranceType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>