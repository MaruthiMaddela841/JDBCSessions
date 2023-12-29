package com.service;

import com.dto.Student;

public interface IStudentService {
	public String addStudent(String name,Integer age, String saddress);
	public Student searchStudent(Integer id);
	public String updateStudent(Integer sid, String sname, Integer sage,String saddress);
	public String updateStudent(Student student);
	public String deleteStudent(Integer id);
	
}
