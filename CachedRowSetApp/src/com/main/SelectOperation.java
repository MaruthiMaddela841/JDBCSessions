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
		CachedRowSet crs=rsf.createCachedRowSet();
		crs.setUrl("jdbc:mysql://localhost:3306/jdbcsessions");
		crs.setUsername("root");
		crs.setPassword("Maru@841");
		crs.setCommand("select id,name,age,address,salary from employees;");
		crs.execute();
		System.out.println("Retrieving the records in forward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
		System.out.println();
		System.out.println("Retrieving the records in backward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(crs.previous()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
		
		}

}
