package com.codingshuttle.om.module4.clients;

import com.codingshuttle.om.module4.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeByID(Long employeeID);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
