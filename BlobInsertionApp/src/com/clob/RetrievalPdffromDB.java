package com.clob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.jdbc.statement.JDBCUtil;

public class RetrievalPdffromDB {
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		Scanner sc=null;
		String name=null;
		String locationOfPdf=null;
		Reader fr=null;
		FileWriter fw=null;
		int i=0;
		try {
			connection=JDBCUtil.getJDBCConnection();
			String query="select * from cities where id=?;";
			if(connection!=null)
				ps=connection.prepareStatement(query);
			if(ps!=null) {
				sc=new Scanner(System.in);
				if(sc!=null) {
					System.out.println("Enter Id of the record to get the resultSet:");
					String idInput=sc.next();
					ps.setString(1, idInput);
					resultSet=ps.executeQuery();
					System.out.println("ID\tNAME\tHISTORY");
					while(resultSet.next()) {
						Integer id=resultSet.getInt(1);
						name=resultSet.getString(2);
						fr=resultSet.getCharacterStream(3);
						File f= new File("D:\\iNeuron\\codes.pdf");
						fw=new FileWriter(f,true);
						IOUtils.copy(fr,fw);
						System.out.println(id+"\t"+name+"\t"+f.getAbsolutePath());
					}
				}
				fw.close();
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
