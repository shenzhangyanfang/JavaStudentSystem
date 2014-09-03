package com.Test1;
/*
 * 这是我的一个Student表的模型
 * 可以吧对stud表的各种操作封装到该模型中
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.*;
public class StudentModel extends AbstractTableModel{

	//rowData用来存放行数据
			//columnNames用来存放列名
			Vector rowData,columnNames;
			JTable jt = null;

			//定义操作数据库需要的东西
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from stud";
		}
		//中间这块代码
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
			ps=ct.prepareStatement(sql);
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
	}
		public void addStu(String sql)
		{
			//根据用户输入的sql语句完成添加任务
			
		}
		//通过sql语句完成查询任务
		public StudentModel(String sql)
		{
			this.init(sql);
		}
		//构造函数，用于初始化我们的数据模型
		public StudentModel()
		{
			this.init("");
		}
			
	//得到共有多少列
	public int getColumnCount()
	{
		
		return this.columnNames.size();
	}
	//得到共有多少行
	public int getRowCount()
	{
		return this.rowData.size();
	}
	//得到某行某列的数据
	public Object getValueAt(int row,int column)
	{
		return ((Vector)this.rowData.get(row)).get(column);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}
}
