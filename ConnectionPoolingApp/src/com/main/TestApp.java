package com.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {
		
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setUrl("jdbc:mysql:///jdbcsessions");
		dataSource.setUser("root");
		dataSource.setPassword("Maru@841");
		Connection connection=dataSource.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select id,name,age,address,salary from employees;");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getInt(5));
		}
		connection.close();
		
		}

}
