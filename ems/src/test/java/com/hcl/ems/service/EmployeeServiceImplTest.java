package com.hcl.ems.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ems.dto.EmployeeDto;
import com.hcl.ems.dto.EmployeeResponseDto;
import com.hcl.ems.dto.EmployeeSearchDto;
import com.hcl.ems.dto.UpdateEmployeeDto;
import com.hcl.ems.entity.Employee;
import com.hcl.ems.exception.EmployeeNotFoundException;
import com.hcl.ems.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceImplTest {
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;
	@Mock
	EmployeeRepository employeeRepository;

	List<Employee> emplist = new ArrayList<>();
	Employee employee = new Employee();
	EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
	UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto();
	EmployeeSearchDto employeeSearchDto=new EmployeeSearchDto();
   List<EmployeeDto> employeedtolist=new ArrayList<>();
	
	EmployeeDto employeeDto=new EmployeeDto();
	@Before
	public void init() {
		employee.setEmpExp(2);
		employee.setEmpId(51784768);
		employee.setEmpName("amala");
		employee.setTechnology("java");
		employeeResponseDto.setEmpId(51784768);
		updateEmployeeDto.setEmpId(51784768);
		// updateEmployeeDto.setEmpId(employee.getEmpId());

		emplist.add(employee);
		employeeDto.setEmpName("puja");
		employeedtolist.add(employeeDto);
		employeeSearchDto.setEmployeedtoList(employeedtolist);
	}

	@Test
	public void testgetemployeeDetails() {

		Mockito.when(employeeRepository.findAll()).thenReturn(emplist);

		List<Employee> empList = employeeServiceImpl.getemployeeDetails();

		assertEquals(1, empList.size());

	}

	@Test
	public void testsaveEmployee() {

		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

		EmployeeResponseDto saveEmployee = employeeServiceImpl.saveEmployee(employee);

		assertEquals(51784768, saveEmployee.getEmpId());

	}

	@Test
	public void testupdateEmployee() throws EmployeeNotFoundException {

		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

		EmployeeResponseDto updateEmp = employeeServiceImpl.saveEmployee(employee);

		assertEquals(51784768, updateEmp.getEmpId());

	}

	@Test
	public void testdeleteEmployee() throws EmployeeNotFoundException {

		Employee emp = new Employee(51784768, "puja", 3, "java");
        Mockito.when(employeeRepository.findById(51784768)).thenReturn(Optional.of(emp));
		employeeServiceImpl.deleteEmployee(51784768);
		verify(employeeRepository, times(1)).deleteById(emp.getEmpId());

	}
	
	@Test
	public void testsearchEmployee(){

		Mockito.when(employeeRepository.findByEmpNameContainingIgnoreCase("amala")).thenReturn(emplist);

		 EmployeeSearchDto searchEmployee = employeeServiceImpl.searchEmployee("amala");

		 assertEquals(1, searchEmployee.getEmployeedtoList().size());

	}

	

	
	
}
