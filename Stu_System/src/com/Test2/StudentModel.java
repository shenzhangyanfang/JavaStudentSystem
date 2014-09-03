package com.Test2;
/*
 * �����ҵ�һ��Student���ģ��
 * ���԰ɶ�stud��ĸ��ֲ�����װ����ģ����
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

	//rowData�������������
	//columnNames�����������
			Vector rowData,columnNames;
			JTable jt = null;

			
	//���ѧ��(����ɾ����ȫ�������Է���������)
		public boolean updateStu(String sql,String []paras)
		{
			//����һ��SqlHelper��������򲢷��Բ����ǣ����԰�SqlHelper���ɾ�̬��
			SqlHelper sqlHelper = new SqlHelper();
			return sqlHelper.updExecute(sql, paras);
		}
		//��ѯ�ı��ʾ��ǳ�ʼ��
	public void queryStu(String sql,String []paras)
	{
		
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
			
			//���뵽rowData
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
