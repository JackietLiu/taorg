<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日志管理</title>
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
		<li class="active"><a href="${ctx}/taorg/loginlog/tbLoginLog/">日志列表</a></li>
		<shiro:hasPermission name="taorg:loginlog:tbLoginLog:edit"><li><a href="${ctx}/taorg/loginlog/tbLoginLog/form">日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbLoginLog" action="${ctx}/taorg/loginlog/tbLoginLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		    
			<li><label>登录人姓名：</label> <form:input path="loginName"
					htmlEscape="false" maxlength="100" class="input-medium" />
			</li>
			<li><label>登录IP：</label> <form:input path="loginIp"
					htmlEscape="false" maxlength="100" class="input-medium" />
			</li>
			<li><label>企业：</label> <form:input path="officeName"
					htmlEscape="false" maxlength="100" class="input-medium" />
			</li>
		<%-- 	<li><label>登录时间：</label> <form:input path="entName"
					htmlEscape="false" maxlength="100" class="input-medium" />
			</li> --%>
	
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column login_name">登录人姓名</th>
				<th class="sort-column last_login_date">最后一次登录时间</th>
				<th>登录IP</th>
				<th>企业名称</th>
				<shiro:hasPermission name="taorg:loginlog:tbLoginLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbLoginLog">
			<tr>
				<td>
					${tbLoginLog.loginName}
				</td>
				<td>
					${tbLoginLog.lastLoginDate}
				</td>
				<td>
					${tbLoginLog.loginIp}
				</td>
				<td>
					${tbLoginLog.officeName}
				</td>
				<shiro:hasPermission name="taorg:loginlog:tbLoginLog:edit"><td>
    				<a href="${ctx}/taorg/loginlog/tbLoginLog/form?id=${tbLoginLog.id}">修改</a>
					<a href="${ctx}/taorg/loginlog/tbLoginLog/delete?id=${tbLoginLog.id}" onclick="return confirmx('确认要删除该日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>