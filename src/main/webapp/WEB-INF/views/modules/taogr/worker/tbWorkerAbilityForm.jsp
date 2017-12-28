<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人技能信息管理</title>
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
			
			$("input[name='isAuth']").click(function () { 
                  if ($(this).val() == "1") {  
                	  oldhidden();
                	  oldhidden2();
                 } else {  
                	 oldshow();  
                	 oldshow2();  
                  }  
              });  
		});
		function oldshow(){
			document.getElementById("oldtr").style.display="";
			}
		function oldhidden(){
		document.getElementById("oldtr").style.display="none";
		}
		function oldshow2(){
			document.getElementById("oldtr2").style.display="";
			}
		function oldhidden2(){
		document.getElementById("oldtr2").style.display="none";
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/taogr/worker/tbEntWorkerRel/">职工列表</a></li>
		<li><a href="${ctx}/taogr/worker/tbWorkerAbility/list?worker.id=${tbWorkerAbility.worker.id}">技能信息列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tbWorkerAbility/form?id=${tbWorkerAbility.id}&worker.id=${tbWorkerAbility.worker.id}">技能信息<shiro:hasPermission name="taogr:worker:tbWorkerAbility:edit">${not empty tbWorkerAbility.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taogr:worker:tbWorkerAbility:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbWorkerAbility" action="${ctx}/taogr/worker/tbWorkerAbility/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="worker.id" value="${tbWorkerAbility.worker.id}"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">工人：</label>
			<div class="controls">
				<form:input path="worker.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>工种代码：</label>
			<div class="controls">
				<%-- <form:select path="profTyCd" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('prof_ty_cd')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<sys:treeselect id="parent" name="profTyCd.id" value="${tbWorkerAbility.profTyCd.id}" labelName="profTyCdb.aName" labelValue="${tbWorkerAbility.profTyCd.abName}"
					title="工种" url="/taogr/worker/tbAbility/treeData" extId="" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>是否有证书：</label>
			<div class="controls">
				<form:radiobuttons path="isAuth" onchange="changeAuth()" id="isAuth" items="${fns:getDictList('is_auth')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group" id="oldtr">
			<label class="control-label">证书编号：</label>
			<div class="controls">
				<form:input path="authNo" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" id="oldtr2">
			<label class="control-label">证书图片：</label>
			<div class="controls">
				 <form:hidden id="nameImage" path="authImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/authImage" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>是否在用：</label>
			<div class="controls">
				<form:radiobuttons path="isActive" items="${fns:getDictList('is_active')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taogr:worker:tbWorkerAbility:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>