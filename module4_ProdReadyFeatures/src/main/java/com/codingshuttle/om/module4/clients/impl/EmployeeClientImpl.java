package com.codingshuttle.om.module4.clients.impl;

import com.codingshuttle.om.module4.advices.ApiResponse;
import com.codingshuttle.om.module4.clients.EmployeeClient;
import com.codingshuttle.om.module4.dto.EmployeeDTO;
import com.codingshuttle.om.module4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try{
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.info("Successfully retrieved the employees in getAllEmployees");
            return employeeDTOList.getData();

        } catch (Exception e) {
            log.error("Exception occured in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeByID(Long employeeID) {
        log.trace("Trying to get employee by ID in getEmployeeByID");
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeID}", employeeID)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not fetch the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeByID");
            throw new RuntimeException(e);
        }
    }


    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create employee with information {}", employeeDTO);
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error("4xx client error occured during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not fetch the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.info("Successfully create a new employee");
            return employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in createNewEmployee");
            throw new RuntimeException(e);
        }
    }
}
