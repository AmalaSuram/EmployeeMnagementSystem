package com.hcl.ems.dto;

import java.util.List;

import com.hcl.ems.entity.Employee;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class EmployeeListDto {
	private List<Employee> emplist;
	private Integer statusCode;
	private String message;
}
