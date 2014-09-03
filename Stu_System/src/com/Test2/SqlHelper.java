/*
 * ����һ�������ݿ���в�������(SqlHelper).
 */
package com.Test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHelper {
	
	//����������ݿ���Ҫ�Ķ���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url="jdbc:mysql://localhost:3306/chat";
	String user="root";
	String passwd="19900811";
	String driver="com.mysql.jdbc.Driver";
	
	//�ر����ݿ���Դ
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
	//дһ������Ҫע��ķ���
	public ResultSet queryExecute(String sql)
	{
		try
		{
			//1����������
		    Class.forName(driver);
		    //2���õ�����
		     ct=DriverManager.getConnection(url,user,passwd);
			//3��ע���������򣬴����Ӷ���(����ps)
			 ps=ct.prepareStatement(sql);
			//ѭ���ĸ�?��ֵ.
			 
			 rs=ps.executeQuery();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}finally
		{
			////û�а취�ڴ˴��ر���Դ��������ҵ���߼���������ڹر���Դ
		}
		return rs;
	}
	
	//��ѯ���ݿ�Ĳ���
	public ResultSet queryExecute(String sql,String[] paras)
	{
		try
		{
			//1����������
		    Class.forName(driver);
		    //2���õ�����
		     ct=DriverManager.getConnection(url,user,passwd);
			//3��ע���������򣬴����Ӷ���(����ps)
			 ps=ct.prepareStatement(sql);
			//ѭ���ĸ�?��ֵ.
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
			////û�а취�ڴ˴��ر���Դ��������ҵ���߼���������ڹر���Դ
		}
		return rs;
	}
	
	//����ɾ�ķ���һ��
	public boolean updExecute(String sql,String[] paras)
	{
		boolean b = true;
		//���û������Ӱ�ť�����Ӧ
		try {
			//1����������
		    Class.forName(driver);
		    //2���õ�����
		     ct=DriverManager.getConnection(url,user,passwd);
			//3��ע���������򣬴����Ӷ���(����ps)
			 ps=ct.prepareStatement(sql);
		    //ѭ���ĸ�?��ֵ.
			 for(int i=0;i<paras.length;i++)
			 {
				 ps.setString(i+1,paras[i]);
			 }
			 
		    //4��ִ�в���
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
