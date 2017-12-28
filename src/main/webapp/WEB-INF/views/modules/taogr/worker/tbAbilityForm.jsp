<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工种管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
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
		<li><a href="${ctx}/taogr/worker/tbAbility/">工种列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tbAbility/form?id=${tbAbility.id}&parent.id=${tbAbilityparent.id}">工种<shiro:hasPermission name="taogr:worker:tbAbility:edit">${not empty tbAbility.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:worker:tbAbility:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbAbility" action="${ctx}/taogr/worker/tbAbility/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">上级工种:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${tbAbility.parent.id}" labelName="parent.name" labelValue="${tbAbility.parent.name}"
					title="父级编号" url="/taogr/worker/tbAbility/treeData" extId="${tbAbility.id}" cssClass="" allowClear="true"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">工种名称：</label>
			<div class="controls">
				<form:input path="abName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="taogr:worker:tbAbility:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>