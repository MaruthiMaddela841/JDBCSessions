package com.callableStatement;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.jdbc.statement.JDBCUtil;

public class CallableStatementGetByNameApp {
	private static final String storedProcedureCall="{CALL `SP_GET_PRODUCT_BY_NAME`(?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection=null;
		CallableStatement cs=null;
		ResultSet resultSet=null;
		Scanner sc=null;
		String name1=null;
		String name2=null;
		boolean flag=false;
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				cs=connection.prepareCall(storedProcedureCall);
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Name1:");
				name1=sc.next();
				System.out.println("Enter Name2:");
				name2=sc.next();
			}
			//set input value
			if(cs!=null) {
				cs.setString(1,name1);
				cs.setString(2,name2);
			}
			if(cs!=null) {
				cs.execute();
			}
			if(cs!=null) {
				resultSet=cs.getResultSet();
			}
			if(resultSet!=null) {
				System.out.println("PID\tPNAME\tPRICE\tQTY");
				while(resultSet.next()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4));
					flag=true;
				}
			}
			if(flag==true) {
				System.out.println("Record Avaiabale");
			}
			else {
				System.out.println("Record Not Available");
			}
			
				
				
			
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, cs, null);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			sc.close();
		}
	}

}
