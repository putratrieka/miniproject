package com.trieka.miniproject.service;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.trieka.miniproject.dao.EmployeeDao;
import com.trieka.miniproject.entity.Employee;
import com.trieka.miniproject.entity.LeaveRequestModel;

public class LeaveService implements JavaDelegate{
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	public Boolean LeaveRequest(LeaveRequestModel leaveReq) {
		Employee employee = new Employee();
		Long id = leaveReq.getIdKaryawan();
		employee =	employeeDao.findById(id).get();
		
		// cek sisa jatah cuti karyawan
		if((leaveReq.getLamaCuti() <= 5)&&(employee.getJatahCuti() > 0)) {
			
			employee.setJatahCuti(employee.getJatahCuti()-1);
			employee.setTotalCuti(employee.getTotalCuti()+1);
			employeeDao.save(employee);
			return true;
		}else {
			return false;
		}
	}

	

}
