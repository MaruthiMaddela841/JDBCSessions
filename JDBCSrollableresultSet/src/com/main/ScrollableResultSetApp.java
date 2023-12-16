package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbc.util.JDBCUtil;

public class ScrollableResultSetApp {

	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		Statement statement=null;	
		ResultSet resultSet=null;
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query="select id,name,age,address from employees";
			if(statement!=null)
				resultSet=statement.executeQuery(query);
			if(resultSet!=null) {
				System.out.println("MOVING IN FORWARD DIRECTION");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while(resultSet.next()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				}
				System.out.println();
				System.out.println("MOVING IN BACKWARD DIRECTION");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while(resultSet.previous()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				}
				System.out.println();
				System.out.println("MOVED TO FIRST RECORD");
				resultSet.first();
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				System.out.println();
				System.out.println("MOVED TO LAST RECORD");
				resultSet.last();
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				System.out.println();
				System.out.println("MOVED TO 3RD RECORD");
				resultSet.first();
				resultSet.absolute(3);
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				System.out.println();
				System.out.println("MOVING TO RELATIVE POSITION RECORD FROM CURRENT POSITION RECORD");
				resultSet.relative(2);
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				System.out.println();
				System.out.println("MOVING TO LAST RECORD THROUGH NEGATIVE INPUT");
				resultSet.absolute(-1);
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				System.out.println();
				System.out.println("MOVING TO PREVIOUS RECORD THROUGH NEGATIVE INPUT");
				resultSet.first();
				resultSet.absolute(3);
				resultSet.relative(-1);
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
			}
			
			
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.cleanUp(connection, statement, resultSet);
		}
	}

}
