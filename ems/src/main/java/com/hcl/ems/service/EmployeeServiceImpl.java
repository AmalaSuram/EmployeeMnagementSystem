package com.hcl.ems.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ems.constant.AppConstant;
import com.hcl.ems.dto.EmployeeDto;
import com.hcl.ems.dto.EmployeeResponseDto;
import com.hcl.ems.dto.EmployeeSearchDto;
import com.hcl.ems.dto.UpdateEmployeeDto;
import com.hcl.ems.entity.Employee;
import com.hcl.ems.exception.EmployeeNotFoundException;
import com.hcl.ems.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * getemployeeDetails() is used to get the employee list
	 * 
	 */

	@Override
	public List<Employee> getemployeeDetails(){
		log.info("Get the emplyoees  list...");
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	/**
	 * saveEmployee() is used create the employee
	 */
	@Override
	public EmployeeResponseDto saveEmployee(Employee emp) {
		log.info("Create the emplyoees  based on employee input...");
		Employee employee = employeeRepository.save(emp);
		EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
		employeeResponseDto.setEmpId(employee.getEmpId());
		return employeeResponseDto;
	}

	/**
	 * deleteEmployee() is used to delete the employee
	 */

	@Override
	public void deleteEmployee(int empId) throws EmployeeNotFoundException {
		log.info("Delete the emplyoee  based on employee Id...");
		Optional<Employee> employee = employeeRepository.findById(empId);

		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException(AppConstant.EMPLOYEENOTFOUNDEXCEPTION + empId);
		}
		employeeRepository.deleteById(empId);

	}

	/**
	 * updateEmployee() is used to update the employee
	 */
	@Override
	public UpdateEmployeeDto updateEmployee(Employee updemp, int empId) throws EmployeeNotFoundException {
		log.info("Upadte the emplyoee  based on employee input...");
		Optional<Employee> employee = employeeRepository.findById(empId);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException(AppConstant.EMPLOYEENOTFOUNDEXCEPTION + empId);
		}

		Employee updateEmp = employeeRepository.save(updemp);
		UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto();
		updateEmployeeDto.setEmpId(updateEmp.getEmpId());
		return updateEmployeeDto;

	}

	/**
	 * searchEmployee() is used to search the employee based on the name
	 */
	@Override
	public EmployeeSearchDto searchEmployee(String empName) {
		log.info("Get the emplyoees  based on empName...");
		EmployeeSearchDto employeeSearchDto = new EmployeeSearchDto();
		List<Employee> employees = employeeRepository.findByEmpNameContainingIgnoreCase(empName);
		List<EmployeeDto> employeeList = employees.stream().map(employee -> convertToDto(employee))
				.collect(Collectors.toList());
		employeeSearchDto.setEmployeedtoList(employeeList);

		return employeeSearchDto;
	}

	private EmployeeDto convertToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employee, employeeDto);
		return employeeDto;
	}

}
