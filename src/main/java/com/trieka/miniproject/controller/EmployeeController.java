package com.trieka.miniproject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trieka.miniproject.dao.EmployeeDao;
import com.trieka.miniproject.entity.Employee;
import com.trieka.miniproject.entity.LeaveRequestModel;
import com.trieka.miniproject.service.LeaveService;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private LeaveService leaveService;
	
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
	
	@PostMapping("/izinCuti")
	public ResponseEntity<?> insert(@RequestBody LeaveRequestModel leaveRequest){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		
		leaveService.LeaveRequest(leaveRequest);

		if (leaveService.LeaveRequest(leaveRequest)) {
			result.put("message", "Leave Approved");
			return ResponseEntity.ok(result);
		}else {
			result.put("message", "Leave Rejected");
			return ResponseEntity.ok(result);
		}
	}
}
