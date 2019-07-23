package com.trieka.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class LeaveRequestModel {

	@Id
	private Long idKaryawan;
	
	private int lamaCuti;
}
