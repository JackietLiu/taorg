<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保险类型信息管理</title>
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
		<li class="active"><a href="${ctx}/taogr/worker/tdInsuranceType/">保险类型信息列表</a></li>
		<shiro:hasPermission name="taogr:worker:tdInsuranceType:edit"><li><a href="${ctx}/taogr/worker/tdInsuranceType/form">保险类型信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tdInsuranceType" action="${ctx}/taogr/worker/tdInsuranceType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>保险名称：</label>
				<form:input path="insName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>保险编号：</label>
				<form:input path="insNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>保险简称：</label>
				<form:input path="insNameAbbr" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>保险名称</th>
				<th>保险编号</th>
				<th>保险简称</th>
				<th>保费</th>
				<th>是否在用</th>
				<th>销售公司</th>
				<shiro:hasPermission name="taogr:worker:tdInsuranceType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdInsuranceType">
			<tr>
				<td><a href="${ctx}/taogr/worker/tdInsuranceType/form?id=${tdInsuranceType.id}">
					${tdInsuranceType.insName}
				</a></td>
				<td>
					${tdInsuranceType.insNo}
				</td>
				<td>
					${tdInsuranceType.insNameAbbr}
				</td>
				<td>
					${tdInsuranceType.feePer/100}
				</td>
				<td>
					${fns:getDictLabel(tdInsuranceType.isActive, 'is_active', '')}
				</td>
				<td>
					${tdInsuranceType.corpName}
				</td>
				<shiro:hasPermission name="taogr:worker:tdInsuranceType:edit"><td>
    				<a href="${ctx}/taogr/worker/tdInsuranceType/form?id=${tdInsuranceType.id}">修改</a>
					<a href="${ctx}/taogr/worker/tdInsuranceType/delete?id=${tdInsuranceType.id}" onclick="return confirmx('确认要删除该保险类型信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>