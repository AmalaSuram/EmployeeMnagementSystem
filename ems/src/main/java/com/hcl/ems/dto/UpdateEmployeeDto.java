package com.hcl.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateEmployeeDto {
	private Integer empId;
	private Integer statusCode;
	private String message;
}
