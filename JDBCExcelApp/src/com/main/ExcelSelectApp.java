package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.util.JDBCUtil;

public class ExcelSelectApp {

	private static final String EXCEL_FILE = "SELECT * FROM data.student;";

	public static void main(String[] args) throws SQLException {
		String url="jdbc:Excel:///D:\\iNeuron";
		try(Connection connection=DriverManager.getConnection(url)) {
			try (PreparedStatement ps=connection.prepareStatement(EXCEL_FILE)){
				try(ResultSet resultSet=ps.executeQuery()){
					while(resultSet.next()) {
						System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}

	}

}
