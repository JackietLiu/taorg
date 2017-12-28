package com.thinkgem.jeesite.modules.taorg.entity.worker;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.taorg.entity.worker.SqlHelper;

public class get_data extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据，处理中文问题
		request.setCharacterEncoding("utf-8");
		//获取登录页面传过来的用户名和密码
		//response.setContentType("application/json;charset=UTF-8");//
		response.setContentType("text/javascript");  //声明类型防止乱码	
		String dtStart = request.getParameter("startTime");
        
		String dtEnd = request.getParameter("endTime");
		System.out.println(dtStart+"<---->"+dtEnd);
		
	//	String the_Sql="SELECT a.*, b.ent_name,sum(DATEDIFF(CASE WHEN a.status_audit_quit = 1 AND	a.dt_end > '"+ dtEnd +"' THEN a.dt_end WHEN a.status_audit_quit = 1 AND a.dt_end <  '"+ dtEnd +"'  THEN '"+ dtEnd +"' WHEN a.status_audit_quit = 2 AND a.dt_end > '"+ dtEnd +"' THEN a.dt_end ELSE '"+ dtEnd +"' END,CASE WHEN a.dt_start < '"+ dtStart +"'  THEN 	'"+ dtStart +"' ELSE a.dt_start  END) * a.fee_per) AS fee_total FROM tb_insurance_list a LEFT JOIN tb_enterprise_info b ON a.ent_id = b.id WHERE a.del_flag = '0' AND a.dt_start >= '"+ dtStart +"' AND a.dt_end <= '"+ dtEnd +"' GROUP BY a.ent_id";
		String the_Sql="SELECT\n" +
				"	a.*, b.name,\n" +
				"	(sum(\n" +
				"		DATEDIFF(\n" +
				"			CASE\n" +
				"			WHEN a.status_audit_quit = '2' THEN\n" +
				"				a.dt_audit_quit\n" +
				"			WHEN a.status_audit = '2'\n" +
				"			AND a.status_audit_quit = '1'\n" +
				"			AND a.dt_end >= '"+dtEnd+"' THEN\n" +
				"				'"+dtEnd+"'\n" +
				"			WHEN a.status_audit = '2'\n" +
				"			AND a.status_audit_quit = '1'\n" +
				"			AND a.dt_end <= '"+dtEnd+"' THEN\n" +
				"				a.dt_end\n" +
				"			END,\n" +
				"			CASE\n" +
				"		WHEN a.dt_start <= '"+dtStart+"' THEN\n" +
				"			'"+dtStart+"'\n" +
				"		ELSE\n" +
				"			a.dt_start\n" +
				"		END\n" +
				"		)+1) * a.fee_per\n" +
				"	) AS fee_total\n" +
				"FROM\n" +
				"	tb_insurance_list a\n" +
				"LEFT JOIN sys_office b ON a.ent_id = b.id\n" +
				"WHERE\n" +
				"	a.del_flag = '0'\n" +
				"GROUP BY\n" +
				"	a.ent_id";/*		SELECT column_name(s)
		FROM table_name1
		LEFT JOIN table_name2 
		ON table_name1.column_name=table_name2.column_name   *///sql join语句
		ResultSet the_rs=new SqlHelper().executeQuery(the_Sql);
			
		String ss="[[";
		try {
			while (the_rs.next()){
		/*		System.out.println(the_rs.next());*/
				System.out.println(the_rs.getString("name"));
				System.out.println(the_rs.getString("fee_total"));
                
				ss = ss +"\""+ the_rs.getString("name")+"\","+the_rs.getString("fee_total");
				ss = ss+"]"+",[";
                
				
			}
			
			ss = ss.substring(0, ss.length()-2);
			ss = ss + "]";
			System.out.println(ss);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		// out.print(ss);
		
		out.print(ss); //响应JSON
		//out.write("{success:\"0\",fileName:\"您请求的内容不存在!\"}"); 
		 out.close();
//		 request.setAttribute("data", ss);
//		 response.sendRedirect( request.getContextPath()+"/tongji.jsp");
	}

}
