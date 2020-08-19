package com.hcl.ems.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class EmployeeSearchDto {
	private List<EmployeeDto> employeedtoList;
	private Integer statusCode;
	private String message;

}
