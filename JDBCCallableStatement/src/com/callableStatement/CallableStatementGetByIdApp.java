package com.callableStatement;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.jdbc.statement.JDBCUtil;

public class CallableStatementGetByIdApp {
	private static final String storedProcedureCall="{CALL `SP_GET_PRODUCT_DETAILS_BY_ID`(?,?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection=null;
		CallableStatement cs=null;
		Scanner sc=null;
		Integer id=null;
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				cs=connection.prepareCall(storedProcedureCall);
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter id:");
				id=sc.nextInt();
			}
			//set input value
			if(cs!=null) {
				cs.setInt(1,id);
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.INTEGER);
				cs.registerOutParameter(4, Types.INTEGER);
			}
			if(cs!=null) {
				cs.execute();
				System.out.println("Product Name:"+cs.getString(2));
				System.out.println("Product Cost:"+cs.getInt(3));
				System.out.println("Product Quantity:"+cs.getInt(4));
			}
			//get output value
			
				
				
			
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
