package com.thinkgem.jeesite.common.autocom;



/** TODO : 逐渐代替 BaseServiceModel 方式 **/
public class BaseResult {
	
	private boolean success;


//	/** 服务执行结果码 默认为 成功 ***/
//	private int exCode = Const.EX_SUCCESS;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/** 操作结果信息 **/
	private String msg;;
	
//	/** 操作结果存放对象 **/
	private Object result;

//	public int getExCode() {
//		return exCode;
//	}
//
//	public void setExCode(int exCode) {
//		this.exCode = exCode;
//	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
//
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	

	
	
}
