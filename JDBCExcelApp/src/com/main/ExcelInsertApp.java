package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcelInsertApp {

	private static final String EXCEL_FILE = "INSERT INTO data.student values(?,?,?);";

	public static void main(String[] args) throws SQLException {
		
		Scanner sc=null;
		String name=null,address=null;
		Integer id=null;
		try {
			sc=new Scanner(System.in);
			System.out.println("Enter Id:");
			id=sc.nextInt();
			System.out.println("Enter Name:");
			name=sc.next();
			System.out.println("Enter Address:");
			address=sc.next();
		}
		catch(Exception e) {
		e.printStackTrace();	
		}
		finally {
			if(sc!=null) {
				sc.close();
			}
		}
		String url="jdbc:Excel:///D:\\iNeuron";
		try(Connection connection=DriverManager.getConnection(url)) {
			try (PreparedStatement ps=connection.prepareStatement(EXCEL_FILE)){
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, address);
				int count=ps.executeUpdate();
				if(count==0) {
					System.out.println("Record not inserted...");
				}
				else {
					System.out.println("Record Inserted...");
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
