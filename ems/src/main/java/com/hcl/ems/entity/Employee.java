package com.hcl.ems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Entity
@Setter
@Getter
public class Employee {
	@Id
	private Integer empId;
	@NotNull
	private String empName;

	@NotNull
	private Integer empExp;
	private String technology;

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empExp=" + empExp + ", technology=" + technology
				+ ", getEmpId()=" + getEmpId() + ", getEmpName()=" + getEmpName() + ", getEmpExp()=" + getEmpExp()
				+ ", getTechnology()=" + getTechnology() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Employee(Integer empId, String empName, Integer empExp, String technology) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empExp = empExp;
		this.technology = technology;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}
