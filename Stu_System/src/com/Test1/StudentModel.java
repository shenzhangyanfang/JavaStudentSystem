package com.Test1;
/*
 * �����ҵ�һ��Student���ģ��
 * ���԰ɶ�stud��ĸ��ֲ�����װ����ģ����
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

	//rowData�������������
			//columnNames�����������
			Vector rowData,columnNames;
			JTable jt = null;

			//����������ݿ���Ҫ�Ķ���
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from stud";
		}
		//�м�������
		columnNames =new Vector();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData = new Vector();
		//rowData���Դ�Ŷ���
		
		try {
			//��������
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
			
			//���뵽rowData
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
			//�����û������sql�������������
			
		}
		//ͨ��sql�����ɲ�ѯ����
		public StudentModel(String sql)
		{
			this.init(sql);
		}
		//���캯�������ڳ�ʼ�����ǵ�����ģ��
		public StudentModel()
		{
			this.init("");
		}
			
	//�õ����ж�����
	public int getColumnCount()
	{
		
		return this.columnNames.size();
	}
	//�õ����ж�����
	public int getRowCount()
	{
		return this.rowData.size();
	}
	//�õ�ĳ��ĳ�е�����
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
