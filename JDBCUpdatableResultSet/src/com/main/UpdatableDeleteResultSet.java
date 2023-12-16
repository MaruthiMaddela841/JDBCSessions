package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.util.JDBCUtil;

public class UpdatableDeleteResultSet {

	public static void main(String[] args) throws SQLException {
		
		Connection connection=null;
		Statement statement=null;	
		ResultSet resultSet=null;
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query="select id,name,age,address from employees";
			if(statement!=null)
				resultSet=statement.executeQuery(query);
			if(resultSet!=null) {
				System.out.println("Records before insertion..");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while(resultSet.next()) {
					resultSet.refreshRow();
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				}	
			}
			System.out.println();
			resultSet.moveToInsertRow();
			resultSet.updateString(2, "brad");
			resultSet.updateInt(3,34);
			resultSet.updateString(4, "UK");
			resultSet.insertRow();
			System.out.println("records after insertion...");
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			while(resultSet.next()) {
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
