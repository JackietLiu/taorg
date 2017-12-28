<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>施工队管理</title>
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
		<li class="active"><a href="${ctx}/taorg/worker/tbWorkerTeam/">施工队列表</a></li>
		<shiro:hasPermission name="taorg:worker:tbWorkerTeam:edit"><li><a href="${ctx}/taorg/worker/tbWorkerTeam/form">施工队添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbWorkerTeam" action="${ctx}/taorg/worker/tbWorkerTeam/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>施工队名称：</label><form:input class="middle-input" path="teamName"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>施工队名称</th>
			    <th>负责项目</th>
				<th>备注</th>
				<shiro:hasPermission name="taorg:worker:tbWorkerTeam:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbWorkerTeam">
			<tr>
				<td><a href="${ctx}/taorg/worker/tbWorkerTeam/form?id=${tbWorkerTeam.id}">
					${tbWorkerTeam.teamName}
				</a></td>
					<td>${tbWorkerTeam.projects.proName}</td>
				<td>${tbWorkerTeam.remarks}</td>
				<shiro:hasPermission name="taorg:worker:tbWorkerTeam:edit"><td>
    				<a href="${ctx}/taorg/worker/tbWorkerTeam/form?id=${tbWorkerTeam.id}">修改</a>
    				<%-- <a href="${ctx}/taorg/ent/tbEntRelation/form?allId=${tbWorkerTeam.id}">添加一个项目</a> --%>
					<a href="${ctx}/taorg/worker/tbWorkerTeam/delete?id=${tbWorkerTeam.id}" onclick="return confirmx('确认要删除该施工队吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>