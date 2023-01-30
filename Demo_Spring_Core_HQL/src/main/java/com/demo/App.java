package com.demo;

import java.util.List;

import com.demo.bean.Student;
import com.demo.dao.StudentDao;

public class App {
	public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
		Student student = new Student("Ramesh", "Fadatare", "rameshfadatare@javaguides.com");
		studentDao.insertStudent(student);

		// update student
		Student student1 = new Student("Ram", "Fadatare", "rameshfadatare@javaguides.com");
		studentDao.updateStudent(student1);

		// get students
		List<Student> students = studentDao.getStudents();
		students.forEach(s -> System.out.println(s.getFirstName()));

		// get single student
		Student student2 = studentDao.getStudent(1);
		System.out.println(student2);

		// delete student
		studentDao.deleteStudent(1);
	}
}
