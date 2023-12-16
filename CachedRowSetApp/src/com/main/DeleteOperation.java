package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DeleteOperation {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rsf= RowSetProvider.newFactory();
		JdbcRowSet jrs=rsf.createJdbcRowSet();
		jrs.setUrl("jdbc:mysql://localhost:3306/jdbcsessions");
		jrs.setUsername("root");
		jrs.setPassword("Maru@841");
		jrs.setCommand("select id,name,age,address,salary from employees;");
		jrs.execute();
		while(jrs.next()) {
			int actualSalary=jrs.getInt(5);
			if(actualSalary<5000) {
				jrs.deleteRow();
			}
		}
		System.out.println("Records Deleted Successfully....");
		jrs.close();
		
		}

}
