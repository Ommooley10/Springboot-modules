package com.codingshuttle.om.module4;

import com.codingshuttle.om.module4.clients.EmployeeClient;
import com.codingshuttle.om.module4.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class Module4ApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	public void getAllEmployeesTest(){
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	public void getEmployeeByIDTest(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeByID(1L);
		System.out.println(employeeDTO);
	}

	@Test
	public void createNewEmployeeTest(){
		EmployeeDTO employeeDTO = new EmployeeDTO(null, "Om", "om@gmail.com", 22, "USER", LocalDate.of(2020,12,1), true );
		EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}

}
