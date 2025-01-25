package edu.cjc.smsapp.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.cjc.smsapp.app.model.Student;
import edu.cjc.smsapp.app.repositary.StudentRepositary;
import edu.cjc.smsapp.app.servicei.StudentServiceI;

@Service
public class StudentServiceImpl  implements StudentServiceI{

	@Autowired
	StudentRepositary sr;
	
	@Override
	public void saveStudent(Student s) {
		sr.save(s);
		
	}

	@Override
	public List<Student> getAllStudents() {
		return sr.findAll();
	}

	@Override
	public List<Student> searchStudentsByBatch(String batchNumber) {
		
		
		return sr.findByBatchNumber(batchNumber);
	}

	@Override
	public Student getStudentById(int id) {
	          Student s    =  sr.findById(id).get();
		return s;
	}

	@Override
	public void updateFees(int studentId, double ammount) {
          	Student s=sr.findById(studentId).get();
          	double amt=s.getFeesPaid()+ammount;
          	
          	s.setFeesPaid(amt);
          	sr.save(s);
	}

	@Override
	public void deleteStudent(int id) {
		
		sr.deleteById(id);
	}

	@Override
	public List<Student> pagingData(int pageNo) {
		
		Pageable page=PageRequest.of(pageNo, 2,Sort.by("studentId").descending());         
		  
	List<Student>	list   = sr.findAll(page).getContent();
		
		return list;
	}

}
