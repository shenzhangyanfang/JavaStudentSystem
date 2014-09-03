package com.Test1;

/*
 * 修改学生界面和添加学生界面非常相似
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
	
	//定义我需要的swing组件
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	//owner它的父窗口
	//title它的窗口名
	//model制定的是模式窗口，还是非模式窗口
	public StuUpdDialog(Frame owner,String title,boolean model,StudentModel sm,int rowNum)
	{
		super(owner,title,model);//调用父类构造方法，达到模式对话框
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("籍贯");
		jl6=new JLabel("院系");
		
		//初始化数据
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
		
		jb1=new JButton("修改");
		jb2=new JButton("取消");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		//添加组件
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
		//展现
		this.setSize(300,250);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			//对用户点击"修改"按钮后的响应
			Connection ct = null;
			Statement stmt = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try {
				//1、加载驱动
			    Class.forName("com.mysql.jdbc.Driver");
			    //2、创建连接对象
			    String url ="jdbc:mysql://localhost:3306/chat";
				//3、注册驱动程序，打开连接对象
			    ct=DriverManager.getConnection(url,"root","19900811");
				
			    //与编译语句对象
			    String strsql="update stud set stuName=? , stuSex=? ," +
			    		"stuAge=? ,stuJg=? ,stuDept=? where stuId=?";
			   
			    pstmt=ct.prepareStatement(strsql);
				
				//给?赋值
			    pstmt.setString(1, jtf2.getText());
			    pstmt.setString(2, jtf3.getText());
			    pstmt.setString(3, jtf4.getText());
			    pstmt.setString(4, jtf5.getText());
			    pstmt.setString(5, jtf6.getText());
			    pstmt.setString(6, jtf1.getText());
				
			    //4、执行操作
			    pstmt.executeUpdate();
				
				this.dispose();//关闭添加学生对话框
			    
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