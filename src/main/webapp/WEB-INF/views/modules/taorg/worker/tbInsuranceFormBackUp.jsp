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
			            text: '保单总额图',
			            
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
			            data: [['退保金额',   45.0],
			                  
			                   ['未确认保单金额',       26.8],
			                   {
			                       name: '在保总金额',
			                       y: 45.0,
			                       sliced: true,
			                       selected: true
			                   }],
			            name:'金额',
			            type:'pie',
			            
			            dataLabels : {
							  enabled : true,
							  rotation : 0,
							  align : 'right'
						    
			            },
			            pointPlacement: 'on'
		           
			        }]
				});
			};
			 ss();
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taorg/worker/tbInsurance/">在保人员列表</a></li>
		<li class="active"><a href="${ctx}/taorg/worker/tbInsurance/form?id=${tbInsurance.id}">保单金额详情<%-- <shiro:hasPermission name="taorg:worker:tbInsuranceList:edit">${not empty tbInsuranceList.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taorg:worker:tbInsuranceList:edit">查看</shiro:lacksPermission> --%></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbInsuranceList" action="${ctx}/taorg/worker/tbInsuranceList/save" method="post" class="form-horizontal">
<div id="container" style="min-width: 400px; max-width: 600px; height: 350px; margin: 0 auto"></div>


	</form:form> 
	
	
</body>
</html>