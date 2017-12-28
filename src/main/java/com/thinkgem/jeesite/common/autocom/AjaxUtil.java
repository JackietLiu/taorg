package com.thinkgem.jeesite.common.autocom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {
	private AjaxUtil(){
	}
	//读取POST型请求参数
	public static String readPostRequestParam(HttpServletRequest request){
		StringBuffer result=new StringBuffer();
		String line="";
		try{
			BufferedReader reader=request.getReader();
			while((line=reader.readLine())!=null){
				result.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}
	//回发响应文本
	public static void sendResponseText(HttpServletResponse response,String text){
		try{
			response.setContentType("text/xml");
			response.setCharacterEncoding("gb2312");
			PrintWriter writer=response.getWriter();
			writer.print(text);
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 将组织好的字符串以JSON的方式响应到客户端
	 * @param response
	 * @param text
	 * @return
	 */
	public static boolean sendResponseJsonText(HttpServletResponse response,String text){
		response.setContentType("text/json;charset=UTF-8");

		try {
			response.setContentLength(text.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			pw.print(text);
			pw.flush();
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
		return false;
	}
}
