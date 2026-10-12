package com.codingshuttle.om.module4.clients.impl;

import com.codingshuttle.om.module4.advices.ApiResponse;
import com.codingshuttle.om.module4.clients.EmployeeClient;
import com.codingshuttle.om.module4.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try{
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeByID(Long employeeID) {
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeID}", employeeID)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
