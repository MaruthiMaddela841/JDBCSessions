package com.callableStatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.statement.JDBCUtil;

public class BatchUpdateUsingPreparedStatement {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		int i=0;
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null) {
				String sqlQuery="INSERT INTO employees(name, age,address) VALUES (?,?,?);";
				ps=connection.prepareStatement(sqlQuery);
			}
			if(ps!=null) {
				sc=new Scanner(System.in);
				while(true) {
					System.out.println("Enter name:");
					String name=sc.next();
					System.out.println("Enter Age:");
					Integer age=sc.nextInt();
					System.out.println("Enter Address:");
					String address=sc.next();
					ps.setString(1, name);
					ps.setInt(2, age);
					ps.setString(3, address);
					ps.addBatch();
					System.out.println("Do you want to insert another Query:Yes/No");
					String option=sc.next();
					if(option.equalsIgnoreCase("no")) {
						break;
					}
				}
				ps.executeBatch();
				System.out.println("Records Insertion Successfull");
			}
			}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			System.out.println("Closing the resources....");
			JDBCUtil.cleanUp(connection, ps, null);
			
		}
	}

}
