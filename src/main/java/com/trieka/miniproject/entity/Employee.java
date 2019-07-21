package com.trieka.miniproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_division", nullable = false)
	private Long idDivision;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String status;
	

}
