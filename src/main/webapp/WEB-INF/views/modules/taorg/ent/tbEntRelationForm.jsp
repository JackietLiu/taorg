<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企项施关系管理</title>
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
		<li><a href="${ctx}/taorg/ent/tbEntRelation/list">企业、项目及施工关系列表</a></li>
		<li class="active"><a href="${ctx}/taorg/ent/tbEntRelation/form?id=${tbEntRelation.id}&parent.id=${tbEntRelation.parentId}">企业、项目及施工关系<shiro:hasPermission name="taorg:ent:tbEntRelation:edit">${not empty tbEntRelation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taorg:ent:tbEntRelation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbEntRelation" action="${ctx}/taorg/ent/tbEntRelation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">上级栏目</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parentId" value="${tbEntRelation.parentId}" labelName="parentName" labelValue="${tbEntRelation.parentName}"
					title="栏目" url="/taorg/ent/tbEntRelation/treeData" extId="" cssClass="" allowClear="true"/>
			</div>			
		</div>
	
<%-- 		<div class="control-group">
			<label class="control-label">上级栏目</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parentId" value="${tbEntRelation.parentId}" labelName="parentName" labelValue="${tbEntRelation.parentName}"
					title="栏目" url="/taorg/ent/tbEntRelation/treeData" extId="" cssClass="" allowClear="true"/>
			</div>			
		</div> --%>
	
		

<%-- 		<div class="control-group">
			<label class="control-label">all_id：</label>
			<div class="controls">
				<form:input path="allId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="taorg:ent:tbEntRelation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>