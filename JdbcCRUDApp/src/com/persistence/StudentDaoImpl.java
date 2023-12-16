package com.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.Student;
import com.jdbc.util.JDBCUtil;

public class StudentDaoImpl implements IStudentDao {

	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet resultSet=null;
	
	@Override
	public String addStudent(String name, Integer age, String address) {
		String sqlQuery="INSERT INTO students(name, age, address) VALUES (?,?,?);";
		try {
		connection=JDBCUtil.getJDBCConnection();
		if(connection!=null)
			ps=connection.prepareStatement(sqlQuery);
		if(ps!=null) {
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, address);
			int rowsEffected=ps.executeUpdate();
			System.out.println("Rows Effected;"+rowsEffected);
			if(rowsEffected==1) {
				return "success";
			}
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer id) {
		Student student=null;
		String sqlSelectQuery="select id,name,age,address from students where id=?;";
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				ps=connection.prepareStatement(sqlSelectQuery);
			if(ps!=null) {
				ps.setInt(1, id);
			}
			if(ps!=null) {
				resultSet=ps.executeQuery();
			}
			if(resultSet!=null) {
				if(resultSet.next()) {
				student=new Student();
				student.setSid(resultSet.getInt(1));
				student.setSname(resultSet.getString(2));
				student.setSage(resultSet.getInt(3));
				student.setAddress(resultSet.getString(4));
				return student;
				}
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		String sqlQuery="update students set name=?,age=?,address=? where id=?;";
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null)
				ps=connection.prepareStatement(sqlQuery);
			if(ps!=null) {
				ps.setString(1, sname);
				ps.setInt(2, sage);
				ps.setString(3, saddress);
				ps.setInt(4, sid);
				Integer i=ps.executeUpdate();
				if(i==1) {
					return "success";
				}
				else {
					return "not found";
				}
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteStudent(Integer id) {
		String sqlDeleteQuery="delete from students where id=?";
		try {
			connection=JDBCUtil.getJDBCConnection();
			if(connection!=null) {
				ps=connection.prepareStatement(sqlDeleteQuery);
				if(ps!=null) {
					ps.setInt(1, id);
					Integer i=ps.executeUpdate();
					if(i==1) {
						return "success";
					}
					else {
						return "not found";
					}
				}
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
