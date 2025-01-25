package edu.cjc.smsapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.smsapp.app.model.Student;
import edu.cjc.smsapp.app.servicei.StudentServiceI;

@Controller
public class AdminController {
  int a;
	@Autowired
	StudentServiceI ssi;
	
	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String username,@RequestParam("password") String password,Model m) {
		if(username.equals("admin") && password.equals("admin"))
		{
			List<Student> list=ssi.getAllStudents();
			m.addAttribute("data",list);
			return "adminscreen";
		}else {
			m.addAttribute("login_fail","ivalid login");
			return "login";
		}
	}
	@RequestMapping("/enroll_student")
	public String saveStudent(@ModelAttribute Student s,Model m) {
		
		      ssi.saveStudent(s);
		   List<Student> list=ssi.getAllStudents();   
		      m.addAttribute("data",list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String searchStudent(@RequestParam("batchNumber") String batchNumber,Model m) {
		
		  
		List<Student> list=ssi.searchStudentsByBatch(batchNumber);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam("id") int id,Model m) {
		Student stu=ssi.getStudentById(id);
		m.addAttribute("st", stu);
		return "feesUpdate";
	}
	
	@RequestMapping("/payfees")
	public String updateFees(@RequestParam("studentid") int studentId,@RequestParam("ammount") double ammount,Model m) {
		
		ssi.updateFees(studentId,ammount);
		m.addAttribute("data",ssi.getAllStudents());
		return "adminscreen";
	}
	
	@RequestMapping("/remove")
	public String deleteStudent(@RequestParam("id") int id,Model m) {
		
		    ssi.deleteStudent(id);
		
		
		m.addAttribute("data", ssi.getAllStudents());
		return "adminscreen";
	}
	
	
	
	
	
	@RequestMapping("/paging")
	public String pagingStudents(@RequestParam("pageNo") int pageNo,Model m) {
		
		List<Student> list=ssi.pagingData(pageNo);
	m.addAttribute("data",list);
	return "adminscreen";
	}
	
	
}
