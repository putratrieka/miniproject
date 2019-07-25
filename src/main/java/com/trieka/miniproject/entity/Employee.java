package com.trieka.miniproject.entity;

import java.math.BigDecimal;
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
@Table(name = "karyawan")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "tanggal_masuk",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date tanggalMasuk;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column
	private BigDecimal sallary;
	
	@Column(name = "jatah_cuti")
	private int jatahCuti;
	
	@Column(name = "total_cuti")
	private int totalCuti;
	

}
