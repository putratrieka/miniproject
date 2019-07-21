package com.trieka.miniproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trieka.miniproject.dao.EmployeeDao;
import com.trieka.miniproject.entity.Employee;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("getAll")
	public List<Employee> getAll(){
		System.out.println("asa");
		List<Employee> result = new ArrayList<>();
		
		employeeDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@PostMapping(value = "save")
	public Employee save(@RequestBody Employee employee) {
		try {
			return employeeDao.save(employee);
		} catch (Exception e) {
			return null;
		}
	}


}
