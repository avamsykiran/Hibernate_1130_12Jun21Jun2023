package com.cts.jpahibdemo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class TraineeId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long rollNumber;
	private String programName;

	public TraineeId() {
		// TODO Auto-generated constructor stub
	}

	public TraineeId(Long rollNumber, String programName) {
		super();
		this.rollNumber = rollNumber;
		this.programName = programName;
	}

	public Long getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Long rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(programName, rollNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraineeId other = (TraineeId) obj;
		return Objects.equals(programName, other.programName) && Objects.equals(rollNumber, other.rollNumber);
	}

	
	
}
