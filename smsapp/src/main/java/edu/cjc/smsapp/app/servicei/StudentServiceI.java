package edu.cjc.smsapp.app.servicei;

import java.util.List;

import edu.cjc.smsapp.app.model.Student;

public interface StudentServiceI {

	void saveStudent(Student s);
public List<Student> getAllStudents();
public List<Student> searchStudentsByBatch(String batchNumber);
Student getStudentById(int id);
void updateFees(int studentId, double ammount);
void deleteStudent(int id);

public List<Student> pagingData(int pageNo);
}
