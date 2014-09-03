package com.Test1;

/*
 * �޸�ѧ�����������ѧ������ǳ�����
 */
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
public class StuUpdDialog extends JDialog implements ActionListener{
	
	//��������Ҫ��swing���
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	//owner���ĸ�����
	//title���Ĵ�����
	//model�ƶ�����ģʽ���ڣ����Ƿ�ģʽ����
	public StuUpdDialog(Frame owner,String title,boolean model,StudentModel sm,int rowNum)
	{
		super(owner,title,model);//���ø��๹�췽�����ﵽģʽ�Ի���
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("����");
		jl6=new JLabel("Ժϵ");
		
		//��ʼ������
		jtf1=new JTextField();		
		jtf1.setText((String)sm.getValueAt(rowNum, 0));
		jtf1.setEditable(false);
		jtf2=new JTextField();
		jtf2.setText((String)sm.getValueAt(rowNum, 1));
		jtf3=new JTextField();
		jtf3.setText((String)sm.getValueAt(rowNum, 2));
		jtf4=new JTextField();
		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
		jtf5=new JTextField();
		jtf5.setText((String)sm.getValueAt(rowNum, 4));
		jtf6=new JTextField();
		jtf6.setText((String)sm.getValueAt(rowNum, 5));
		
		jb1=new JButton("�޸�");
		jb2=new JButton("ȡ��");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//���ò���
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		//�������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		//չ��
		this.setSize(300,250);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			//���û����"�޸�"��ť�����Ӧ
			Connection ct = null;
			Statement stmt = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try {
				//1����������
			    Class.forName("com.mysql.jdbc.Driver");
			    //2���������Ӷ���
			    String url ="jdbc:mysql://localhost:3306/chat";
				//3��ע���������򣬴����Ӷ���
			    ct=DriverManager.getConnection(url,"root","19900811");
				
			    //�����������
			    String strsql="update stud set stuName=? , stuSex=? ," +
			    		"stuAge=? ,stuJg=? ,stuDept=? where stuId=?";
			   
			    pstmt=ct.prepareStatement(strsql);
				
				//��?��ֵ
			    pstmt.setString(1, jtf2.getText());
			    pstmt.setString(2, jtf3.getText());
			    pstmt.setString(3, jtf4.getText());
			    pstmt.setString(4, jtf5.getText());
			    pstmt.setString(5, jtf6.getText());
			    pstmt.setString(6, jtf1.getText());
				
			    //4��ִ�в���
			    pstmt.executeUpdate();
				
				this.dispose();//�ر�����ѧ���Ի���
			    
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
					try {
						if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(ct!=null) ct.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
			}
		}
	}
	
}