package com.Test1;
import javax.swing.*;
import java.util.*;
/*
 * JTable��ʹ��.
 */
public class Test1 extends JFrame{

	//rowData�������������
	//columnNames�������
	Vector rowData,columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1();
	}
	//���캯��
	public Test1()
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
		Vector hang = new Vector();
		hang.add("13111373");
		hang.add("���巼");
		hang.add("Ů");
		hang.add("22");
		hang.add("����ʡ");
		hang.add("���ѧԺ");
		
		//���뵽rowData
		rowData.add(hang);
		
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
