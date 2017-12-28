<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值汇总管理</title>
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
		<li class="active"><a href="${ctx}/taogr/ent/tbEntChargeM/">充值汇总列表</a></li>
		<%-- <shiro:hasPermission name="taogr:ent:tbEntChargeM:edit"><li><a href="${ctx}/taogr/ent/tbEntChargeM/form">充值汇总添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="tbEntChargeM" action="${ctx}/taogr/ent/tbEntChargeM/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业信息：</label>
				<form:input path="office.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th  class="span1 " style="text-align: center; ">序号</th>
				<th>企业信息</th>
				<th>余额（分）</th>
				<th>总充值额（分）</th>
				<%-- <shiro:hasPermission name="taogr:ent:tbEntChargeM:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbEntChargeM"  varStatus="statuss">
			<tr>
			<td style="text-align: center; ">${statuss.count}</td>
				<td>
					${tbEntChargeM.office.name}
				</td>
				<td>
					${tbEntChargeM.fee/100}
				</td>
				<td>
					${tbEntChargeM.feeTotal/100}
				</td>
				<%-- <shiro:hasPermission name="taogr:ent:tbEntChargeM:edit"><td>
    				<a href="${ctx}/taogr/ent/tbEntChargeM/form?id=${tbEntChargeM.id}">修改</a>
					<a href="${ctx}/taogr/ent/tbEntChargeM/delete?id=${tbEntChargeM.id}" onclick="return confirmx('确认要删除该充值汇总吗？', this.href)">删除</a> 
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>