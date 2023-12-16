package com.daofactory;

import com.persistence.IStudentDao;
import com.persistence.StudentDaoImpl;

public class StudentDaoFactory {

	private StudentDaoFactory() {
		
	}
	
	private static IStudentDao studentDao=null;
	public static IStudentDao getStudentDao() {
		if(studentDao==null) {
			studentDao=new StudentDaoImpl();
		}
		return studentDao;
	}
	
}
