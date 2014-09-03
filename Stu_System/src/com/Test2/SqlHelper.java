/*
 * 这是一个对数据库进行操作的类(SqlHelper).
 */
package com.Test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHelper {
	
	//定义操作数据库需要的东西
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url="jdbc:mysql://localhost:3306/chat";
	String user="root";
	String passwd="19900811";
	String driver="com.mysql.jdbc.Driver";
	
	//关闭数据库资源
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	//写一个不需要注入的方法
	public ResultSet queryExecute(String sql)
	{
		try
		{
			//1、加载驱动
		    Class.forName(driver);
		    //2、得到连接
		     ct=DriverManager.getConnection(url,user,passwd);
			//3、注册驱动程序，打开连接对象(创建ps)
			 ps=ct.prepareStatement(sql);
			//循环的给?赋值.
			 
			 rs=ps.executeQuery();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}finally
		{
			////没有办法在此处关闭资源，必须有业务逻辑层用完后在关闭资源
		}
		return rs;
	}
	
	//查询数据库的操作
	public ResultSet queryExecute(String sql,String[] paras)
	{
		try
		{
			//1、加载驱动
		    Class.forName(driver);
		    //2、得到连接
		     ct=DriverManager.getConnection(url,user,passwd);
			//3、注册驱动程序，打开连接对象(创建ps)
			 ps=ct.prepareStatement(sql);
			//循环的给?赋值.
			 for(int i=0;i<paras.length;i++)
			 {
				 ps.setString(i+1,paras[i]);
			 }
			 rs=ps.executeQuery();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}finally
		{
			////没有办法在此处关闭资源，必须有业务逻辑层用完后在关闭资源
		}
		return rs;
	}
	
	//把增删改放在一起
	public boolean updExecute(String sql,String[] paras)
	{
		boolean b = true;
		//对用户点击添加按钮后的响应
		try {
			//1、加载驱动
		    Class.forName(driver);
		    //2、得到连接
		     ct=DriverManager.getConnection(url,user,passwd);
			//3、注册驱动程序，打开连接对象(创建ps)
			 ps=ct.prepareStatement(sql);
		    //循环的给?赋值.
			 for(int i=0;i<paras.length;i++)
			 {
				 ps.setString(i+1,paras[i]);
			 }
			 
		    //4、执行操作
		    if(ps.executeUpdate()!=1)
		    {
		    	b=false;
		    }
		    
		 } 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			b=false;
			e1.printStackTrace();
		}
		finally
		{
			this.close();
		}
		return b;
	}
}
