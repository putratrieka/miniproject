package com.trieka.miniproject;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.trieka.miniproject.dao.EmployeeDao;
import com.trieka.miniproject.entity.Employee;
import com.trieka.miniproject.service.LeaveService;

@EnableProcessApplication
@SpringBootApplication
public class MiniprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniprojectApplication.class, args);
	}
	
	@Bean
	public LeaveService leaveService(){
		return new LeaveService();
	}
	@Autowired
	private RuntimeService runtimeService;
	 
	@EventListener
	private void processPostDeploy(PostDeployEvent event) {
		runtimeService.startProcessInstanceByKey("cuti");
	}
//	@Autowired
//	private EmployeeDao employeeDao;
////	
//	@Override
//	public void run(ApplicationArguments args) throws Exception{
//		
//		Employee employee = new Employee();
//		
//		employee =	employeeDao.findById(1L).get();
//		System.out.println(employee);
//		employeeDao.findAll().forEach(System.out::println);
//	}

}
