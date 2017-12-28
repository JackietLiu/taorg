<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保单信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbInsuranceList"  class="form-horizontal">
		<form:hidden path="id"/>
		
		<sys:message content="${message}"/>		
		<div class="container-fluid">	
		<div class="tab-pane active" id="panel-base">
		
		<div class="row-fluid">
			<div class="span12">
			<div class="control-group">
				<label class="control-label">企业：</label>
				<div class="controls">
				<input type="text"  style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.office.name}"/>
				</div>
			</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
				<label class="control-label">职工姓名：</label>
				<div class="controls">
				<input type="text"  style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.workerName}"/>
				</div>
			</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">身份证：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.idNo}"/>
			</div>
			</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
		<div class="control-group">
			<label class="control-label">保单类型：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.insuranceTp.insName}"/>
			</div>
		</div>
		</div>
		 <div class="span6">
		<div class="control-group">
			<label class="control-label">保费单价（元）：</label>
			<div class="controls">
			<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.feePer/100 }"/>
			</div>
		</div>
		</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
		
		<div class="control-group">
			<label class="control-label">开始日期（含当天）：</label>
			<div class="controls">
							<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.dtStart }"/>
			</div>
		</div>
		</div>
		<div class="span6">
		<div class="control-group">
			<label class="control-label">结束日期（含当天）：</label>
			<div class="controls">
								<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.dtEnd }"/>
			</div>
		</div>
		</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
			<label class="control-label">保单天数：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.days}"/>
			</div>
		</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">保单金额（元）：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.feePer*tbInsuranceList.days/100}"/>
			</div>
		</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<label class="control-label">确认人：</label>
			<div class="controls">
			<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.userAudit.name }"/>
			</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">确认日期：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.dtAudit }"/>
			</div>
		</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
			<label class="control-label">确认标志：</label>
			<div class="controls">
			<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${fns:getDictLabel(tbInsuranceList.statusAudit, 'status_audit', '')}"/>
				
			</div>
			</div> 
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">进保退保：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${fns:getDictLabel(tbInsuranceList.inQuit, 'in_quit', '')}"/>
			</div>
			</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保日期：</label>
			<div class="controls">
			<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.dtQuitInsurance }"/>
			</div>
		</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保操作人：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.userCreateQuit.name }"/>
			</div>
		</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保天数：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.daysQuit}"/>
			</div>
		</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保金额（元）：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.feePer*tbInsuranceList.daysQuit/100}"/>
			</div>
		</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保操作日期：</label>
			<div class="controls">
			<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.dtQuitCreate }"/>
			</div>
		</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保确认人：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.userAuditQuit.name }"/>
			</div>
		</div>
			</div>
		</div>
		
		
		<div class="row-fluid">
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保确认日期：</label>
			<div class="controls">
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${tbInsuranceList.dtAuditQuit}"/>
			</div>
		</div>
			</div>
			<div class="span6">
			<div class="control-group">
			<label class="control-label">退保确认标志：</label>
			<div class="controls">	
				<input type="text" style="width:180px;"  readonly="readonly" 
					 value="${fns:getDictLabel(tbInsuranceList.statusAuditQuit, 'status_audit_quit', '')}"/>
			</div>
		</div>
			</div>
		</div>
		
		
		<div class="row-fluid">
			<div class="span12">
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		</div>
		</div>
		
		</div>
		</div>
	</form:form>
</body>
</html>