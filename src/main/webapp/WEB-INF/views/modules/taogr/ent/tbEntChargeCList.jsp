<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>费用变更明细管理</title>
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
		<li class="active"><a href="${ctx}/taogr/ent/tbEntChargeC/list?office.id=${tbEntChargeC.office.id}">费用变更明细列表</a></li>
		<%-- <shiro:hasPermission name="taogr:ent:tbEntChargeC:edit"><li><a href="${ctx}/taogr/ent/tbEntChargeC/form?ent.id=${tbEntChargeC.ent.id}">费用变更明细添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="tbEntChargeC" action="${ctx}/taogr/ent/tbEntChargeC/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="office.id" value="${tbEntChargeC.office.id}"/>
		<ul class="ul-form">
			<%-- <li><label>企业ID：</label>
				<form:input path="ent.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>确认人：</label>
				<form:input path="userAudit.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>进保退保：</label>
			<form:radiobuttons path="inQuit" items="${fns:getDictList('in_quit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<%-- <li><label>确认日期：</label>
				<form:input path="dtAudit" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业</th>
				<th>金额（元）</th>
				<th>总工人数</th>
				<th>进保退保</th>
				<th>是否确认</th>
				<th>确认人</th>
				<th>确认日期</th>
				<shiro:hasPermission name="taogr:ent:tbEntChargeC:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbEntChargeC">
			<tr>
				<td>
					${tbEntChargeC.office.name}
				</td>
				<td>
					${tbEntChargeC.feeTotal/100}
				</td>
				
				<td>
					${tbEntChargeC.workerCount}
				</td>
				<td>
					${fns:getDictLabel(tbEntChargeC.inQuit, 'in_quit', '')}
				</td>
				
				<td>
					${fns:getDictLabel(tbEntChargeC.auditStatus, 'status_audit_c', '')}
				</td>
				<td>
					${tbEntChargeC.userAudit.name}
				</td>
				<td>
					${tbEntChargeC.dtAudit}
				</td>
				<shiro:hasPermission name="taogr:ent:tbEntChargeC:edit"><td>
				<c:if test="${tbEntChargeC.auditStatus==1 }">
				<a href="${ctx}/taogr/ent/tbEntChargeC/toUpdateStatus?id=${tbEntChargeC.id}&office.id=${tbEntChargeC.office.id}" onclick="return confirmx('确认该费用变更明细吗？', this.href)">确认</a>
    			</c:if>
    				<%-- <a href="${ctx}/taogr/ent/tbEntChargeC/form?id=${tbEntChargeC.id}">修改</a>
					<a href="${ctx}/taogr/ent/tbEntChargeC/delete?id=${tbEntChargeC.id}" onclick="return confirmx('确认要删除该费用变更明细吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>