<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保单信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出保单数据吗？","系统提示",function(v,h,f){
 
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/taogr/worker/tbInsuranceList/export");
					   /*  $.ajax({
					    	type:"post",
					    	dataType:"json",
					    	data:{officeId:officeId},
					    	url:"${ctx}/taogr/worker/tbInsuranceList/export",
					    	success:function(data){
					    		
					    	},
					    	error:function(msg){
					    		
					    	}
					    					    						    	
					    }); */
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			$('#allcheck').click(function () {
				var itemElements= document.getElementsByName("delId");
				var flag = $("#allcheck").is(":checked");
				$("input[name=delId]:checkbox").each( function() {
				for(var i=0;i<itemElements.length;i++){
					if (flag == false) {
						$(this).attr("checked", false);
					} else {
					if(itemElements[i].disabled==false){
						itemElements[i].checked="checked";
						}
					}}
					$(this).click( function() {
						if($("input[name=delId]:checkbox").length==$("input[name=delId]:checked").length){
							$("#allcheck").attr("checked",true);
						}else{
							$("#allcheck").removeAttr("checked");
						}		
					});
				});
		 	});
			
			$("#confirmInsBatch").click(function() {
				var data = "";
				$("input[name=delId]:checked").each(function(){
					data += $(this).val() + ",";
				});
				if(data.length == 0) {
					top.$.jBox.alert('请选择数据！', '系统提示');
				} else {
					data = data.substring(0, data.length - 1);
					alert(data);
				}
			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function toInsDetail(id){
			top.$.jBox.open(
					"iframe:${ctx}/taogr/worker/tbInsuranceList/detail?id="+id , 
					"保单详情",
					1000,
					$(top.document).height()-70,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
		}
		
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/taogr/worker/tbInsuranceList/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/taogr/worker/tbInsuranceList/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taogr/worker/tbInsuranceList/">保单信息列表</a></li>
		<shiro:hasPermission name="taogr:worker:tbInsuranceList:edit"><li><a href="${ctx}/taogr/worker/tbInsuranceList/form">保单信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbInsuranceList" action="${ctx}/taogr/worker/tbInsuranceList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>企业：</label>
				<form:input path="ent.entName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>工人姓名：</label>
				<form:input path="workerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
				<li><label>确认标志：</label>
				<form:radiobuttons path="statusAudit" items="${fns:getDictList('status_audit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>企业：</label> 
			<sys:treeselect id="office" name="office.id" value="${tbEntWorkerRel.office.id}" labelName="office.name" labelValue="${tbEntWorkerRel.office.name}" 
				title="企业" url="/taorg/office/sysOffice/treeData" cssClass="" allowClear="true"/>
			</li>
			
			<li><label>身份证：</label>
				<form:input path="idNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>进保退保：</label>
				<form:radiobuttons path="inQuit" items="${fns:getDictList('in_quit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>保单类型：</label>
			<select name="insuranceTp.id"  style="width:180px;">  
				<option value="" >--请选择--</option>
			<c:forEach items="${tdInsuranceTypeList }" var="tdInsuranceType" >
				<option value="${tdInsuranceType.id }" >${tdInsuranceType.insName }</option>
			</c:forEach>
		    </select>
			</li>
			<li><label>退保确认：</label>
				<form:radiobuttons path="statusAuditQuit" items="${fns:getDictList('status_audit_quit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns">
			     <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				 <input id="btnExport" class="btn btn-primary" type="button" onClick="" value="导出"/> 
				<shiro:hasPermission name="taogr:worker:tbInsuranceList:export">
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
				</shiro:hasPermission>
				</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th  class="span1 " style="text-align: center; ">序号</th>
				<th>企业</th>
				<th>工人姓名</th>
				<th>身份证</th>
				<th>保单类型</th>
				<th>保费单价</th>
				<th>保费天数</th>
				<th>保费总价</th>
				<th>进保日期</th>
				<th>退保日期</th>
				<th>购买确认标志</th>
				<th>进保退保</th>
				<th>退保标志</th>				
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbInsuranceList"  varStatus="statuss">
			<tr>
			<td style="text-align: center; ">${statuss.count}</td>
				<td><a href="#" onclick="toInsDetail('${tbInsuranceList.id}');" >
					${tbInsuranceList.office.name}
				</a></td>
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
					${tbInsuranceList.feePer/100}
				</td>
				<td>
				  <c:if test="${tbInsuranceList.days != 0 }">
					${tbInsuranceList.days}
				  </c:if>
				  <c:if test="${tbInsuranceList.days == 0 }">
					${tbInsuranceList.nowDays}
				  </c:if>
				</td>
				<td>
				  <c:if test="${tbInsuranceList.days != 0 }">
					￥${tbInsuranceList.feePer*tbInsuranceList.days/100}
				  </c:if>
				  <c:if test="${tbInsuranceList.days == 0 }">
					￥${tbInsuranceList.feePer*tbInsuranceList.nowDays/100}
				  </c:if>
				</td>
				<td>
					${tbInsuranceList.dtStart}
				</td>
				<td>
					${tbInsuranceList.dtQuitInsurance}
				</td>
				<td>
					${fns:getDictLabel(tbInsuranceList.statusAudit, 'status_audit', '')}
				</td>
				<td>
					${fns:getDictLabel(tbInsuranceList.inQuit, 'in_quit', '')}	
				</td>
				<td>
					${fns:getDictLabel(tbInsuranceList.statusAuditQuit, 'status_audit_quit', '')}	
				</td>
				<td>
					<fmt:formatDate value="${tbInsuranceList.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${tbInsuranceList.statusAudit==1 }"><!-- 进保状态，未确认保单并且没有退保的，才能操作确认保单 -->
					<c:if test="${tbInsuranceList.statusAuditQuit==1 }">					
					<shiro:hasPermission name="taogr:worker:tbInsuranceList:confirmIn">
					<a href="${ctx}/taogr/worker/tbInsuranceList/updateStatusAudit?id=${tbInsuranceList.id}&statusAudit=2" onclick="return confirmx('确认要确认保单信息吗？', this.href)">确认保单</a>
					</shiro:hasPermission>
					</c:if>
					</c:if>
					
					<c:if test="${tbInsuranceList.inQuit==1 }"><!-- 未退保，才可以申请退保 -->
					<shiro:hasPermission name="taogr:worker:tbInsuranceList:outApply">
					<a href="${ctx}/taogr/worker/tbInsuranceList/formExit?id=${tbInsuranceList.id}">退保申请</a>
					</shiro:hasPermission>
					</c:if>
					
					<c:if test="${tbInsuranceList.inQuit==2 }"><!-- 已经申请退保，并且没有确认的才可以有确认退保操作 -->
					<c:if test="${tbInsuranceList.statusAuditQuit==1 }">
					<shiro:hasPermission name="taogr:worker:tbInsuranceList:confirmOut">
					<a href="${ctx}/taogr/worker/tbInsuranceList/updateStatusAuditQuit?id=${tbInsuranceList.id}&statusAuditQuit=2" onclick="return confirmx('执行确认退保吗？', this.href)">确认退保</a>
					</shiro:hasPermission>
					</c:if>
					</c:if>
    				<shiro:hasPermission name="taogr:worker:tbInsuranceList:edit">
						<a href="${ctx}/taogr/worker/tbInsuranceList/form?id=${tbInsuranceList.id}">修改</a>
						<a href="${ctx}/taogr/worker/tbInsuranceList/delete?id=${tbInsuranceList.id}" onclick="return confirmx('确认要删除该保单信息吗？', this.href)">删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>