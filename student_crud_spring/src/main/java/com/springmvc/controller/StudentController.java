package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.beans.StudentBean;
import com.springmvc.dao.StudentDAO;

@Controller
public class StudentController {
	
	@Autowired
	StudentDAO dao;//will inject dao from XML file
	
	@RequestMapping("/student")
	public String showform(Model m) {
		m.addAttribute("command", new StudentBean());
		return "student"; //student.jsp page exists
	}
	
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@ModelAttribute("stu") StudentBean stu){
	dao.save(stu);
	return "redirect:/viewstudent";//will redirect to viewstudent request mapping
	}
	
	/* It provides list of students in model object */
	@RequestMapping("/viewstudent")
	public String viewstudent(Model m){
	List<StudentBean> list=dao.getStudentDetails();
	m.addAttribute("list",list);
	return "viewstudent";
	}
	
	/* It displays object data into form for the given id.
	* The @PathVariable puts URL data into variable.*/
	@RequestMapping(value="/editstudent/{id}")
	public String edit(@PathVariable int id, Model m){
	StudentBean stu=dao.getStudentDetailsById(id);
	m.addAttribute("command",stu);
	return "editstudent";
	}
	
	/* It updates model object. */
	@RequestMapping(value="/editsave",method = RequestMethod.POST)
	public String editsave(@ModelAttribute("stu") StudentBean stu){
	dao.update(stu);
	return "redirect:/viewstudent";
	}

}
