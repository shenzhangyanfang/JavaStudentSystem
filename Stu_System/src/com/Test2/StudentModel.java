package com.Test2;
/*
 * 这是我的一个Student表的模型
 * 可以吧对stud表的各种操作封装到该模型中
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.*;
public class StudentModel extends AbstractTableModel{

	//rowData用来存放行数据
	//columnNames用来存放列名
			Vector rowData,columnNames;
			JTable jt = null;

			
	//添加学生(增、删、改全部都可以放置在这里)
		public boolean updateStu(String sql,String []paras)
		{
			//创建一个SqlHelper，如果程序并发性不考虑，可以吧SqlHelper做成静态的
			SqlHelper sqlHelper = new SqlHelper();
			return sqlHelper.updExecute(sql, paras);
		}
		//查询的本质就是初始化
	public void queryStu(String sql,String []paras)
	{
		
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
		SqlHelper sqlHelper =null;
		ResultSet rs;
		try {
			sqlHelper= new SqlHelper();
			rs=sqlHelper.queryExecute(sql, paras);

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
				sqlHelper.close();
			
		}
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
