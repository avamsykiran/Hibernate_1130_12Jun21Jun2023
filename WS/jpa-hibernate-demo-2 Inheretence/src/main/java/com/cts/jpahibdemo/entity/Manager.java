package com.cts.jpahibdemo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Manager extends Employee {

	private Double hra;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(Long empId, String name, Double basicPay,Double hra) {
		super(empId, name, basicPay);
		this.hra=hra;
	}

	public Double getHra() {
		return hra;
	}

	public void setHra(Double hra) {
		this.hra = hra;
	}
	
	
}