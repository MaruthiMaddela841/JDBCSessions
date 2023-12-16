package com.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.jdbc.statement.JDBCUtil;

public class RetrievalImageinDB {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		Scanner sc=null;
		String name=null;
		String locationOfImage=null;
		InputStream is=null;
		FileOutputStream fos=null;
		int i=0;
		try {
			connection=JDBCUtil.getJDBCConnection();
			String query="select * from persons where id=?;";
			if(connection!=null)
				ps=connection.prepareStatement(query);
			if(ps!=null) {
				sc=new Scanner(System.in);
				if(sc!=null) {
					System.out.println("Enter Id of the record to get the resultSet:");
					String idInput=sc.next();
					ps.setString(1, idInput);
					resultSet=ps.executeQuery();
					System.out.println("ID\tNAME\tIMAGE");
					while(resultSet.next()) {
						Integer id=resultSet.getInt(1);
						name=resultSet.getString(2);
						is=resultSet.getBinaryStream(3);
						File f= new File("D:\\iNeuron\\copy.jpg");
						fos=new FileOutputStream(f,true);
//						i=is.read();
//						while(i!=-1) {
//							fos.write(i);
//							i=is.read();
//						}
						//2nd Method
//						byte[] b= new byte[1024];
//						while(is.read(b)>0) {
//							fos.write(b);
//						}
						//3rd Method
						IOUtils.copy(is,fos);
						System.out.println(id+"\t"+name+"\t"+f.getAbsolutePath());
					}
				}
				fos.close();
			}
			else {
				System.out.println("No Record Found.....");
			}
				
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			System.out.println("Closing the resources....");
			JDBCUtil.cleanUp(connection, ps, resultSet);
			sc.close();
		}
	}

}
