package com.service;

import com.daofactory.StudentDaoFactory;
import com.dto.Student;
import com.persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDao;
	@Override
	public String addStudent(String name, Integer age, String address) {
		IStudentDao studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.addStudent(name, age, address);
	}

	@Override
	public Student searchStudent(Integer id) {
		IStudentDao studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(id);
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		IStudentDao studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.updateStudent(sid, sname, sage, saddress);
	}

	@Override
	public String deleteStudent(Integer id) {
		IStudentDao studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(id);
	}

}
