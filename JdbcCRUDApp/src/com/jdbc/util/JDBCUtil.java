package com.jdbc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCUtil {
	
	private JDBCUtil() {
		//created private as this is a utility class we made every method as static
		// and preveneting object creation by private constructor
	}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getJDBCConnection() throws SQLException, IOException{
		// TODO Auto-generated method stub
		
//		FileInputStream fis= new FileInputStream("D:\\iNeuron\\Git\\JdbcCRUDApp\\src\\com\\properties\\application.properties");
//		Properties properties= new Properties();
//		properties.load(fis);
//		
//		String url=properties.getProperty("url");
//		String username=properties.getProperty("username");
//		String password=properties.getProperty("password");
//		Connection connection=DriverManager.getConnection(url,username,password);
		
		HikariConfig config= new HikariConfig("src\\com\\properties\\application.properties");
		HikariDataSource dataSource= new HikariDataSource(config);
		
		System.out.println("Connection Established.....");
		return dataSource.getConnection();
	}
	
	public static void cleanUp(Connection connection,Statement statement,ResultSet resultSet) throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		if(statement!=null) {
			statement.close();
		}
		if(resultSet!=null) {
			resultSet.close();
		}
	}

}
