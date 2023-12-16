package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class UpdateOperation {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rsf= RowSetProvider.newFactory();
		JdbcRowSet jrs=rsf.createJdbcRowSet();
		jrs.setUrl("jdbc:mysql://localhost:3306/jdbcsessions");
		jrs.setUsername("root");
		jrs.setPassword("Maru@841");
		jrs.setCommand("select id,name,age,address,salary from employees;");
		jrs.execute();

		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(jrs.next()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		while(jrs.next()) {
			int actualSalary=jrs.getInt(5);
			if(actualSalary<5000) {
				int updatedSalary=actualSalary+1000;
				jrs.updateInt(5, updatedSalary);
				jrs.updateRow();
			}
		}
		System.out.println("Record Updated Successfully....");
		
		
		System.out.println();
		System.out.println("Retrieving the records after update....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(jrs.next()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		jrs.close();
		
		}

}
