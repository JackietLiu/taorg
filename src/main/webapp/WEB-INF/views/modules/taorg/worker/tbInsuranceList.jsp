<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>在保人员管理</title>
	<meta name="decorator" content="default"/>

	<script type="text/javascript">
		$(document).ready(function() {
			window.onload = function(){

			}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		});
	</script>
		<script type="text/javascript" type="text/javascript">
/* 	function GetRequest() { 
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object(); 
		if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
		} 
		return theRequest; 
		}
		var data = '';
		var pa = GetRequest();
		$.ajax({  
	        type: "POST",  
	        cache: false,  
	        dataType: 'json', 
	        url: "/report_yhjk/get_tb?id="+pa.id, 
	        data:{
	        	
	            }, 
	         success: function(dat) { 
	        	 data = dat;
	        	// alert(data);
	        	 ss();
	         },
	         error: function () {
	          	alert("未知错误，请与系统管理员联系");
	         }
		})
		 */
		

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taorg/worker/tbInsurance/">在保人员列表</a></li>
		<li><a href="${ctx}/taorg/worker/tbInsurance/form">各公司保单金额查询</a></li><%-- <shiro:hasPermission name="taorg:worker:tbInsuranceList:edit"></shiro:hasPermission> --%>
	</ul> 
	<form:form id="searchForm" modelAttribute="tbInsurance" action="${ctx}/taorg/worker/tbInsurance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
                 <form:input path="office.name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业名称</th>
				<th>工人姓名</th>
				<th>身份证</th>
				<th>保单类型</th>
				<th>保费</th>
				<th>开始日期</th>
				<th>结束日期</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="taorg:worker:tbInsuranceList:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbInsuranceList">
			<tr>
				<td>
					${tbInsuranceList.office.name}
				</td>
				<td>
					${tbInsuranceList.workerName}
				</td>
				<td>
					${tbInsuranceList.idNo}
				</td>
				<td>
					${tbInsuranceList.insuranceTp.insName}
				</td>
				<td>
					${tbInsuranceList.feePer}
				</td>
				<td>
					${tbInsuranceList.dtStart}
				</td>
				<td>
					${tbInsuranceList.dtEnd}
				</td>
				<td>
				 <font color="green">在保</font>
				    
<%-- 				 <c:if test="${tbInsuranceList.inQuit == 1}">
				   									     
				</c:if>  
					 <c:if test="${tbInsuranceList.inQuit == 2}">	
					     <font color="red">过保</font>	   
					</c:if> 
					 --%>
				</td>
				<td>
					<fmt:formatDate value="${tbInsuranceList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tbInsuranceList.remarks}
				</td>
			
				<shiro:hasPermission name="taorg:worker:tbInsuranceList:edit">
				  <td>
    				<a href="${ctx}/taorg/worker/tbInsuranceList/form?id=${tbInsuranceList.id}">修改</a>
					<a href="${ctx}/taorg/worker/tbInsuranceList/delete?id=${tbInsuranceList.id}" onclick="return confirmx('确认要删除该在保人员吗？', this.href)">删除</a>
				  </td>
				</shiro:hasPermission>
			</tr>

		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
	</script>
</body>
</html>