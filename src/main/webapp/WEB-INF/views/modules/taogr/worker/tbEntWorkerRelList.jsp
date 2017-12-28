<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业信息和职工关系管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
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
													iframeScrolling : "yes",
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
										
										self.location="${ctx}/taogr/worker/tbEntWorkerRel/index";
									}else if(this.value==2){
										//window.location.href="${ctx}/taogr/worker/tbEntWorkerRel/abilityIndex";
										self.location="${ctx}/taogr/worker/tbEntWorkerRel/abilityIndex";
									}else{
										window.location.href="${ctx}/taogr/worker/tbEntWorkerRel";
									}
									
								});
			                 $("#clear").click(function(){
			                	 window.location.href="${ctx}/taogr/worker/tbEntWorkerRel";									
								});
								$("#searchForm").attr("action","${ctx}/taogr/worker/tbEntWorkerRel/list");
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
		<li class="active"><a href="${ctx}/taogr/worker/tbEntWorkerRel/">职工列表</a></li>
		<shiro:hasPermission name="taogr:worker:tbWorkerInfo:edit">
			<li><a href="${ctx}/taogr/worker/tbWorkerInfo/form">职工信息添加</a></li>
		</shiro:hasPermission>
		<%-- <shiro:hasPermission name="taogr:worker:tbEntWorkerRel:edit"><li><a href="${ctx}/taogr/worker/tbEntWorkerRel/form">职工信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<!-- 	<div style="height:30px;">
	<ul class="orderby">
			<li>
				<a href=""><button id="bycompany" class="">按公司名称</button></a>
			</li>
			<li>
				<button id="byability" class="">按工种名称排序</button>
			</li>
			<li>
				<button id="byname" class="">按员工姓名排序</button>
			</li>
	</ul></div> -->

	<form:form id="searchForm" modelAttribute="tbEntWorkerRel"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
		<ul class="ul-form">
			<li><label>企业：</label> 
			<sys:treeselect id="office" name="office.id" value="${tbEntWorkerRel.office.id}" labelName="office.name" labelValue="${tbEntWorkerRel.office.name}" 
				title="企业" url="/taorg/office/sysOffice/treeData" cssClass="" allowClear="true"/>
			</li>
			<li><label>工种：</label> 

			<sys:treeselect id="profTyCd" name="profTyCd.id" value="${tbEntWorkerRel.profTyCd.id}" labelName="profTyCd.abName" labelValue="${tbEntWorkerRel.profTyCd.abName}" 
				title="工种" url="/taogr/worker/tbAbility/treeData" cssClass="" allowClear="true"/>
				
			</li>
			<li><label>职工姓名：</label> <form:input path="worker.workerCode"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>职工身份证：</label> <form:input path="worker.idNo"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>				
			<li><label>关系状态：</label> <form:radiobuttons path="status"
					items="${fns:getDictList('e_w_status')}" itemLabel="label"
					itemValue="value" htmlEscape="false" /></li>
					
			<li><a class="btn" id="clear" style="margin-left:20px;">清除搜索条件</a>	</li>
<!--        <li><label>分类查询：</label>
            <select id="search_type">
              <option>初始化查询</option>
              <option value="1">按企业查询</option>
              <option value="2">按工种查询</option>
            </select>
            </li> -->
            
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
				<!-- <th class="sort-column ins_name">所购险种</th> -->
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
			<c:forEach items="${page.list}" var="tbEntWorkerRel"
				varStatus="statuss">
				<tr>
					<td style="text-align: center;">${statuss.count}</td>
					<td style="text-align: center;"><input type="checkbox"
						name="delId" id="score${statuss.count}"
						value="${tbEntWorkerRel.worker.id}" /></td>
					<td><a href="${ctx}/taorg/office/sysOffice/form?id=${tbEntWorkerRel.office.id}">
							${tbEntWorkerRel.office.name} </a></td>
					<td><a
						href="${ctx}/taogr/worker/tbWorkerInfo/form?id=${tbEntWorkerRel.worker.id}">
							${tbEntWorkerRel.worker.workerCode} </a></td>
					<td>${fns:getDictLabel(tbEntWorkerRel.worker.sex, 'sex', '')}
					</td>
					<td>${tbEntWorkerRel.worker.idNo}</td>
					<td>${tbEntWorkerRel.worker.py}</td>


					<td>${fns:getDictLabel(tbEntWorkerRel.status, 'e_w_status', '')}
					</td>
	<%-- 				<td>${tbEntWorkerRel.insuranceTpCd.insName}
					</td> --%>
					<td>${tbEntWorkerRel.profTyCd.abName}</td>
					<td>${tbEntWorkerRel.worker.address}</td>
					<td>${tbEntWorkerRel.worker.phoneNumber}</td>

					<td>${tbEntWorkerRel.worker.jg}</td>
					<td>${tbEntWorkerRel.worker.nowAddress}</td>
					<td><fmt:formatDate value="${tbEntWorkerRel.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>


					<td><shiro:hasPermission name="taogr:worker:tbWorkerInfo:edit">
							<a
								href="${ctx}/taogr/worker/tbWorkerInfo/form?id=${tbEntWorkerRel.worker.id}">修改</a>
							<c:if test="${tbEntWorkerRel.status==1 }">
								<a
									href="${ctx}/taogr/worker/tbEntWorkerRel/updateStatus?id=${tbEntWorkerRel.id}"
									onclick="return confirmx('确认要解除该企业信息和职工关系吗？', this.href)">解除关系</a>
							</c:if>
							<c:if test="${tbEntWorkerRel.status==2 }">
								<a
									href="${ctx}/taogr/worker/tbEntWorkerRel/updateStatus?id=${tbEntWorkerRel.id}"
									onclick="return confirmx('确认要恢复该企业信息和职工关系吗？', this.href)">恢复关系</a>
							</c:if>
						</shiro:hasPermission> <shiro:hasPermission name="taogr:worker:tbEntWorkerRel:edit">
							<a
								href="${ctx}/taogr/worker/tbWorkerAbility/list?worker.id=${tbEntWorkerRel.worker.id}">职工技能修改</a>
						</shiro:hasPermission>
			<%--    <c:if test="${tbEntWorkerRel.tbInsuranceList.inQuit.indexOf('1') > 0 || tbEntWorkerRel.tbInsuranceList.inQuit == 1}"><!-- 未退保，才可以申请退保 -->
					<shiro:hasPermission name="taogr:worker:tbInsuranceList:outApply">
					<a href="${ctx}/taogr/worker/tbInsuranceList/formExit?id=${tbEntWorkerRel.tbInsuranceList.id}"><font color="orange">退保申请</font></a>
					</shiro:hasPermission>
				    </c:if> --%>
					    
						<a href="${ctx}/taogr/worker/tbEntWorkerRel/delete?id=${tbEntWorkerRel.id}" onclick="return confirmx('确认要删除该企业信息和职工关系吗？', this.href)">删除</a> 
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>