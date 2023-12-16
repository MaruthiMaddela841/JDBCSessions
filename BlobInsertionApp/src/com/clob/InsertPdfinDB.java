package com.clob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.statement.JDBCUtil;

public class InsertPdfinDB {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		String name=null;
		String locationOfPdf=null;
		int i=0;
		try {
			connection=JDBCUtil.getJDBCConnection();
			String query="INSERT INTO cities(name, history) VALUES (?,?);";
			if(connection!=null)
				ps=connection.prepareStatement(query);
			if(ps!=null) {
				sc=new Scanner(System.in);
				if(sc!=null) {
					System.out.println("Enter name:");
					name=sc.next();
					System.out.println("Enter history Location:");
					locationOfPdf=sc.next();
				}
				ps.setString(1, name);
				ps.setCharacterStream(2, new FileReader(new File(locationOfPdf)));
				i=ps.executeUpdate();
				System.out.println("Rows Effected:"+i);
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
