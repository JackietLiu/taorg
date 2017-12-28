<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业信息管理</title>
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
		<li><a href="${ctx}/taogr/ent/tbEnterpriseInfo/list">企业信息列表</a></li>
		<li class="active"><a href="${ctx}/taogr/ent/tbEnterpriseInfo/form?id=${tbEnterpriseInfo.id}&parent.id=${tbEnterpriseInfoparent.id}">企业信息<shiro:hasPermission name="taogr:ent:tbEnterpriseInfo:edit">${not empty tbEnterpriseInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:ent:tbEnterpriseInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbEnterpriseInfo" action="${ctx}/taogr/ent/tbEnterpriseInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">企业名称：</label>
			<div class="controls">
				<form:input path="entName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<c:if test="${tbEnterpriseInfo.id!=null&&tbEnterpriseInfo.id!='' }">
		
				<div class="control-group">
					<label class="control-label">企业编号：</label>
					<div class="controls">
						<form:input path="entNo" htmlEscape="false" readonly="true" maxlength="100" class="input-xlarge"/>
				
				</div>
			</div>
			</c:if>
		<div class="control-group">
			<label class="control-label">所属上级单位:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${tbEnterpriseInfo.parent.id}" labelName="parent.name" labelValue="${tbEnterpriseInfo.parent.name}"
					title="上级单位" url="/taogr/ent/tbEnterpriseInfo/treeData" extId="${tbEnterpriseInfo.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="contactName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人职务：</label>
			<div class="controls">
				<form:input path="contactTitle" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="contactTel" htmlEscape="false" maxlength="50" class="input-xlarge mobile"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人邮箱：</label>
			<div class="controls">
				<form:input path="contactMail" htmlEscape="false" maxlength="100" class="input-xlarge email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业法人：</label>
			<div class="controls">
				<form:input path="entRep" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">办公地址：</label>
			<div class="controls">
				<form:input path="entAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业邮箱：</label>
			<div class="controls">
				<form:input path="entMail" htmlEscape="false" maxlength="100" class="input-xlarge email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否在用：</label>
			<div class="controls">
				<form:radiobuttons path="isActive" items="${fns:getDictList('is_active')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">企业性质：</label>
			<div class="controls">
				<form:select path="entType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ent_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册地址：</label>
			<div class="controls">
				<form:input path="regAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业LOGO：</label>
			<div class="controls">
				 <form:hidden id="logoPath" path="logoPath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="logoPath" type="images" uploadPath="/logoPath" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业预警额度试方式：</label>
			<div class="controls">
				<form:select path="warnTp" class="input-xlarge ">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('warn_tp')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业预警额度值：</label>
			<div class="controls">
				<input type="text" name="warnValue" value="${tbEnterpriseInfo.warnValue/100 }" maxlength="20" class="input-xlarge alarmsec required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taogr:ent:tbEnterpriseInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>