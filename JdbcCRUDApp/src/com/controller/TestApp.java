package com.controller;
import java.util.Scanner;

import com.dto.Student;
import com.service.IStudentService;
import com.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) {
		
		//insertOperation();
		//searchOperation();
		//deleteStudent();
		updateStudent();
	}
	
	private static void updateStudent() {
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Id of the Student:");
		Integer id=sc.nextInt();
		Student std=studentService.searchStudent(id);
		if(std!=null) {
			System.out.println(std);
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			System.out.println(std.getSid()+"\t"+std.getSname()+"\t"+std.getSage()+"\t"+std.getAddress());
			System.out.println();
			System.out.println("Enter Name of the Student to Change:");
			String name=sc.next();
			System.out.println("Enter Age of the Student to Change:");
			Integer age=sc.nextInt();
			System.out.println("Enter Address of the Student to Change:");
			String address=sc.next();
			String msg=studentService.updateStudent(id, name, age, address);
			if(msg.equalsIgnoreCase("success"))
				System.out.println("Records updated Successfully");
			else if(msg.equalsIgnoreCase("not found"))
				System.out.println("Record not found");
		else
			System.out.println("Records updation Failed");
		}
		else {
			System.out.println("Sorry Record Not Found for given Id");
		}
		
		sc.close();
		
		
	}

	private static void deleteStudent() {
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Id of the Student:");
		Integer id=sc.nextInt();
		String msg=studentService.deleteStudent(id);
		if(msg.equalsIgnoreCase("success"))
			System.out.println("Records deleted Successfully");
		else if(msg.equalsIgnoreCase("not found"))
			System.out.println("Record not found");
	else
		System.out.println("Records deletion Failed");
	}

	private static void searchOperation() {
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Id of the Student:");
		Integer id=sc.nextInt();
		Student std=studentService.searchStudent(id);
		if(std!=null) {
			System.out.println(std);
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			System.out.println(std.getSid()+"\t"+std.getSname()+"\t"+std.getSage()+"\t"+std.getAddress());
		}
		else {
			System.out.println("Sorry Record Not Found for given Id");
		}
		sc.close();
	}

	private static void insertOperation() {
		IStudentService studentService=StudentServiceFactory.getStudentService();
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Name of the Student:");
		String name=sc.next();
		System.out.print("Enter age of the Student:");
		Integer age=sc.nextInt();
		System.out.print("Enter address of the Student:");
		String address=sc.next();
		String msg=studentService.addStudent(name,age,address);
		if(msg.equalsIgnoreCase("success"))
				System.out.println("Records inserted Successfully");
		else
			System.out.println("Records Insertion Failed");
	}

}
