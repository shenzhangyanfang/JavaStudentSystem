/*
 * 迷你版的宿舍成员管理系统  MIS系统就是管理系统
 * 由model1模式改为model2模式。model2模式是mv模式。
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
    //定义一些控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt1;
	JScrollPane jsp;
	JTextField jtf;
	
	
	public static void main(String[] args) {
		StuView stuview = new StuView();

	}
	//构造函数
	public StuView()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入名字");
		
		//把各个控件加入到jp1中
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		
		jp2=new JPanel();
		
		jb2=new JButton("添加");
		
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		
		jb4.addActionListener(this);
		
		//把各个控件加入到jp2中
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//创建一个数据模型对象
		 sm = new StudentModel();
		 String[] paras={"1"};
		sm.queryStu("select * from stud where 1=?", paras);
		//初始化JTable
		jt1=new JTable(sm);
		
		//初始化jsp
		jsp= new JScrollPane(jt1);
		
		//把jsp放入到JFrame中去
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//判断哪个按钮被点击
		if(arg0.getSource()==jb1)
		{
			System.out.println("用户想查询");
			//因为把对表的数据封装到StudentModel中，我们就可以比较简单的完成查询。
			String name=this.jtf.getText().trim();
			//trim()函数的作用过滤空字符串
			//写一个sql语句
			String sql="select * from stud where stuName = ?";
			String paras[] ={name}; 
			//构建新的数据模型类，并更新
			 sm =  new StudentModel();
			 sm.queryStu(sql, paras);
			//更新JTable
			jt1.setModel(sm);
		}
		//当用户点击添加按钮时的响应实践
		else if(arg0.getSource()==jb2)
		{
			StuAddDialog sa = new StuAddDialog(this,"添加学生",true);
			//构建新的数据模型类，并更新
			 sm =  new StudentModel();
			 String []paras2 ={"1"};
			 sm.queryStu("select * from stud where 1=?", paras2);
			//更新JTable
			jt1.setModel(sm);
		}
		else if(arg0.getSource()==jb3)
		{
			//用户希望修改
			int rowNum = this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return ;	
			}
			//显示修改对话框
			new StuUpdDialog(this,"修改学生",true,sm,rowNum);
			//构建新的数据模型类，并更新
			 sm =  new StudentModel();
			 String []paras2 ={"1"};
			 sm.queryStu("select * from stud where 1=?", paras2);
			//更新JTable
			jt1.setModel(sm);
		}
		else if(arg0.getSource()==jb4)
		{
			//说明用户希望删除记录
			//1、得到该学生的id号
			//getSelectedRow()会返回用户点中的行，如果该用户一行都没有选中，就返回-1；
			int rowNum = this.jt1.getSelectedRow();
			if(rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return ;
			}
			//得到学生编号
			String stuId =(String) sm.getValueAt(rowNum, 0);
			
			System.out.println("stuId"+stuId);
			//创建一个sql语句
			String sql="delete from stud where stuId=?";
			String[] paras = {stuId};
			StudentModel temp = new StudentModel();
			temp.updateStu(sql, paras);
			
			//构建新的数据模型类，并更新
			 sm =  new StudentModel();
			 String []paras2 ={"1"};
			 sm.queryStu("select * from stud where 1=?", paras2);
			//更新JTable
			jt1.setModel(sm);
		}
		
	}

}
