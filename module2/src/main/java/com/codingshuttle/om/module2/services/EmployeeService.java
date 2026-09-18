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
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }


    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeID) {

        EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);

        // If ID exists → UPDATE
        if (employeeRepository.existsById(employeeID)) {
            employeeEntity.setId(employeeID);
            EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
            return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
        }

        // If ID doesn't exist → CREATE
        employeeEntity.setId(null);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeID) {

        boolean exists = employeeRepository.existsById(employeeID);

        if (!exists) {
            return false;
        }

        employeeRepository.deleteById(employeeID);
        return true;
    }


    public EmployeeDTO updateEmployeePartially(EmployeeDTO employeeDTO, Long employeeID) {

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employeeDTO.getName() != null) {
            employeeEntity.setName(employeeDTO.getName());
        }

        if (employeeDTO.getEmail() != null) {
            employeeEntity.setEmail(employeeDTO.getEmail());
        }

        if (employeeDTO.getAge() != null) {
            employeeEntity.setAge(employeeDTO.getAge());
        }

        if (employeeDTO.getDateOfJoining() != null) {
            employeeEntity.setDateOfJoining(
                    employeeDTO.getDateOfJoining());
        }

        if (employeeDTO.getIsActive() != null) {
            employeeEntity.setIsActive(
                    employeeDTO.getIsActive());
        }

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);

        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}