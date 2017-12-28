<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在保人员情况</title>
    

	<script src="${ctxStatic}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/js/highcharts.js" type="text/javascript"></script>
	<script src="${ctxStatic}/js/highcharts-more.js" type="text/javascript"></script>
	<script src="${ctxStatic}/js/exporting.js" type="text/javascript"></script>
	
	
	<script type="text/javascript" language="javascript">
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
		 ss();
		var chart;
		function ss(){
			chart = new Highcharts.Chart({
				chart: {
					alignTicks: true,
					renderTo : 'container',
		            type:'line',
		            polar:true
				
		        },
		        colors:['#00FF00','#FFD700','#DC143C','#DC143C','#FFD700','#00FF00','#000']
		        ,
		        title: {
		            text: '体质分析图',
		            
		             style:{
		                	 
		                fontFamily:'黑体'	                
		            }
		            //center
		        },
		        xAxis: {	
		        	
		            labels:{
		                style:{
		                	 fontSize:'12pt',
		                	 fontFamily:'仿宋'
		                }
		            },
		            categories: ['平和质','痰湿质','湿热质', '气郁质', '血瘀质', '特禀质', '阴虚质' , '阳虚质', '气虚质'],
		            lineWidth:0,
		            tickmarkPlacement: 'on'
		        },
		        yAxis: {
		            gridLineInterpolation: 'polygon',
		           
		            gridLineColor: "#000",
		            lineWidth: 0,
		            min: 0,		            
		            tickPositions: [0,15,30,45,60,75,90,100,105],
		            labels:{
		                style:{
		                	 fontSize:'5pt',
		                	 fontFamily:'calibri'
		                }
		            }
		            
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
		        	type:'line',
		        	data: [30,30,30,30,30,30,30,30,30],
		        	pointPlacement: 'on',
		        	 marker: {
	 		                enabled: false
	 		            },
	 		            states: {
	 		                hover: {
	 		                    lineWidth: 0
	 		                }
	 		            },
	 		            enableMouseTracking: false,
			        	  lineWidth:'2'
		        	
		        },{
		        	type:'line',
		        	data: [45,45,45,45,45,45,45,45,45],
		        	pointPlacement: 'on',
		        	 marker: {
	 		                enabled: false
	 		            },
	 		            states: {
	 		                hover: {
	 		                    lineWidth: 0
	 		                }
	 		            },
	 		            enableMouseTracking: false,
		        	  lineWidth:'2'
		        },
		        {
		        	type:'line',
		        	data: [60,60,60,60,60,60,60,60,60],
		        	pointPlacement: 'on',
		        	 marker: {
	 		                enabled: false
	 		            },
	 		            states: {
	 		                hover: {
	 		                    lineWidth: 0
	 		                }
	 		            },
	 		            enableMouseTracking: false,
			        	  lineWidth:'2'
		        	
		        },
		        {
		        	type:'line',
		        	data: [[0,0],[0,30]],
		        	pointPlacement: 'on',
		        	 marker: {
	 		                enabled: false
	 		            },
	 		            states: {
	 		                hover: {
	 		                    lineWidth: 0
	 		                }
	 		            },
	 		            enableMouseTracking: false,
			        	  lineWidth:'4'
		        	
		        },
		        {
		        	type:'line',
		        	data: [[0,30],[0,60]],
		        	pointPlacement: 'on',
		        	 marker: {
	 		                enabled: false
	 		            },
	 		            states: {
	 		                hover: {
	 		                    lineWidth: 0
	 		                }
	 		            },
	 		            enableMouseTracking: false,
			        	  lineWidth:'4'
		        	
		        },{
		        	type:'line',
		        	data: [[0,60],[0,100]],
		        	pointPlacement: 'on',
		        	 marker: {
	 		                enabled: false
	 		            },
	 		            states: {
	 		                hover: {
	 		                    lineWidth: 0
	 		                }
	 		            },
	 		            enableMouseTracking: false,
			        	  lineWidth:'4'
		        	
		        },{		            
		            data: data,
		            name:'得分',
		            type:'line',
		            dataLabels : {
						  enabled : true,
						  rotation : 0,
						  align : 'right'
					    
		            },
		            pointPlacement: 'on'
	           
		        }]
			});
		};
	</script>
</head>
<body>
	<div id="container" style="min-width: 400px; max-width: 600px; height: 350px; margin: 0 auto"></div>
</body>
</html>
