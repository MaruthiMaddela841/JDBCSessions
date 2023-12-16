package com.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class SelectOperation {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rsf= RowSetProvider.newFactory();
		JdbcRowSet jrs=rsf.createJdbcRowSet();
		jrs.setUrl("jdbc:mysql://localhost:3306/jdbcsessions");
		jrs.setUsername("root");
		jrs.setPassword("Maru@841");
		jrs.setCommand("select id,name,age,address,salary from employees;");
		jrs.execute();
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
