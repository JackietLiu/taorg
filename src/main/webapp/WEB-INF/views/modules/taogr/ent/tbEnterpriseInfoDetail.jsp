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
			$("#searchForm").attr("action","${ctx}/taorg/office/sysOffice/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taorg/office/sysOffice/">企业/部门列表</a></li>
	</ul>

	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>联系人</th>
				<th>性别</th>
				<th>联系电话</th>
				<th>身份证号码</th>
				<th>进保日期</th>
				<th>退保日期</th>
				<th>投保状态</th>
				<th>工种</th>
				
				
				
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
       
		</tr>
	</script>
</body>
</html>