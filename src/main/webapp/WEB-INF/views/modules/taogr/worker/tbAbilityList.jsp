<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工种管理</title>
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
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taogr/worker/tbAbility/">工种列表</a></li>
		<shiro:hasPermission name="taogr:worker:tbAbility:edit"><li><a href="${ctx}/taogr/worker/tbAbility/form">工种添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbAbility" action="${ctx}/taogr/worker/tbAbility/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>工种名称：</label>
				<form:input path="abName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工种名称</th>
				<th>创建时间</th>
				<shiro:hasPermission name="taogr:worker:tbAbility:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/taogr/worker/tbAbility/form?id={{row.id}}">
				{{row.abName}}
			</a></td>
			<td>
				{{row.createDate}}
			</td>
			<shiro:hasPermission name="taogr:worker:tbAbility:edit"><td>
   				<a href="${ctx}/taogr/worker/tbAbility/form?id={{row.id}}">修改</a>
				<a href="${ctx}/taogr/worker/tbAbility/delete?id={{row.id}}" onclick="return confirmx('确认要删除该工种及所有子工种吗？', this.href)">删除</a>
				<a href="${ctx}/taogr/worker/tbAbility/form?parent.id={{row.id}}">添加下级工种</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>