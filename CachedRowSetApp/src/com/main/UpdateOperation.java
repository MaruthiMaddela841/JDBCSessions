package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class UpdateOperation {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rsf= RowSetProvider.newFactory();
		CachedRowSet crs=rsf.createCachedRowSet();
		crs.setUrl("jdbc:mysql://localhost:3306/jdbcsessions");
		crs.setUsername("root");
		crs.setPassword("Maru@841");
		crs.setCommand("select id,name,age,address,salary from employees;");
		crs.execute();

		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
		while(crs.next()) {
			int actualSalary=crs.getInt(5);
			if(actualSalary<5000) {
				int updatedSalary=actualSalary+1000;
				crs.updateInt(5, updatedSalary);
				crs.updateRow();
			}
		}
		System.out.println("Record Updated Successfully....");
		
		
		System.out.println();
		System.out.println("Retrieving the records after update....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
		crs.close();
		
		}

}
