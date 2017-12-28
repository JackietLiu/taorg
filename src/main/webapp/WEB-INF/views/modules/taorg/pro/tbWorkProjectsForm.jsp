<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程项目管理</title>
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
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/taorg/pro/tbWorkProjects/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taorg/pro/tbWorkProjects/list?id=${tbWorkProjects.id}&parentIds=${tbWorkProjects.parentIds}">工程项目列表</a></li>
		<li class="active"><a href="${ctx}/taorg/pro/tbWorkProjects/form?id=${tbWorkProjects.id}&parent.id=${tbWorkProjectsparent.id}">工程项目<shiro:hasPermission name="taorg:pro:tbWorkProjects:edit">${not empty tbWorkProjects.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taorg:pro:tbWorkProjects:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbWorkProjects" action="${ctx}/taorg/pro/tbWorkProjects/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="proName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属企业：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${tbWorkProjects.office.id}" labelName="office.name" labelValue="${tbWorkProjects.office.name}"
					title="企业" url="/taorg/office/sysOffice/treeData" extId="" cssClass="" allowClear="true"/>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">上级项目：</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${tbWorkProjects.parent.id}" labelName="parent.proName" labelValue="${tbWorkProjects.parent.proName}"
					title="项目" url="/taorg/pro/tbWorkProjects/treeData" extId="" cssClass="${tbWorkProjects.id}" allowClear="true"/><span>#</span><span>此项无需填写</span>
			</div>
		</div> --%>

		<div class="control-group">
			<label class="control-label">项目地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<form:input path="principal" htmlEscape="false" maxlength="25" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理：</label>
			<div class="controls">
				<form:input path="manager" htmlEscape="false" maxlength="25" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="taorg:pro:tbWorkProjects:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>