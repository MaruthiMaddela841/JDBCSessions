package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.util.JDBCUtil;

public class CSVSelectApp {

	private static final String EXCEL_CSV = "SELECT * FROM data.csv;";

	public static void main(String[] args) throws SQLException {
		String url="jdbc:Text:///D:\\iNeuron";
		try(Connection connection=DriverManager.getConnection(url)) {
			try (PreparedStatement ps=connection.prepareStatement(EXCEL_CSV)){
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
