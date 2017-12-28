package com.thinkgem.jeesite.modules.taorg.entity.worker;
/*第一步：新建SqlHelper类；
第二步：新建一个属性文件dbinfo.properties，具体内容如下：
driver=com.mysql.jdbc.Driver
url=jdbc\:mysql\://localhost\:3306/test
userName=root
password=10Floor
第三步：完成SqlHelper类；一定要记着引入数据库驱动程序。SqlHelper类的具体内容如下：*/
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;





public class SqlHelper {
	public  void main(String args[]) throws SQLException {
		String sql="select * from ts_user ";
		ResultSet aa=executeQuery(sql);
		ResultSetMetaData m=aa.getMetaData();
		 		 
		int columns=m.getColumnCount();
			 
		while (aa.next())
		{
			 for(int i=1;i<=columns;i++)
			 {
			     System.out.print(aa.getString(i));
			     System.out.print("\t\t");
			 }
		}
	}
    // 定义要使用的变量
    private  Connection conn = null;
    private  PreparedStatement ps = null;
    private  ResultSet rs = null;
    private  CallableStatement cs = null;

    private  String driver = "";
    private  String url = "";
    private  String userName = "";
    private  String password = "";

    private  Properties pp = null;
    private  FileInputStream fis = null;

    public  Connection getConn() {
        return conn;
    }

    public  PreparedStatement getPs() {
        return ps;
    }

    public  ResultSet getRs() {
        return rs;
    }
   
    public  CallableStatement getCs() {
        return cs;
    }
    
    
    public SqlHelper()
    {
    	try {
            // 从配置文件dbinfo.properties中读取配置信息
            pp = new Properties();
            System.out.println("3zcz");
            pp.load(SqlHelper.class.getResourceAsStream("dbinfo2.properties"));
            System.out.println("4zcz");
            // fis = new FileInputStream( );
           // pp.load(fis);
            driver = pp.getProperty("driver");
            System.out.println("driver" + driver);
            url = pp.getProperty("url");
            userName = pp.getProperty("userName");
            password = pp.getProperty("password");

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            fis = null;
        }
    }
    
    public SqlHelper(String prop)
    {
    	try {
            // 从配置文件dbinfo.properties中读取配置信息
            pp = new Properties();
            pp.load(SqlHelper.class.getResourceAsStream(prop ));
           // fis = new FileInputStream( );
           // pp.load(fis);
            driver = pp.getProperty("driver");
            url = pp.getProperty("url");
            userName = pp.getProperty("userName");
            password = pp.getProperty("password");

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            fis = null;
        }
    }
    // 加载驱动，只需要一次
     
        
     
public   int getRowCount(ResultSet aa){
	int rowCount = 0; 
	 try {
		while(aa.next()) { 
		   rowCount++; 
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return rowCount;
}
    // 得到连接
    public  Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 处理多个update/delete/insert
    public  void executeUpdateMultiParams(String[] sql,
            String[][] parameters) {
        try {
            // 获得连接
            conn = getConnection();
            // 可能传多条sql语句
            conn.setAutoCommit(false);
            for (int i = 0; i < sql.length; i++) {
                if (parameters[i] != null) {
                    ps = conn.prepareStatement(sql[i]);
                    for (int j = 0; j < parameters[i].length; j++)
                        ps.setString(j + 1, parameters[i][j]);
                }
                ps.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        } finally {
            // 关闭资源
            close(rs, ps, conn);
        }
    }

    // update/delete/insert
    // sql格式:UPDATE tablename SET columnn = ? WHERE column = ?
    public  void executeUpdate(String sql, String[] parameters) {
        try {
            // 1.创建一个ps
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            // 给？赋值
            if (parameters != null)
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            // 执行
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();// 开发阶段
            throw new RuntimeException(e.getMessage());
        } finally {
            // 关闭资源
            close(rs, ps, conn);
        }
    }

    public    ResultSet executeQuery(String sql) {
    	System.out.println(sql);
    	 ResultSet rs = null;
         try {
             conn = getConnection();
             ps = conn.prepareStatement(sql);
             rs = ps.executeQuery();
         } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException(e.getMessage());
         } finally {

         }
         return rs;
	}
    
    // select
    public  ResultSet executeQuery(String sql, String[] parameters) {
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return rs;
    }

    // 调用无返回值存储过程
    // 格式： call procedureName(parameters list)
    public  void callProc(String sql, String[] parameters) {
        try {
            conn = getConnection();
            cs = conn.prepareCall(sql);
            // 给？赋值
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++)
                    cs.setObject(i + 1, parameters[i]);
            }
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            // 关闭资源
            close(rs, cs, conn);
        }
    }

    // 调用带有输入参数且有返回值的存储过程
    public  CallableStatement callProcInput(String sql, String[] inparameters) {
        try {
            conn = getConnection();
            cs = conn.prepareCall(sql);
            if(inparameters!=null)
                for(int i=0;i<inparameters.length;i++)
                    cs.setObject(i+1, inparameters[i]);               
            cs.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally{
           
        }
        return cs;
    }
   
    // 调用有返回值的存储过程
    public  CallableStatement callProcOutput(String sql,Integer[] outparameters) {
        try {
            conn = getConnection();
            cs = conn.prepareCall(sql);                   
            //给out参数赋值
            if(outparameters!=null)
                for(int i=0;i<outparameters.length;i++)
                    cs.registerOutParameter(i+1, outparameters[i]);
            cs.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally{
           
        }
        return cs;
    }

    public  void close(ResultSet rs, Statement ps, Connection conn) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        rs = null;
        if (ps != null)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        ps = null;
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        conn = null;
    }
}

 /*
编写测试程序
package edu.xaut.wuqiang.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;//必须引入的

public class TestSqlHelper {
    // 测试SqlHelper
    @Test//每一个测试的方法前都要加@Test
    public void testSqlHelper1() {// 一条SQL语句insert/update/delete
        testInsert();
        testUpdate();
        testDelete();
    }

    @Test
    public void testSqlHelper2() {// 测试一个事务的提交
        testUpdateMuti();
    }

    @Test
    public void testSqlHelper3() {// 测试SQl的Select语句
        testQuery();
    }

    @Test
    public void testSqlHelper4() {// 测试调用无返回值的存储过程
        testInsertProc();
        testUpdateProc();
        testDeleteProc();
    }

    @Test
    public void testSqlHelper5() {// 测试调用有返回值的存储过程
        testCallProcOutput();
        testCallProcInput();
    }

    private void testCallProcInput() {
        ResultSet rs = null;
        try {
            String sql = "{call proc_userinfo_findByUsername(?)}";
            String[] in = { "Tom" };
            // Integer[] out ={Types.INTEGER};
            CallableStatement cs = (CallableStatement) SqlHelper.callProcInput(
                    sql, in);
            rs = cs.executeQuery();
            while (rs.next()) {
                System.out.println("username:" + rs.getString(2)
                        + "\tpassword:" + rs.getString(3) + "\tsalary:"
                        + rs.getDouble(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            SqlHelper.close(rs, SqlHelper.getCs(), SqlHelper.getConn());
        }
    }

    private void testCallProcOutput() {
        ResultSet rs = null;
        try {
            String sql = "{call proc_userinfo_getCount(?)}";
            Integer[] out = { Types.INTEGER };
            CallableStatement cs = (CallableStatement) SqlHelper
                    .callProcOutput(sql, out);
            rs = cs.executeQuery();
            while (rs.next()) {
                System.out.println("Record numbers:"+rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            SqlHelper.close(rs, SqlHelper.getCs(), SqlHelper.getConn());
        }
    }

    private void testDeleteProc() {
        String sql = "{call proc_userinfo_delete(?)}";
        String[] parameters = { "Jim" };
        SqlHelper.callProc(sql, parameters);
    }

    private void testUpdateProc() {
        String sql = "{call proc_userinfo_update(?,?,?)}";
        String[] parameters = { "Lucy", "ncist", "5200.00" };
        SqlHelper.callProc(sql, parameters);
    }

    private void testInsertProc() {
        String sql = "{call proc_userinfo_insert(?,?,?,?)}";
        String[] parameters = { "wYan", "wyan7", "female", "5600.00" };
        SqlHelper.callProc(sql, parameters);
    }

    private void testUpdateMuti() {
        String sql1 = "UPDATE userinfo SET salary=salary-100 WHERE username = ?";
        String sql2 = "UPDATE userinfo SET salary=salary+100 WHERE username = ?";
        String[] sql = { sql1, sql2 };
        String[] sql1_params = { "Tom" };
        String[] sql2_params = { "Jim" };
        String[][] parameters = { sql1_params, sql2_params };
        SqlHelper.executeUpdateMultiParams(sql, parameters);
    }

    private void testInsert() {
        String sql = "INSERT INTO userinfo (username,password,gender,salary) VALUES (?,?,?,?)";
        String[] parameters = { "wqiang", "wYan", "male", "6000.00" };
        SqlHelper.executeUpdate(sql, parameters);
    }

    private void testUpdate() {
        String sql = "UPDATE userinfo SET password=?,salary=? WHERE username = 'Jim'";
        String[] parameters = { "xaut", "6500.00" };
        SqlHelper.executeUpdate(sql, parameters);
    }

    private void testDelete() {
        String sql = "DELETE FROM userinfo WHERE username = ?";
        String[] parameters = { "xiaoqiang" };
        SqlHelper.executeUpdate(sql, parameters);
    }

    private void testQuery() {
        String sql = "SELECT * FROM userinfo";
        try {
            ResultSet rs = SqlHelper.executeQuery(sql, null);
            while (rs.next()) {
                System.out.println("userName:" + rs.getString("userName")
                        + "\tpassword:" + rs.getString("password")
                        + "\tgender:" + rs.getString("gender") + "\tsalary:"
                        + rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper
                    .getConn());
        }
    }
}
*/