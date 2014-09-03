/*
 * �����������Ա����ϵͳ  MISϵͳ���ǹ���ϵͳ
 * ��model1ģʽ��Ϊmodel2ģʽ��model2ģʽ��mvģʽ��
 */
package com.Test2;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class StuView extends JFrame implements ActionListener{

	StudentModel sm;
    //����һЩ�ؼ�
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt1;
	JScrollPane jsp;
	JTextField jtf;
	
	
	public static void main(String[] args) {
		StuView stuview = new StuView();

	}
	//���캯��
	public StuView()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("����������");
		
		//�Ѹ����ؼ����뵽jp1��
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		
		jp2=new JPanel();
		
		jb2=new JButton("���");
		
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		
		jb4.addActionListener(this);
		
		//�Ѹ����ؼ����뵽jp2��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//����һ������ģ�Ͷ���
		 sm = new StudentModel();
		 String[] paras={"1"};
		sm.queryStu("select * from stud where 1=?", paras);
		//��ʼ��JTable
		jt1=new JTable(sm);
		
		//��ʼ��jsp
		jsp= new JScrollPane(jt1);
		
		//��jsp���뵽JFrame��ȥ
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//�ж��ĸ���ť�����
		if(arg0.getSource()==jb1)
		{
			System.out.println("�û����ѯ");
			//��Ϊ�ѶԱ�����ݷ�װ��StudentModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ��
			String name=this.jtf.getText().trim();
			//trim()���������ù��˿��ַ���
			//дһ��sql���
			String sql="select * from stud where stuName = ?";
			String paras[] ={name}; 
			//�����µ�����ģ���࣬������
			 sm =  new StudentModel();
			 sm.queryStu(sql, paras);
			//����JTable
			jt1.setModel(sm);
		}
		//���û������Ӱ�ťʱ����Ӧʵ��
		else if(arg0.getSource()==jb2)
		{
			StuAddDialog sa = new StuAddDialog(this,"���ѧ��",true);
			//�����µ�����ģ���࣬������
			 sm =  new StudentModel();
			 String []paras2 ={"1"};
			 sm.queryStu("select * from stud where 1=?", paras2);
			//����JTable
			jt1.setModel(sm);
		}
		else if(arg0.getSource()==jb3)
		{
			//�û�ϣ���޸�
			int rowNum = this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//��ʾ
				JOptionPane.showMessageDialog(this,"��ѡ��һ��");
				return ;	
			}
			//��ʾ�޸ĶԻ���
			new StuUpdDialog(this,"�޸�ѧ��",true,sm,rowNum);
			//�����µ�����ģ���࣬������
			 sm =  new StudentModel();
			 String []paras2 ={"1"};
			 sm.queryStu("select * from stud where 1=?", paras2);
			//����JTable
			jt1.setModel(sm);
		}
		else if(arg0.getSource()==jb4)
		{
			//˵���û�ϣ��ɾ����¼
			//1���õ���ѧ����id��
			//getSelectedRow()�᷵���û����е��У�������û�һ�ж�û��ѡ�У��ͷ���-1��
			int rowNum = this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//��ʾ
				JOptionPane.showMessageDialog(this,"��ѡ��һ��");
				return ;
			}
			//�õ�ѧ�����
			String stuId =(String) sm.getValueAt(rowNum, 0);
			
			System.out.println("stuId"+stuId);
			//����һ��sql���
			String sql="delete from stud where stuId=?";
			String[] paras = {stuId};
			StudentModel temp = new StudentModel();
			temp.updateStu(sql, paras);
			
			//�����µ�����ģ���࣬������
			 sm =  new StudentModel();
			 String []paras2 ={"1"};
			 sm.queryStu("select * from stud where 1=?", paras2);
			//����JTable
			jt1.setModel(sm);
		}
		
	}

}
