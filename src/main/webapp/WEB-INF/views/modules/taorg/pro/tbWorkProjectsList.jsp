<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程项目管理</title>
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
		<li class="active"><a href="${ctx}/taorg/pro/tbWorkProjects/list">工程项目列表</a></li>
		<shiro:hasPermission name="taorg:pro:tbWorkProjects:edit"><li><a href="${ctx}/taorg/pro/tbWorkProjects/form?id=${tbWorkProjects.id}&parent.id=${tbWorkProjectsparent.id}">工程项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbWorkProjects" action="${ctx}/taorg/pro/tbWorkProjects/list" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:input path="proName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目负责人：</label>
				<form:input path="principal" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="manager" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
		
<%-- 		    <li><label>类型选择：</label>
				<form:radiobuttons path="type" items="${fns:getDictList('tb_ent_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>项目地址</th>
				<th>项目负责人</th>
				<th>项目经理</th>
		
				<th>所属企业</th>
				<th>备注</th>
				
				<shiro:hasPermission name="taorg:pro:tbWorkProjects:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/taorg/pro/tbWorkProjects/form?id={{row.id}}">
				{{row.proName}}
			</a></td>
			<td>
				{{row.address}}
			</td>
			<td>
				{{row.principal}}
			</td>
			<td>
				{{row.manager}}
			</td>
	
			<td>
				{{row.office.name}}
			</td>
			<td>
				{{row.remark}}
			</td>

			<shiro:hasPermission name="taorg:pro:tbWorkProjects:edit"><td>
   				<a href="${ctx}/taorg/pro/tbWorkProjects/form?id={{row.id}}">修改</a>
				<a href="${ctx}/taorg/pro/tbWorkProjects/delete?id={{row.id}}" onclick="return confirmx('确认要删除该工程项目及所有子工程项目吗？', this.href)">删除</a>
				
			</td></shiro:hasPermission>
		</tr>
	</script>
	<%-- <a href="${ctx}/taorg/pro/tbWorkProjects/form?parent.id={{row.id}}">添加下级工程项目</a>  --%>
</body>
</html>