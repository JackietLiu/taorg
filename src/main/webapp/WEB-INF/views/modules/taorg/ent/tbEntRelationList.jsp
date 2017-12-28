<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业、项目及施工关系管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			
			for (var i=0; i<data.length; i++){
				ids.push(data[i].allId);
				//alert(data[i].type);
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
					addRow(list, tpl, data, row.allId);
				}
				//alert(row.allId);
			}
			
						
		}
		
	</script>
<style type="text/css">
.orderby {
	width: 100%;
}

.orderby li {
	text-decoration: none;
	list-style: none;
	float: left;
}

/* .orderby li button:hover {
	color: red;
} */
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/taorg/ent/tbEntRelation/list?id=${tbEntRelation.id}&parentIds=${tbEntRelation.parentIds}">企业、项目及施工关系列表</a></li>
		<%-- <shiro:hasPermission name="taorg:ent:tbEntRelation:edit"><li><a href="${ctx}/taorg/ent/tbEntRelation/form?parent.id=${tbEntRelation.id}">企业、项目及施工关系添加</a></li></shiro:hasPermission>
 --%>
	</ul>
	<div style="height: 30px;">
		<ul class="orderby">
			<li><a href="${ctx}/taogr/ent/tbEnterpriseInfo/form"><button
						id="newEnt" class="">新增企业</button></a></li>
			<li><a href="${ctx}/taorg/pro/tbWorkProjects/form"><button
						id="newPro" class="">新增项目</button></a></li>
			<li><a href="${ctx}/taorg/worker/tbWorkerTeam/form"><button
						id="newTeam" class="">新增施工队</button></a></li>
		</ul>
	</div>
	<form:form id="searchForm" modelAttribute="tbEntRelation"
		action="${ctx}/taorg/ent/tbEntRelation/list" method="post"
		class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>栏目名称：</label> <form:input path="allName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="treeTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<shiro:hasPermission name="taorg:ent:tbEntRelation:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/javascript">

	</script>
   
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.allId}}" pId="{{row.pid}}">
            <td>{{row.allName}}</td>            
			<shiro:hasPermission name="taorg:ent:tbEntRelation:edit"><td>    
                <a href="${ctx}/{{row.url}}?id={{row.allId}}">修改</a>
  				<a href="${ctx}/taorg/ent/tbEntRelation/delete?id={{row.id}}" onclick="return confirmx('确认要删除该企项施关系及所有子企项施关系吗？', this.href)">删除</a>
				<a href="${ctx}/taorg/ent/tbEntRelation/form?id={{row.id}}">修改上级关系</a> 
                
			</td></shiro:hasPermission>
		</tr>
	</script>
	<%-- <a href="${ctx}/taorg/ent/tbEntRelation/form?parentId={{row.parentId}} ">添加上级关系</a> <a href="${ctx}/taorg/ent/tbEntRelation/form?parent.id={{row.id}}">添加下级企项施关系</a>  --%>
</body>
</html>