package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class InsertOperation {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rsf= RowSetProvider.newFactory();
		JdbcRowSet jrs=rsf.createJdbcRowSet();
		jrs.setUrl("jdbc:mysql://localhost:3306/jdbcsessions");
		jrs.setUsername("root");
		jrs.setPassword("Maru@841");
		jrs.setCommand("select id,name,age,address,salary from employees;");
		jrs.execute();
		Scanner sc=new Scanner(System.in);
		jrs.moveToInsertRow();
		while(true) {
			System.out.println("Enter Name:");
			String name=sc.next();
			System.out.println("Enter Age:");
			Integer age=sc.nextInt();
			System.out.println("Enter Address:");
			String address=sc.next();
			System.out.println("Enter Salary:");
			Integer salary=sc.nextInt();
			jrs.updateString(2, name);
			jrs.updateInt(3, age);
			jrs.updateString(4, address);
			jrs.updateInt(5, salary);
			jrs.insertRow();
			System.out.println("Rocord Inserted Successfully....");
			System.out.println("Do you want to insert more records? Yes/No");
			String input=sc.next();
			if(input.equalsIgnoreCase("no"))
				break;
		}
		
		
		
		System.out.println("Retrieving the records in forward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(jrs.next()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		System.out.println();
		System.out.println("Retrieving the records in backward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(jrs.previous()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		System.out.println();
		
		jrs.first();
		jrs.absolute(3);
		System.out.println("ABSOLUTE");
		System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		jrs.relative(2);
		System.out.println("RELATIVE");
		System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		
		
		}

}
