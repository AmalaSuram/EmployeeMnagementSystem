package com.hcl.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ems.constant.AppConstant;
import com.hcl.ems.dto.EmployeeDeleteDto;
import com.hcl.ems.dto.EmployeeListDto;
import com.hcl.ems.dto.EmployeeResponseDto;
import com.hcl.ems.dto.EmployeeSearchDto;
import com.hcl.ems.dto.UpdateEmployeeDto;
import com.hcl.ems.entity.Employee;
import com.hcl.ems.exception.EmployeeNotFoundException;
import com.hcl.ems.service.EmployeeService;
/*
 * This application is about Employee Mnagement System
 */
import com.hcl.ems.service.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	/**
	 * getemployeelist() is used to get list of employees
	 * 
	 * @return it return employee list
	 * 
	 */
	@GetMapping("/all")
	public ResponseEntity<EmployeeListDto> getemployeelist(){
		log.info("Get the emplyoees  list...");
		List<Employee> emplist = employeeService.getemployeeDetails();
		EmployeeListDto employeeList = new EmployeeListDto();
		employeeList.setEmplist(emplist);
		employeeList.setMessage(AppConstant.SUCCESS_MESSAGE);
		employeeList.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(employeeList, HttpStatus.CREATED);
	}

	/**
	 * saveEmployee() is used to create new employee
	 * 
	 * @param emp is input for the saveEmployee()
	 * @return it return the EmployeeResponseDto
	 */
	@PostMapping("/createemp")
	public ResponseEntity<EmployeeResponseDto> saveEmployee(@RequestBody Employee emp) {
		log.info(" Inside the Create the emplyoees  based on employee input...");
		EmployeeResponseDto employeeResponse = employeeService.saveEmployee(emp);
		employeeResponse.setMessage(AppConstant.SUCCESS_MESSAGE);
		employeeResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	/**
	 * deleteEmployee() is used to delete the existing employee
	 * 
	 * @param empId is input for the deleteEmployee()
	 * @return it return the EmployeeDeleteDto
	 * @throws EmployeeNotFoundException
	 */
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<EmployeeDeleteDto> deleteEmployee(@PathVariable int empId) throws EmployeeNotFoundException {
		log.info(" Starting the Delete the emplyoee method...");
		employeeService.deleteEmployee(empId);
		EmployeeDeleteDto deleteEmployee = new EmployeeDeleteDto();
		deleteEmployee.setMessage(AppConstant.SUCCESS_MESSAGE);
		deleteEmployee.setStatusCode(HttpStatus.OK.value());
		deleteEmployee.setEmpId(empId);
		return new ResponseEntity<>(deleteEmployee, HttpStatus.CREATED);
	}

	/**
	 * updateEmployee() is used to update the employee
	 * 
	 * @param updemp is one of the input for updateEmployee()
	 * @param empId is one of the input for updateEmployee()
	 * @return it returns the UpdateEmployeeDto
	 * @throws EmployeeNotFoundException
	 */
	@PutMapping("/update/{empId}")
	public ResponseEntity<UpdateEmployeeDto> updateEmployee(@RequestBody Employee updemp, @PathVariable int empId)
			throws EmployeeNotFoundException {
		log.info("Inside Update the emplyoee  based on employee input...");
		UpdateEmployeeDto updateEmployee = employeeService.updateEmployee(updemp, empId);
		updateEmployee.setMessage(AppConstant.SUCCESS_MESSAGE);
		updateEmployee.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED);
	}
/**
 * searchEmployee() is used to search the employee based on empname
 * @param empName is iput for the searchEmployee()  
 * @return it returns the EmployeeSearchDto
 */
	@GetMapping("/searchemployee")
	public ResponseEntity<EmployeeSearchDto> searchEmployee(@RequestParam String empName) {
		log.info("Starting the  emplyoees  based on empName...");
		EmployeeSearchDto searchEmployee = employeeService.searchEmployee(empName);
		searchEmployee.setMessage(AppConstant.SUCCESS_MESSAGE);
		searchEmployee.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(searchEmployee, HttpStatus.CREATED);
	}

}
