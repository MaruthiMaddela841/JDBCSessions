package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbc.util.JDBCUtil;

public class TransactionApp {

	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		Statement statement=null;	
		ResultSet resultSet=null;
		Scanner sc=null;
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				statement=connection.createStatement();
			if(statement!=null)
				resultSet=statement.executeQuery("select * from accounts");
			if(resultSet!=null) {
				System.out.println("NAME\tBALANCE");
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2));
				}
			}
			//Transaction
			System.out.println("Transaction Begins....");
			connection.setAutoCommit(false);
			
			//Single Unit Preparation
			statement.executeUpdate("update accounts set balance =balance-100 where name='sachin';");
			statement.executeUpdate("update accounts set balance =balance-100 where name='dhoni';");
			System.out.println("Can you please confirm the transaction of 100INR...Yes/No");
			sc=new Scanner(System.in);
			String option=sc.next();
			if(option.equalsIgnoreCase("yes")) {
				connection.commit();
			}
			else {
				connection.rollback();
			}
			System.out.println("Data After Transaction....");
			ResultSet resultSet2=statement.executeQuery("select name,balance from accounts;");
			if(resultSet2!=null) {
				System.out.println("NAME\tBALANCE");
				while(resultSet2.next()) {
					System.out.println(resultSet2.getString(1)+"\t"+resultSet2.getInt(2));
				}
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
