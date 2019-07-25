package com.trieka.miniproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.springframework.beans.factory.annotation.Autowired;

import com.trieka.miniproject.dao.EmployeeDao;
import com.trieka.miniproject.entity.Employee;
import com.trieka.miniproject.entity.LeaveRequestModel;

public class CutiService{
		
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private ProcessEngine camunda;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private BpmnModelInstance bpmn;

	@Transactional
	public Boolean LeaveRequest(LeaveRequestModel leaveReq) {	
		//String isAccepted;
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("ID Karyawan", leaveReq.getIdKaryawan());
		variables.put("Lama Cuti", leaveReq.getLamaCuti());
			
		//boolean leaveAcc;
		Employee employee = new Employee();
		Long id = leaveReq.getIdKaryawan();
		
		employee =	employeeDao.findById(id).get();
		
		if((leaveReq.getLamaCuti() <= 5)&&(employee.getJatahCuti() > 0)) {
			employee.setJatahCuti(employee.getJatahCuti()-1);
			
			employee.setTotalCuti(employee.getTotalCuti()+1);
			variables.put("Status", "Izin Diterima");
			runtimeService.startProcessInstanceByKey("camunda-cuti", variables);
			employeeDao.save(employee);

			return true;
		}else {
			variables.put("Status", "Izin Ditolak");
			runtimeService.startProcessInstanceByKey("camunda-cuti", variables);
			return false;
		}
	}
	

}
