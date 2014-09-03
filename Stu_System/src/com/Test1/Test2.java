package com.Test1;
import java.util.*;

import javax.swing.*;

import java.sql.*;
/*
 * 从数据库中取出表格中的内容
 */
public class Test2 extends JFrame{

	/**
	 * @param args
	 */
	//rowData用来存放行数据
	//columnNames用来存放列名
	Vector rowData,columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	//定义操作数据库需要的东西
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  new Test2();
	}
	//构造函数
		public Test2()
		{
			columnNames =new Vector();
			//设置列名
			columnNames.add("学号");
			columnNames.add("姓名");
			columnNames.add("性别");
			columnNames.add("年龄");
			columnNames.add("籍贯");
			columnNames.add("系别");
			
			rowData = new Vector();
			//rowData可以存放多行
			
			try {
				//加载驱动
			    Class.forName("com.mysql.jdbc.Driver");
				ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","19900811");
				ps=ct.prepareStatement("select * from stud");
				
				rs=ps.executeQuery();
				
				
				while(rs.next())
				{	
				Vector hang = new Vector();	
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				//加入到rowData
				rowData.add(hang);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
					try {
						if(rs!=null) rs.close();
						if(ps!=null) ps.close();
						if(ct!=null) ct.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			

			
			//初始化JTable
			jt=new JTable(rowData,columnNames);
			
			//初始化jsp
			jsp= new JScrollPane(jt);
			
			//把jsp放入到JFrame中去
			this.add(jsp);
			this.setSize(400, 300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}

}
