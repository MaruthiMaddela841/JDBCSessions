package com.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.dto.Student;
import com.service.IStudentService;
import com.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		//
		while(true) {
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. UPDATE_2");
			System.out.println("6. EXIT");
			System.out.println("Enter Your Choice Here..");
			String option=br.readLine();
		switch(option) {
		case "1": insertOperation();
		System.out.println("****************************");
			break;
		case "2": searchOperation();
		System.out.println("****************************");
			break;
		case "3": updateStudent();
		System.out.println("****************************");
			break;
		case "4": deleteStudent();
		System.out.println("****************************");
			break;
		case "5": updateRecordThroughStudentObject();
		System.out.println("****************************");
			break;
		case "6": System.out.println("Thanks for using the application....");
			System.exit(0);;
		default: System.out.println("Invalid Option");
		System.out.println("****************************");
			break;
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static void updateRecordThroughStudentObject() throws IOException {
		IStudentService studentService=StudentServiceFactory.getStudentService();
		//Scanner sc= new Scanner(System.in);
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter Id of the Student to be Updated:");
		//Integer id=sc.nextInt();
		String id=br.readLine();
		//Student student=studentService.searchStudent(id);
		Student student=studentService.searchStudent(Integer.parseInt(id));
		Student newStudent= new Student();
		if(student!=null) {
			System.out.println("Student Id: "+student.getSid());
			newStudent.setSid(student.getSid());
			System.out.println("Student Old Name is: "+student.getSname()+" Enter New Name: ");
			//String newName=sc.next();
			String newName=br.readLine();
			if(newName.equals("") || newName=="") {
				newStudent.setSname(student.getSname());
			}
			else {
				newStudent.setSname(newName);
			}
			System.out.println("Student Old Age is: "+student.getSage()+" Enter New Age: ");
			//String newAge=sc.next();
			String newAge=br.readLine();
			if(newAge.equals("") || newAge=="") {
				newStudent.setSage(student.getSage());
			}
			else {
				newStudent.setSage(Integer.parseInt(newAge));
			}
			System.out.println("Student Old Address is: "+student.getAddress()+" Enter New Address: ");
			//String NewAddress=sc.next();
			String NewAddress=br.readLine();
			if(NewAddress.equals("") || NewAddress=="") {
				newStudent.setAddress(student.getAddress());
			}
			else {
				newStudent.setAddress(NewAddress);
			}
			String status=studentService.updateStudent(newStudent);
			if(status.equalsIgnoreCase("success"))
				System.out.println("Records updated Successfully");
			else
				System.out.println("Record not updated");
		}
		else {
			System.out.println("Student record is not available for the given id: "+id);
			
		}
		
	}

	@SuppressWarnings("unused")
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
		
		//sc.close();
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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
		//sc.close();
	}

	@SuppressWarnings("unused")
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
