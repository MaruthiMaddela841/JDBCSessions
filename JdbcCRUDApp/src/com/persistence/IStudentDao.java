package com.persistence;

import com.dto.Student;

public interface IStudentDao {

	public String addStudent(String name,Integer age, String saddress);
	public Student searchStudent(Integer id);
	public String updateStudent(Integer sid, String sname, Integer sage,String saddress);
	public String deleteStudent(Integer id);
	
}
