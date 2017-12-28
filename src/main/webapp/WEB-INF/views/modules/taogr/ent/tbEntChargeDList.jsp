<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值明细管理</title>
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
		<li class="active"><a href="${ctx}/taorg/office/sysOffice/">企业/部门列表</a></li>
		<li class="active"><a href="${ctx}/taogr/ent/tbEntChargeD/list?office.id=${tbEntChargeD.office.id}">充值明细列表</a></li>
		<shiro:hasPermission name="taogr:ent:tbEntChargeD:edit"><li><a href="${ctx}/taogr/ent/tbEntChargeD/form?office.id=${tbEntChargeD.office.id}">充值明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbEntChargeD" action="${ctx}/taogr/ent/tbEntChargeD/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="office.id" value="${tbEntChargeD.office.id}"/>
		<ul class="ul-form">
			<li><label style="width:120px;">转账号/支票号：</label>
				<form:input path="feeCertificateNo" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>确认人：</label>
				<form:input path="userAudit.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>确认日期：</label>
				<form:input path="dtAudit" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>确认标志：</label>
				<form:radiobuttons path="statusAudit" items="${fns:getDictList('status_audit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业</th>
				<th>充值金额（元）</th>
				<th>转账号/支票号</th>
				<th>确认人</th>
				<th>确认日期</th>
				<th>确认标志</th>
				<th>创建日期</th>
				<shiro:hasPermission name="taogr:ent:tbEntChargeD:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbEntChargeD">
			<tr>
				<td><a href="${ctx}/taogr/ent/tbEntChargeD/form?id=${tbEntChargeD.id}">
					${tbEntChargeD.office.name}
				</a></td>
				<td>
					${tbEntChargeD.fee/100}
				</td>
				<td>
					${tbEntChargeD.feeCertificateNo}
				</td>
				<td>
					${tbEntChargeD.userAudit.name}
				</td>
				<td>
					${tbEntChargeD.dtAudit}
				</td>
				<td>
					${fns:getDictLabel(tbEntChargeD.statusAudit, 'status_audit', '')}
				</td>
				<td>
					<fmt:formatDate value="${tbEntChargeD.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" />
				</td>
				<shiro:hasPermission name="taogr:ent:tbEntChargeD:edit"><td>
				<c:if test="${tbEntChargeD.statusAudit==1 }">
    				<a href="${ctx}/taogr/ent/tbEntChargeD/form?id=${tbEntChargeD.id}&office.id=${tbEntChargeD.office.id}">修改</a>
					<a href="${ctx}/taogr/ent/tbEntChargeD/delete?id=${tbEntChargeD.id}&office.id=${tbEntChargeD.office.id}" onclick="return confirmx('确认要删除该充值明细吗？', this.href)">删除</a>
					<a href="${ctx}/taogr/ent/tbEntChargeD/toUpdateStatus?id=${tbEntChargeD.id}&office.id=${tbEntChargeD.office.id}" onclick="return confirmx('确认要确认该充值明细吗？', this.href)">确认</a>
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>