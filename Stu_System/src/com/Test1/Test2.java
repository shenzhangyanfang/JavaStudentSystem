package com.Test1;
import java.util.*;

import javax.swing.*;

import java.sql.*;
/*
 * �����ݿ���ȡ������е�����
 */
public class Test2 extends JFrame{

	/**
	 * @param args
	 */
	//rowData�������������
	//columnNames�����������
	Vector rowData,columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	//����������ݿ���Ҫ�Ķ���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  new Test2();
	}
	//���캯��
		public Test2()
		{
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
			

			
			//��ʼ��JTable
			jt=new JTable(rowData,columnNames);
			
			//��ʼ��jsp
			jsp= new JScrollPane(jt);
			
			//��jsp���뵽JFrame��ȥ
			this.add(jsp);
			this.setSize(400, 300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}

}
