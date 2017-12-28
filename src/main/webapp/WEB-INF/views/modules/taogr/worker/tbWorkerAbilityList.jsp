<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人技能信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/taogr/worker/tbWorkerInfo/">工人信息列表</a></li> --%>
		<li><a href="${ctx}/taogr/worker/tbEntWorkerRel/">职工列表</a></li>
		<li class="active"><a href="${ctx}/taogr/worker/tbWorkerAbility/list?worker.id=${tbWorkerAbility.worker.id}">技能信息列表</a></li>
		<shiro:hasPermission name="taogr:worker:tbWorkerAbility:edit"><li><a href="${ctx}/taogr/worker/tbWorkerAbility/form?worker.id=${tbWorkerAbility.worker.id}">技能信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbWorkerAbility" action="${ctx}/taogr/worker/tbWorkerAbility/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="worker.id" value="${tbWorkerAbility.worker.id}"/>
		<ul class="ul-form">
			 <%-- <li><label>工人：</label>
				<form:input path="worker.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%> 
			<li><label>是否有证书：</label>
				<form:radiobuttons path="isAuth" items="${fns:getDictList('is_auth')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>证书编号：</label>
				<form:input path="authNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>是否在用：</label>
				<form:radiobuttons path="isActive" items="${fns:getDictList('is_active')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工种</th>
				<th>是否有证书</th>
				<th>证书编号</th>
				<th>证书图片</th>
				<th>是否在用</th>
				<shiro:hasPermission name="taogr:worker:tbWorkerAbility:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbWorkerAbility">
			<tr>
				<td><a href="${ctx}/taogr/worker/tbWorkerAbility/form?id=${tbWorkerAbility.id}">
					<%-- ${fns:getDictLabel(tbWorkerAbility.profTyCd, 'prof_ty_cd', '')} --%>
					${tbWorkerAbility.profTyCd.abName }
				</a></td>
				<td>
					${fns:getDictLabel(tbWorkerAbility.isAuth, 'is_auth', '')}
				</td>
				<td>
					${tbWorkerAbility.authNo}
				</td>
				<td>
					${tbWorkerAbility.authImage}
				</td>
				<td>
					${fns:getDictLabel(tbWorkerAbility.isActive, 'is_active', '')}
				</td>
				<shiro:hasPermission name="taogr:worker:tbWorkerAbility:edit"><td>
    				<a href="${ctx}/taogr/worker/tbWorkerAbility/form?id=${tbWorkerAbility.id}&worker.id=${tbWorkerAbility.worker.id}">修改</a>
					<a href="${ctx}/taogr/worker/tbWorkerAbility/delete?id=${tbWorkerAbility.id}&worker.id=${tbWorkerAbility.worker.id}" onclick="return confirmx('确认要删除该工人技能信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>