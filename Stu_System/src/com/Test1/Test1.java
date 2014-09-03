package com.Test1;
import javax.swing.*;
import java.util.*;
/*
 * JTable的使用.
 */
public class Test1 extends JFrame{

	//rowData用来存放行数据
	//columnNames存放列名
	Vector rowData,columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1();
	}
	//构造函数
	public Test1()
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
		Vector hang = new Vector();
		hang.add("13111373");
		hang.add("张彦芳");
		hang.add("女");
		hang.add("22");
		hang.add("河南省");
		hang.add("软件学院");
		
		//加入到rowData
		rowData.add(hang);
		
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
