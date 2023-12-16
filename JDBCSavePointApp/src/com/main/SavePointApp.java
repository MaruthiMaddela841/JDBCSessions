package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbc.util.JDBCUtil;

public class SavePointApp {

	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		Statement statement=null;	
		try {
			connection=JDBCUtil.getJDBCConnection();
			statement=connection.createStatement();
			//Transaction
			System.out.println("Transaction Begins....");
			connection.setAutoCommit(false);
			statement.executeUpdate("insert into politicians(name,party) values ('MODI','BJP');");
			statement.executeUpdate("insert into politicians(name,party) values ('KCR','TRS');");
			Savepoint sp=connection.setSavepoint();
			statement.executeUpdate("insert into politicians(name,party) values ('siddu','TRS');");
			System.out.println("Something went wrong.....");
			connection.rollback(sp);
			System.out.println("Operations are rolled back to the Savepoint");
			connection.commit();
			System.out.println("Transaction done...");
			
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.cleanUp(connection, statement, null);
		}
	}

}
