package com.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUDOperations {
	static Connection connection=null;
	static Statement statement=null;
	static ResultSet resultSet=null;
	static Scanner sc=null;
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {
			String url="jdbc:mysql://localhost:3306/jdbcsessions";
			String username="root";
			String password="Maru@841";
			connection=DriverManager.getConnection(url,username,password);
			statement=connection.createStatement();
			System.out.println("Select Operation Press 1");
			System.out.println("Insert Operation Press 2");
			System.out.println("Update Operation Press 3");
			System.out.println("Delete Operation Press 4");
			System.out.println("Select Id Operation Press 5");
			sc=new Scanner(System.in);
			int input=sc.nextInt();
			switch(input) {
			case 1: selectQuery();
			break;
			case 2: InsertQuery();
			break;
			case 3: updateQuery();
			break;
			case 4: deleteQuery();
			break;
			case 5: selectedRecordQuery();
			break;
			default: System.out.println("Invalid Input");
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			statement.close();
		}
	}

	private static void selectedRecordQuery() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Id of the record to get the resultSet:");
		String idInput=sc.next();
		resultSet=statement.executeQuery("select * from students where id='"+idInput+"';");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while(resultSet.next()) {
			Integer id=resultSet.getInt(1);
			String name=resultSet.getString(2);
			Integer age=resultSet.getInt(3);
			String address=resultSet.getString(4);
			System.out.println(id+"\t"+name+"\t"+age+"\t"+address);
		}
	}

	private static void deleteQuery() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter id of the record to delete:");
		String id=sc.next();
		int i=statement.executeUpdate("delete from students where id="+id+";");
		System.out.println("Rows effected:"+i);
	}

	private static void updateQuery() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("enter what column to update:");
		String updatingColumn=sc.next();
		System.out.println("enter what value to update in that column:");
		String updatingColumnValue=sc.next();
		System.out.println("Enter name to insert:");
		String name=sc.next();
		int i=statement.executeUpdate("Update students set "+updatingColumn+"='"+updatingColumnValue+"' where name='"+name+"';");
		System.out.println("Rows effected:"+i);
	}

	private static void InsertQuery() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter name to insert:");
		String name=sc.next();
		System.out.println("Enter age to insert:");
		String age=sc.next();
		System.out.println("Enter address to insert:");
		String address=sc.next();
		int i=statement.executeUpdate("INSERT INTO students(name, age, address) VALUES ('"+name+"',"+age+", '"+address+"');");
		System.out.println("Rows effected:"+i);
	}

	private static void selectQuery() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		resultSet=statement.executeQuery("select * from students;");
		while(resultSet.next()) {
			Integer id=resultSet.getInt(1);
			String name=resultSet.getString(2);
			Integer age=resultSet.getInt(3);
			String address=resultSet.getString(4);
			System.out.println(id+"\t"+name+"\t"+age+"\t"+address);
		}
	}
}
