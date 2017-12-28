<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值明细管理</title>
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
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taorg/office/sysOffice/">企业/部门列表</a></li>
		<li><a href="${ctx}/taogr/ent/tbEntChargeD/list?office.id=${tbEntChargeD.office.id}">充值明细列表</a></li>
		<li class="active"><a href="${ctx}/taogr/ent/tbEntChargeD/form?id=${tbEntChargeD.id}&office.id=${tbEntChargeD.office.id}">充值明细<shiro:hasPermission name="taogr:ent:tbEntChargeD:edit">${not empty tbEntChargeD.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:ent:tbEntChargeD:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbEntChargeD" action="${ctx}/taogr/ent/tbEntChargeD/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="office.id" value="${tbEntChargeD.office.id}"/>
		<input type="hidden" name="oldFee" value="${tbEntChargeD.fee}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">企业名称：</label>
			<div class="controls">
			<%-- ${tbEntChargeD.office.name} --%>
			<c:choose>
			<c:when test="${tbEntChargeD.office.name==''||tbEntChargeD.office.name==null}">
			${nameAdd }
			</c:when>
			<c:otherwise>
			${tbEntChargeD.office.name}
			</c:otherwise>
			</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">充值（单位元）：</label>
			<div class="controls">
				<input type="text" name="fee" value="${tbEntChargeD.fee/100 }" maxlength="20" class="input-xlarge alarmsec required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">转账号/支票号：</label>
			<div class="controls">
				<form:input path="feeCertificateNo" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发票截图：</label>
			<div class="controls">
				 <form:hidden id="nameImage" path="feeImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/feeImage" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>