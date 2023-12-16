package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class TestApp {

	public static void main(String[] args) throws SQLException {
		
		Connection connection=DriverManager.getConnection("jdbc:mysql:///jdbcsessions","root","Maru@841");
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select id,name,age,address,salary from employees;");
		RowSetFactory rsf= RowSetProvider.newFactory();
		CachedRowSet crs=rsf.createCachedRowSet();
		crs.populate(resultSet);
		connection.close();
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}

	}

}
