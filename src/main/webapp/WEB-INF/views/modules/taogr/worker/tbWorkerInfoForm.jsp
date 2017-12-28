<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		<li><a href="${ctx}/taogr/worker/tbEntWorkerRel/">职工列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tbWorkerInfo/form?id=${tbWorkerInfo.id}">职工信息<shiro:hasPermission name="taogr:worker:tbWorkerInfo:edit">${not empty tbWorkerInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:worker:tbWorkerInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbWorkerInfo" action="${ctx}/taogr/worker/tbWorkerInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">选择企业</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${tbWorkerInfo.office.id}" labelName="office.name" labelValue="${tbWorkerInfo.office.name}"
					title="企业" url="/taorg/office/sysOffice/treeData" extId="" cssClass="required" allowClear="true"/><span style="color:red;margin-left:10px;">*</span>
			</div>			
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="workerCode" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			<span style="color:red;margin-left:10px;">*</span></div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">照片：</label>
			<div class="controls">
				 <form:hidden id="workerImage" path="workerImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="workerImage" type="images" uploadPath="/workerImage" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">曾用名：</label>
			<div class="controls">
				<form:input path="nameOnce" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否在用：</label>
			<div class="controls">
				<form:radiobuttons path="isActive" items="${fns:getDictList('is_active')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证：</label>
			<div class="controls">
				<form:input id="idNo" path="idNo" htmlEscape="false" maxlength="100" class="input-xlarge card  required"/><span style="color:red;margin-left:10px;">*</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传身份证：</label>
			<div class="controls">
				 <form:hidden id="py" path="py" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="py" type="images" uploadPath="/idsImages" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<form:input path="phoneNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">住址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯：</label>
			<div class="controls">
				<form:input path="jg" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现居地址：</label>
			<div class="controls">
				<form:input path="nowAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taogr:worker:tbWorkerInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>