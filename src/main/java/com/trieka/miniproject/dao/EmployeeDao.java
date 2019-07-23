package com.trieka.miniproject.dao;

import org.springframework.data.repository.CrudRepository;

import com.trieka.miniproject.entity.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Long>{

	//public Employee findById(Long id);
}
