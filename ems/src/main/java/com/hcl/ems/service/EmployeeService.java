package com.hcl.ems.service;

import java.util.List;

import com.hcl.ems.dto.EmployeeResponseDto;
import com.hcl.ems.dto.EmployeeSearchDto;
import com.hcl.ems.dto.UpdateEmployeeDto;
import com.hcl.ems.entity.Employee;
import com.hcl.ems.exception.EmployeeNotFoundException;

public interface EmployeeService {

	List<Employee> getemployeeDetails();

	EmployeeResponseDto saveEmployee(Employee emp);

	void deleteEmployee(int empId) throws EmployeeNotFoundException;

	UpdateEmployeeDto updateEmployee(Employee updemp, int empId) throws EmployeeNotFoundException;

	EmployeeSearchDto searchEmployee(String empName);

}
