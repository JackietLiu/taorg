<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业信息和职工关系管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						/*                     $("#bycompany").click(
						                      function(){
						                     	 $("#bycompany").addClass("selected");
						                     	 $("#byability").removeClass("selected");
						                     	 $("#byname").removeClass("selected");
						                     	 
						                     	 
						                     	 
						                     	 
						                         });
						                    $("#byability").click(
						                            function(){
						                         	 $("#byability").addClass("selected");
						                         	 $("#bycompany").removeClass("selected");
						                           	 $("#byname").removeClass("selected");
						                            });
						                    $("#byname").click(
						                            function(){
						                         	 $("#byname").addClass("selected");
						                         	 $("#bycompany").removeClass("selected");
						                           	 $("#byability").removeClass("selected");
						                           	
						                            }); */
						$('#allcheck')
								.click(
										function() {
											var itemElements = document
													.getElementsByName("delId");
											var flag = $("#allcheck").is(
													":checked");
											$("input[name=delId]:checkbox")
													.each(
															function() {
																for (var i = 0; i < itemElements.length; i++) {
																	if (flag == false) {
																		$(this)
																				.attr(
																						"checked",
																						false);
																	} else {
																		if (itemElements[i].disabled == false) {
																			itemElements[i].checked = "checked";
																		}
																	}
																}
																$(this)
																		.click(
																				function() {
																					if ($("input[name=delId]:checkbox").length == $("input[name=delId]:checked").length) {
																						$(
																								"#allcheck")
																								.attr(
																										"checked",
																										true);
																					} else {
																						$(
																								"#allcheck")
																								.removeAttr(
																										"checked");
																					}
																				});
															});
										});

						$("#btnBuy").click(
								function() {
									var data = "";
									$("input[name=delId]:checked").each(
											function() {
												data += $(this).val() + ",";
											});
									if (data.length == 0) {
										top.$.jBox.alert('请选择数据！', '系统提示');
									} else {
										data = data.substring(0,data.length - 1);
										
										top.$.jBox.open(
												"iframe:${ctx}/taogr/worker/tbEntWorkerRel/listByWorkerId?workids="
														+ data, "购保职工列表", 1000,
												$(top.document).height() - 120,
												{
													top : "20px",
													iframeScrolling : "no",
													buttons : {
														'关闭' : true
													}
												});
									}
								});
						/*  $("#quit").click(
								function() {
									var data = "";
									$("input[name=delId]:checked").each(
											function() {
												data += $(this).val() + ",";
											});
									if (data.length == 0) {
										top.$.jBox.alert('请选择数据！', '系统提示');
									} else {
										data = data.substring(0,
												data.length - 1);
										top.$.jBox.open(
												"iframe:${ctx}/taogr/worker/tbEntWorkerRel/listByWorkerIds?workids="
														+ data, "退保职工列表", 1000,
												$(top.document).height() - 120,
												{
													top : "20px",
													iframeScrolling : "no",
													buttons : {
														'关闭' : true
													}
												});
									}
								});  */
								$("#search_type").click(function(){
									
									
									if(this.value==1){
										window.location.href="${ctx}/taogr/worker/tbEntWorkerRel/index";
									}else if(this.value==2){
										window.location.href="${ctx}/taogr/worker/tbEntWorkerRel/abilityIndex";
									}else{
										window.location.href="${ctx}/taogr/worker/tbWorkerInfo/list";
									}
									
								});
								$("#searchForm").attr("action","${ctx}/taogr/worker/tbEntWorkerRel/");
					});
	
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>

<style type="text/css">
.selected {
	color: red;
}

.orderby {
	width: 100%;
}

.orderby li {
	text-decoration: none;
	list-style: none;
	float: left;
}

.orderby li button:hover {
	color: red;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taogr/worker/tbWorkerInfo/">职工列表</a></li>
		<shiro:hasPermission name="taogr:worker:tbWorkerInfo:edit">
			<li><a href="${ctx}/taogr/worker/tbWorkerInfo/form">职工信息添加</a></li>
		</shiro:hasPermission>
		<%-- <shiro:hasPermission name="taogr:worker:tbEntWorkerRel:edit"><li><a href="${ctx}/taogr/worker/tbEntWorkerRel/form">职工信息添加</a></li></shiro:hasPermission> --%>
	</ul>

	<form:form id="searchForm" modelAttribute="tbWorkerInfo"
		action="${ctx}/taogr/worker/tbWorkerInfo/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
		<ul class="ul-form">
			<%-- <li><label>企业：</label> <form:input path="office.name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li> --%>
			<li><label>职工姓名：</label> <form:input path="workerCode"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>职工身份证：</label> <form:input path="idNo"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>				
            <li><label>企业：</label> 
			<sys:treeselect id="office" name="office.id" value="${tbWorkerInfo.office.id}" labelName="office.name" labelValue="${tbEntWorkerRel.office.name}" 
				title="企业" url="/taorg/office/sysOffice/treeData" cssClass="" allowClear="true"/>
			</li>
            <li><label>工种：</label> 
			<sys:treeselect id="profTyCd" name="profTyCd.id" value="${tbWorkerInfo.profTyCd.id}" labelName="profTyCd.abName" labelValue="${tbEntWorkerRel.profTyCd.abName}" 
				title="工种" url="/taogr/worker/tbAbility/treeData" cssClass="" allowClear="true"/>		
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /> <shiro:hasPermission
					name="taogr:worker:tbWorkerInfo:edit">
					 <a class="btn btn-success" id="btnBuy">购保</a> 
				    <!-- <a class="btn btn-success" id="quit">退保</a> --> 
				</shiro:hasPermission></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="span1 " style="text-align: center;">序号</th>
				<th class="span1" style="text-align: center;"><input
					type="checkbox" name="allcheck" id="allcheck" /></th>
				<th>企业</th>
				<th class="sort-column worker_code">职工姓名</th>
				<th class="sort-column sex">性别</th>
				<th>身份证</th>
				<th>拼音</th>
				<th>企业与职工关系</th>
				<th class="sort-column ins_name">所购险种</th>
				<th class="sort-column ab_name">职工技能</th>
				<th>住址</th>
				<th>联系电话</th>
				<th class="sort-column jg">籍贯</th>
				<th>现居地址</th>
				<th>更新时间</th>

				<shiro:hasPermission name="taogr:worker:tbEntWorkerRel:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tbWorkerInfo"
				varStatus="statuss">
				<tr>
					<td style="text-align: center;">${statuss.count}</td>
					<td style="text-align: center;"><input type="checkbox"
						name="delId" id="score${statuss.count}"
						value="${tbWorkerInfo.id}" /></td>
					<td><a href="${ctx}/taorg/office/sysOffice/form?id=${tbWorkerInfo.office.id}">${tbWorkerInfo.office.name} </a></td>
					<td><a href="${ctx}/taogr/worker/tbWorkerInfo/form?id=${tbWorkerInfo.id}">${tbWorkerInfo.workerCode} </a></td>
					<td>${fns:getDictLabel(tbWorkerInfo.sex, 'sex', '')}</td>
					<td>${tbWorkerInfo.idNo}</td>
					<td>${tbWorkerInfo.py}</td>
					<td>${fns:getDictLabel(tbWorkerInfo.tbEntWorkerRel.status, 'e_w_status', '')}</td>
					<td>${tbWorkerInfo.tbEntWorkerRel.insuranceTpCd.insName}</td>
					<td>${tbWorkerInfo.profTyCd.abName}</td>
					<td>${tbWorkerInfo.address}</td>
					<td>${tbWorkerInfo.phoneNumber}</td>
					<td>${tbWorkerInfo.jg}</td>
					<td>${tbWorkerInfo.nowAddress}</td>
					<td><fmt:formatDate value="${tbWorkerInfo.tbEntWorkerRel.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><shiro:hasPermission name="taogr:worker:tbWorkerInfo:edit">
							<a href="${ctx}/taogr/worker/tbWorkerInfo/form?id=${tbWorkerInfo.id}">修改</a>
							<c:if test="${tbWorkerInfo.tbEntWorkerRel.status==1 }">
								<a href="${ctx}/taogr/worker/tbEntWorkerRel/updateStatus?id=${tbWorkerInfo.tbEntWorkerRel.id}"
									onclick="return confirmx('确认要解除该企业信息和职工关系吗？', this.href)">解除关系</a>
							</c:if>
							<c:if test="${tbWorkerInfo.tbEntWorkerRel.status==2 }">
								<a href="${ctx}/taogr/worker/tbEntWorkerRel/updateStatus?id=${tbWorkerInfo.tbEntWorkerRel.id}"
									onclick="return confirmx('确认要恢复该企业信息和职工关系吗？', this.href)">恢复关系</a>
							</c:if>
						</shiro:hasPermission> <shiro:hasPermission name="taogr:worker:tbEntWorkerRel:edit">
							<a href="${ctx}/taogr/worker/tbWorkerAbility/list?worker.id=${tbWorkerInfo.id}">职工技能修改</a>
						</shiro:hasPermission>
						 <a href="${ctx}/taogr/worker/tbWorkerInfo/delete?id=${tbWorkerInfo.id}" onclick="return confirmx('确认要删除该企业信息和职工关系吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>