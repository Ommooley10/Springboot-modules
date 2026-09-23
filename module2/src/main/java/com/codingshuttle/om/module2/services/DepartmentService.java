package com.codingshuttle.om.module2.services;

import com.codingshuttle.om.module2.dto.DepartmentDTO;
import com.codingshuttle.om.module2.entities.DepartmentEntity;
import com.codingshuttle.om.module2.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }


    public DepartmentDTO getDepartmentById(Long departmentID) {
        DepartmentEntity departmentEntity = departmentRepository
                .findById(departmentID)
                .orElseThrow(() -> new NoSuchElementException("Department not found with id "+departmentID));
        return mapper.map(departmentEntity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> mapper.map(departmentEntity, DepartmentDTO.class))
                .toList();
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity = mapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toSaveEntity);
        return mapper.map(savedEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long departmentID) {
        DepartmentEntity departmentEntity = mapper.map(departmentDTO, DepartmentEntity.class);

        // If ID exists → UPDATE
        if (departmentRepository.existsById(departmentID)) {
            departmentEntity.setDepartmentID(departmentID);
            DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
            return mapper.map(savedDepartmentEntity, DepartmentDTO.class);
        }

        // If ID doesn't exist → CREATE
        departmentEntity.setDepartmentID(null);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return mapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public boolean deleteDepartmentByID(Long departmentID) {
        boolean exists = departmentRepository.existsById(departmentID);

        if(!exists){
            throw new NoSuchElementException("Department not found by id "+departmentID);
        }

        departmentRepository.deleteById(departmentID);
        return true;
    }
}
