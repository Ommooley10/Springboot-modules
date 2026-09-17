package com.codingshuttle.om.module2.services;

import com.codingshuttle.om.module2.dto.EmployeeDTO;
import com.codingshuttle.om.module2.entities.EmployeeEntity;
import com.codingshuttle.om.module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = modelMapper;
    }


    //NOTE: Here we have used ModelMapper which is used to map the similar fields of two objects (in this case employeeEntity and EmployeeDTO as we need to return EmployeeDTO)
    /*TIP: Instead of creating and object of ModelMapper in every method, we create it's Bean in "configs" and initialize it here in EmployeeService.java
    below is the implementation of modelmapper if the bean is not made*/

//    public EmployeeDTO getEmployeeById(Long employeeID) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).orElse(null);
//        ModelMapper mapper = new ModelMapper();
//        return mapper.map(employeeEntity, EmployeeDTO.class);
//    }

    //ModelMapper implementation using @Bean (see configs package for its Bean implementation)
    public EmployeeDTO getEmployeeById(Long employeeID) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).orElse(null);
        return mapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }


    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = mapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository. save(toSaveEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}
