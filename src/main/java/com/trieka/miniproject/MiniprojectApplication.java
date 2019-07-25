package com.trieka.miniproject;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.trieka.miniproject.service.CutiService;

@EnableProcessApplication
@SpringBootApplication
public class MiniprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniprojectApplication.class, args);
	}
	
	@Bean
	public CutiService leaveService(){
		return new CutiService();
	}
//	@Autowired
//	private RuntimeService runtimeService;
//	 
//	@EventListener
//	private void processPostDeploy(PostDeployEvent event) {
//		runtimeService.startProcessInstanceByKey("cuti");
//	}
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
