<%@page import="java.sql.Date"%>
<%@page import="com.mysql.fabric.xmlrpc.base.Data"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>在保人员管理</title>
	<meta name="decorator" content="default"/>
	
	<script src="${ctxStatic}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/highcharts/js/highcharts.js" type="text/javascript"></script>
	<script src="${ctxStatic}/highcharts/js/highcharts-more.js" type="text/javascript"></script>
	<script src="${ctxStatic}/js/exporting.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }


		});

	</script>
	<script type="text/javascript">
	var showChart = function(){
		var startTime = $("#dtStart").val();
		var endTime = $("#dtEnd").val();
		var data1;
	 	$.ajax({  
	        type: "POST",  
	        cache: false,  
	        dataType: 'json', 
	        url: "/taorg/get_data", 
	        data:{
	        	 startTime:startTime,
	        	 endTime:endTime
	            }, 
	         success: function(dat) { 
	        	 datas = dat;
	        	 //alert(datas);
	        	 ss();
	         },
	         error: function (err) {
	          	alert(err);
	         }
		})
		 
		var chart;
		function ss(){
			chart = new Highcharts.Chart({
				chart: {
					alignTicks: true,
					renderTo : 'container',
		            type:'line'
				
		        }/* ,
		        colors:['#00FF00','#FFD700','#DC143C','#DC143C','#FFD700','#00FF00','#000'] */
		        ,
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                 cursor: 'pointer', 
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f}% ',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        title: {
		            text: '各公司保单总额图',
		            
		             style:{
		                	 
		                fontFamily:'黑体'	                
		            }

		        },
		        xAxis: {	
		        	
		        },
		        yAxis: {

		            
		        },
/* 		        exporting:{
				    filename:'tizhi_exp_pic',
				    url:'/report_yhjk/SaveAsImage?id='+pa.id
				}, */

		        legend: {
		        	enabled:false/* ,
		        
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle', */
		        },
		        credits: {
		             enabled : false
		           },
		          series: [{		            
		            data: datas ,
		            name:'金额(元) ',
		            type:'pie',
		            pointPlacement: 'on'
	           
		        }]
			});
		  }
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taorg/worker/tbInsurance/">在保人员列表</a></li>
		<li class="active"><a href="${ctx}/taorg/worker/tbInsurance/form?id=${tbInsurance.id}">各公司保单金额查询<%-- <shiro:hasPermission name="taorg:worker:tbInsuranceList:edit">${not empty tbInsuranceList.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taorg:worker:tbInsuranceList:edit">查看</shiro:lacksPermission> --%></a></li>
	</ul><br/>
	  
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:form id="" >
	

		<div class="control-group">
			<label class="control-label"><font color="red">*</font>开始日期（含当天）：</label>
		
			<input id="dtStart"  name="dtStart" type="text"  maxlength="20"
								class="Wdate"
								style="margin-bottom:0px"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

	
	
			<label class="control-label"><font color="red">*</font>结束日期（含当天）：</label>
			
			<input id="dtEnd"  name="dtEnd" type="text"  maxlength="20"
								class="Wdate"
								style="margin-bottom:0px"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		
			  <input id="btnSubmit" class="btn btn-primary" style="width:80px;" value="查询" onclick="showChart();"/>


		</div>
		
           
		
		</form:form>

             <div id="container" style="margin-top:100px;min-width: 400px; max-width: 800px; height:550px; margin: 0 auto"></div>

	
	
</body>
</html>