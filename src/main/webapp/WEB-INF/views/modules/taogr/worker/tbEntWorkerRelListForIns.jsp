<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业信息和职工关系管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

			$("#btnSubmit").click(function(){
					if($("#inputForm").valid()){
						//封装需要提交的数据
						var workerIds = "";
						var workerNames = "";
						var idNos = "";
						var insuranceTps = "";
						var feePers = "";
						var sDates = "";
						var eDates = "";
						var entIds = "";
						var isNullNum = 0 ;//判断未填数据数量
						var msg="";
						$("input[type='checkbox'][name='delId']:checked").each(function(){
							
							if(""==$("#insuranceTp"+this.value).val()){
								isNullNum ++ ;
								return ;
							}
							
							if(""==$("#feePer"+this.value).val()){
								isNullNum ++ ;
								return ;
							}
							
							if(""==$("#dtStart"+this.value).val()){
								isNullNum ++ ;
								return ;
							}
							
/* 							if(""==$("#dtEnd"+this.value).val()){
								isNullNum ++ ;
								return ;
							}  */
							
							 var oDate = new Date(); //实例一个时间对象；
							 var mouth = oDate.getMonth()+1;
							var nows = oDate.getFullYear()+"-"+mouth+"-"+oDate.getDate();
							var now = new Date(nows).getTime();
							 
							 var s = new Date($("#dtStart"+this.value).val()).getTime(); 
							 var e = new Date($("#dtEnd"+this.value).val()).getTime(); 
									
/* 							  if(now>s){
								  isNullNum ++ ;
								  msg= $("#workerName"+this.value).val()+" ，开始日期必须在今天之后！" ;
								  $("#dtStart"+this.value).val("");
								  return ;
							  }
							  if(now>e){
								  isNullNum ++ ;
								  msg= $("#workerName"+this.value).val()+" ，结束日期必须在今天之后！" ;
								  $("#dtEnd"+this.value).val("");
								  return ;
							  } */
							 if(s>e){
								 isNullNum ++ ;
								 msg= $("#workerName"+this.value).val()+" ，结束日期必须在大于开始时间！" ;
								 $("#dtEnd"+this.value).val("");
								  return ;
							 }
							if(""== entIds){
									entIds = this.value+"|"+ $("#entId"+this.value).val()  ;
							}else{
									entIds = this.value+"|"+ $("#entId"+this.value).val() + "," + entIds;
							}
							
							
							if(""==workerIds){
								workerIds = $(this).val()  ;
							}else{
								workerIds = $(this).val() + "," + workerIds;
							}
							
							if(""==workerNames){
								workerNames = this.value+"|"+ $("#workerName"+this.value).val() ;
							}else{
								workerNames = this.value+"|"+ $("#workerName"+this.value).val()+"," + workerNames ;
							}
							
							if(""==idNos){
								idNos = this.value+"|"+ $("#idNo"+this.value).val() ;
							}else{
								idNos = this.value+"|"+ $("#idNo"+this.value).val()+"," + idNos;
							}
							
							if(""==insuranceTps){
								insuranceTps = this.value+"|"+ $("#insuranceTp"+this.value).val() ;
							}else{
								insuranceTps = this.value+"|"+ $("#insuranceTp"+this.value).val()+","+insuranceTps;
							}
						
							if(""==feePers){
								feePers = this.value+"|"+ $("#feePer"+this.value).val() ;
							}else{
								feePers = this.value+"|"+ $("#feePer"+this.value).val()+","+feePers;
							}	
						
							if(""==sDates){
								sDates = this.value+"|"+ $("#dtStart"+this.value).val() ;
							}else{
								sDates = this.value+"|"+ $("#dtStart"+this.value).val()+","+sDates;
							}
						
							if(""==eDates){
								eDates = this.value+"|"+ $("#dtEnd"+this.value).val() ;
							}else{
								eDates = this.value+"|"+ $("#dtEnd"+this.value).val()+","+eDates;
							}
							
						});
						
						if(isNullNum>0){
							if(msg == ""){
								msg = "数据不能空，请正确填写内容！";
							}
							jBox.tip(msg);
							return ;
						}
						
						if(workerIds.length == 0){
							top.$.jBox.alert('请选择数据！', '系统提示');
						}else{
							loading();
							$("#inputForm").submit(
									 $.ajax({
									 	type : 'post',
										url :  '${ctx}/taogr/worker/tbInsuranceList/saveIns',
										data : {
											workerIds : workerIds,
											workerNames : workerNames,
											idNos : idNos,
											insuranceTps : insuranceTps,
											feePers : feePers,
											sDates : sDates,
											eDates : eDates,
											entIds : entIds
										},
										success : function(data) {
											closeLoading();
											if(data.success){
												jBox.tip("提交成功！");
												top.$.jBox.close();
											}else{
												Box.tip(data.msg);
											}
										},
										error:function(data){
										}
									})
								 );
						}
						
					}else{
						validator.focusInvalid();
					}
				});	
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function changeTp(workid){
			 var tpId = $("#insuranceTp"+workid).val();
			
			if(tpId==''){
				$("#feePer"+workid).val("0");
			}else{
				$.ajax({
					type : 'post',
					url :  '${ctx}/taogr/worker/tdInsuranceType/getInSuranCeType',
					data : {
						id : tpId
					},
					dataType:'json',
					success : function(data) {
						var  tdInsuranceType = data.result;
						$("#feePer"+workid).val(tdInsuranceType.feePer/100);
					},
					error:function(data){
					}
				});
			} 
		}
	</script>
</head>
<body>


	<form:form id="inputForm" modelAttribute="TbInsuranceList"   method="post" class="form-horizontal" >
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th  class="span1 " style="text-align: center; ">序号</th>
				<th class="span1"><input type="checkbox" name="allcheck" id="allcheck" checked="checked"/></th>
				<th>职工</th>
				<th>身份证</th>
				<th>险种</th>
				<th>保费（单位元）</th>
				<th>进保日期</th>
				<th>退保日期</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${tbEntWorkerRelList}" var="tbEntWorkerRel" varStatus="statuss">
			<input type="hidden" name="worker.id" id="workerId"  value="${tbEntWorkerRel.worker.id}"/>
			<input type="hidden" name="ent.id" id="entId${tbEntWorkerRel.worker.id }"  value="${tbEntWorkerRel.entId}"/>
			<tr>
				<td style="text-align: center; ">${statuss.count}</td>
				<td><input type="checkbox" name="delId" id="score${statuss.count}"checked="checked" value="${tbEntWorkerRel.worker.id}"/></td>
				<td>
				<input type="text" name="workerName" style="width:60px;" id="workerName${tbEntWorkerRel.worker.id }" readonly="readonly" 
					 value="${tbEntWorkerRel.worker.workerCode}"/>
				</td>
				<td>
				<input type="text" name="idNo" style="width:150px;" id="idNo${tbEntWorkerRel.worker.id }" readonly="readonly" 
					 value="${tbEntWorkerRel.worker.idNo}"/>
				</td>
				<td>
				<select name="insuranceTp.id" id="insuranceTp${tbEntWorkerRel.worker.id }" style="width:150px;"  
					 onchange="changeTp('${tbEntWorkerRel.worker.id }');">  
					<option value="" >--请选择--</option>
					<c:forEach items="${tdInsuranceTypeList }" var="tdInsuranceType" >
						<option value="${tdInsuranceType.id }" >${tdInsuranceType.insName }</option>
					</c:forEach>
			    </select>
				</td>
				<td>
				<input type="text" name="feePer" style="width:80px;"
					 id="feePer${tbEntWorkerRel.worker.id }"  value="0"/>
				</td>
				<td>
				<input id="dtStart${tbEntWorkerRel.worker.id}" name="dtStart" type="text" style="width:120px;"  maxlength="20" class="input-medium Wdate"
					value=""
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
				<td>
				<input id="dtEnd${tbEntWorkerRel.worker.id}" name="dtEnd" type="text"  style="width:120px;" maxlength="20" class="input-medium Wdate"
					value=""
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>
	</form:form>
		<form:form  class="breadcrumb form-search">
	<ul class="ul-form">
	<li class="btns">
	 <a class="btn btn-success" id="btnSubmit">购买保险</a> 
	 <font color="red">注意：进保退保工作日15:00之前确认后于次日零点生效</font>
	</li>
	</ul>
	</form:form>
</body>
</html>