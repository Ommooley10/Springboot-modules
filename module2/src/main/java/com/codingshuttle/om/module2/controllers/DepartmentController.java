package com.codingshuttle.om.module2.controllers;


import com.codingshuttle.om.module2.dto.DepartmentDTO;
import com.codingshuttle.om.module2.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentID}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentID){
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(departmentID);
        return ResponseEntity.ok(departmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departmentDTOS = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDTOS);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment){
        DepartmentDTO departmentDTO = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(departmentDTO,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentID}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long departmentID){
        DepartmentDTO departmentDTO1 = departmentService.updateDepartment(departmentDTO, departmentID);
        return ResponseEntity.ok(departmentDTO1);
    }

    @DeleteMapping(path = "/{departmentID}")
    public ResponseEntity<Void> deleteDepartmentByID(@PathVariable Long departmentID){
        boolean deleted = departmentService.deleteDepartmentByID(departmentID);

        if(!deleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
