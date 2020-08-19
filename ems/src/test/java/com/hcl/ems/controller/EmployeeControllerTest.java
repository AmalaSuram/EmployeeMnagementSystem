package com.hcl.ems.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.ems.dto.EmployeeDto;
import com.hcl.ems.dto.EmployeeListDto;
import com.hcl.ems.dto.EmployeeResponseDto;
import com.hcl.ems.dto.EmployeeSearchDto;
import com.hcl.ems.dto.UpdateEmployeeDto;
import com.hcl.ems.entity.Employee;
import com.hcl.ems.exception.EmployeeNotFoundException;
import com.hcl.ems.service.EmployeeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	@Mock
	EmployeeService employeeService;

	List<Employee> emplist = new ArrayList<>();
	Employee employee = new Employee();
	EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
	UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto();
	EmployeeSearchDto employeeSearchDto = new EmployeeSearchDto();
	List<EmployeeDto> employeedtolist = new ArrayList<>();

	EmployeeDto employeeDto = new EmployeeDto();

	@Before
	public void init() {
		employee.setEmpExp(2);
		employee.setEmpId(51784768);
		employee.setEmpName("amala");
		employee.setTechnology("java");
		employeeResponseDto.setEmpId(51784768);
		updateEmployeeDto.setEmpId(51784768);

		employeeDto.setEmpName("puja");
		employeedtolist.add(employeeDto);
		employeeSearchDto.setEmployeedtoList(employeedtolist);
	}

	@Test
	public void testgetemployeelist() {

		Mockito.when(employeeService.getemployeeDetails()).thenReturn(emplist);

		ResponseEntity<EmployeeListDto> getemployeelist = employeeController.getemployeelist();

		assertEquals(200, getemployeelist.getBody().getStatusCode());

	}

	@Test
	public void testsaveEmployee() {

		Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employeeResponseDto);

		ResponseEntity<EmployeeResponseDto> saveEmployee = employeeController.saveEmployee(employee);

		assertEquals(200, saveEmployee.getBody().getStatusCode());

	}

	@Test
	public void testupdateEmployee() throws EmployeeNotFoundException {

		Mockito.when(employeeService.updateEmployee(employee, 51784768)).thenReturn(updateEmployeeDto);

		ResponseEntity<UpdateEmployeeDto> updateEmployee = employeeController.updateEmployee(employee, 51784768);

		assertEquals(200, updateEmployee.getBody().getStatusCode());

	}

	@Test
	public void testsearchEmployee() {

		Mockito.when(employeeService.searchEmployee("amala")).thenReturn(employeeSearchDto);

		ResponseEntity<EmployeeSearchDto> searchEmployee = employeeController.searchEmployee("amala");

		assertEquals(200, searchEmployee.getBody().getStatusCode());

	}

	@Test
	public void testdeleteEmployee() throws EmployeeNotFoundException {

		assertEquals(200, employeeController.deleteEmployee(51784768).getBody().getStatusCode());

	}

}
