<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业信息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							isActive: getDictLabel(${fns:toJson(fns:getDictList('is_active'))}, row.isActive), 
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/taogr/ent/tbEnterpriseInfo/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taogr/ent/tbEnterpriseInfo/list">企业信息列表</a></li>
		<shiro:hasPermission name="taogr:ent:tbEnterpriseInfo:edit"><li><a href="${ctx}/taogr/ent/tbEnterpriseInfo/form?parent.id=${tbEnterpriseInfo.id}">企业信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbEnterpriseInfo" action="${ctx}/taogr/ent/tbEnterpriseInfo/list" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="entName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>企业编号：</label>
				<form:input path="entNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系人：</label>
				<form:input path="contactName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="contactTel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>联系人邮箱：</label>
				<form:input path="contactMail" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>企业法人：</label>
				<form:input path="entRep" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>是否在用：</label>
				<form:radiobuttons path="isActive" items="${fns:getDictList('is_active')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业名称</th>
				<th>企业编号</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>联系人邮箱</th>
				<th>企业法人</th>
				<th>是否在用</th>
				<shiro:hasPermission name="taogr:ent:tbEnterpriseInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/taogr/ent/tbEnterpriseInfoDetail?id={{row.id}}">
				{{row.entName}}
			</a></td>
			<td>
				{{row.entNo}}
			</td>
			<td>
				{{row.contactName}}
			</td>
			<td>
				{{row.contactTel}}
			</td>
			<td>
				{{row.contactMail}}
			</td>
			<td>
				{{row.entRep}}
			</td>
			<td>
			 <c:if test="${tbEnterpriseInfo.isActive == 1}">
<font color="green">在用</font>
                </c:if>
			</td>
            <td>
           <shiro:hasPermission name="taogr:ent:tbEnterpriseInfo:edit">
   				<a href="${ctx}/taogr/ent/tbEnterpriseInfo/form?id={{row.id}}">修改</a>
				<a href="${ctx}/taogr/ent/tbEnterpriseInfo/delete?id={{row.id}}" onclick="return confirmx('确认要删除该企业信息及所有子企业信息吗？', this.href)">删除</a>
				<a href="${ctx}/taogr/ent/tbEnterpriseInfo/form?parent.id={{row.id}}">添加下级企业信息</a> </shiro:hasPermission>

			    <a href="${ctx}/taogr/ent/tbEntChargeD/list?ent.id={{row.id}}">充值明细</a>
				<a href="${ctx}/taogr/ent/tbEntChargeC/list?ent.id={{row.id}}">费用变更明细</a>
		</td>
		</tr>
	</script>
</body>
</html>