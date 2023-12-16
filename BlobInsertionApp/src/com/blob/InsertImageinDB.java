package com.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.statement.JDBCUtil;

public class InsertImageinDB {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		String name=null;
		String locationOfImage=null;
		int i=0;
		try {
			connection=JDBCUtil.getJDBCConnection();
			String query="INSERT INTO persons(name, image) VALUES (?,?);";
			if(connection!=null)
				ps=connection.prepareStatement(query);
			if(ps!=null) {
				sc=new Scanner(System.in);
				if(sc!=null) {
					System.out.println("Enter name:");
					name=sc.next();
					System.out.println("Enter Image Location:");
					locationOfImage=sc.next();
				}
				ps.setString(1, name);
				ps.setBinaryStream(2, new FileInputStream(new File(locationOfImage)));
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
