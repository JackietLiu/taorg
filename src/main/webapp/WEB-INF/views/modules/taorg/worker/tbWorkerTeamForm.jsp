<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>施工队管理</title>
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
		<li><a href="${ctx}/taorg/worker/tbWorkerTeam/">施工队列表</a></li>
		<li class="active"><a href="${ctx}/taorg/worker/tbWorkerTeam/form?id=${tbWorkerTeam.id}">施工队<shiro:hasPermission name="taorg:worker:tbWorkerTeam:edit">${not empty tbWorkerTeam.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taorg:worker:tbWorkerTeam:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbWorkerTeam" action="${ctx}/taorg/worker/tbWorkerTeam/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">负责项目：</label>
			<div class="controls">
				<sys:treeselect id="projects" name="projects.id" value="${tbWorkerTeam.projects.id}" labelName="projects.proName" labelValue="${tbWorkerTeam.projects.proName}"
					title="项目" url="/taorg/pro/tbWorkProjects/treeData" extId="" cssClass="" allowClear="true" checked="false" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">队名称：</label>
			<div class="controls">
				<form:input path="teamName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taorg:worker:tbWorkerTeam:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>