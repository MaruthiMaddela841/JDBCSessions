package com.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {
		
		String configFile="C:\\Users\\marut\\eclipse-workspace\\HikariCPConnectionPoolingApp\\src\\com\\main\\db.properties";
		HikariConfig config=new HikariConfig(configFile);
		
		try(HikariDataSource dataSource= new HikariDataSource(config)){
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

}
