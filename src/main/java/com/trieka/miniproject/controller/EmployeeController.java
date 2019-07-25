package com.trieka.miniproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trieka.miniproject.dao.EmployeeDao;
import com.trieka.miniproject.entity.Employee;
import com.trieka.miniproject.entity.LeaveRequestModel;
import com.trieka.miniproject.service.CutiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employee")
@Api(tags = "Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CutiService cutiService;
	
	  @Autowired
	  private ProcessEngine camunda;
	
//	============================= GET ALL DATA ==================================
	@ApiOperation(
			value = "Get employee's data",
			notes = "Get employee's data based on provide ID and attached data",
			tags = "Get Data API"
			)
	@GetMapping("getAll")
	public List<Employee> getAll(){
		System.out.println("asa");
		List<Employee> result = new ArrayList<>();
		
		employeeDao.findAll().forEach(result::add);
		
		return result;
	}
	@ApiOperation(
			value = "Save employee's data",
			notes = "Save employee's data based on provide ID and attached data",
			tags = "Data Manipulation API"
			)
	@PostMapping(value = "save")
	public Employee save(@RequestBody Employee employee) {
		try {
			return employeeDao.save(employee);
		} catch (Exception e) {
			return null;
		}
	}
	
	// ====================== IZIN CUTI ======================================
	@ApiOperation(
			value = "Leave Request",
			notes = "Leave Request based on provide ID and attached data",
			tags = "Data Manipulation API"
			)
	@PostMapping("/izinCuti")
	public ResponseEntity<?> insert(@RequestBody LeaveRequestModel leaveRequest){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById("leaveReq");
		
		
		cutiService.LeaveRequest(leaveRequest);
		
		if (cutiService.LeaveRequest(leaveRequest)) {
			result.put("message", "Izin Cuti Diterima");
			return ResponseEntity.ok(result);
		}else {
			result.put("message", "Izin Cuti Ditolak");
			return ResponseEntity.ok(result);
		}
	}
	
	// ====================== DELETE DATA ======================================
	@ApiOperation(
			value = "Update employee's data",
			notes = "Update employee's data based on provide ID and attached data",
			tags = "Data Manipulation API"
			)
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Employee update(@RequestBody Employee employee, @PathVariable Long id) {
		Employee employeeSelected = employeeDao.findById(id).orElse(null);
		if (employeeSelected != null) {
			employeeSelected.setName(employee.getName()); 
			employeeSelected.setAddress(employee.getAddress());
			employeeSelected.setAge(employee.getAge());
			employeeSelected.setDob(employee.getDob());
			employeeSelected.setPhone(employee.getPhone());
			employeeSelected.setSallary(employee.getSallary());
			employeeSelected.setTanggalMasuk(employee.getTanggalMasuk());
			
			return employeeDao.save(employeeSelected);
			 
		}else {
			return null;
		}
	}
	
	@ApiOperation(
			value = "Delete employee's data",
			notes = "Delete employee's data based on provide ID and attached data",
			tags = "Data Manipulation API"
			)
	@DeleteMapping(value = "delete/{id}")
	public HashMap<String, Object> delete(@PathVariable	Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		employeeDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		
		return result;
	}
	
//	public ProcessInstance camundaProcess(LeaveRequestModel leaveRequest) {
//		return processEngine.getRuntimeService().startProcessInstanceByKey(
//				ProcessConstants.PROCESS_KEY, Variables.putValue(ProcessConstants.VAR_NAME_text, leaveRequest)
//				);
//	}
}
