package com.codingshuttle.om.module2.controllers;

import com.codingshuttle.om.module2.dto.EmployeeDTO;
import com.codingshuttle.om.module2.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping(path = "/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID) {
//        return employeeService.getEmployeeById(employeeID);
//    }

    @GetMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable Long employeeID) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeID);
        return ResponseEntity.ok(employeeDTO);
    }

//    @GetMapping
//    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
//        return employeeService.getAllEmployees();
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        List<EmployeeDTO> employeesDTO = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeesDTO);
    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
//        return employeeService.createNewEmployee(inputEmployee);
//    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
        EmployeeDTO employeeDTO = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

//    @PutMapping(path = "/{employeeID}")
//    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeID) {
//        return employeeService.updateEmployeeById(employeeDTO, employeeID);
//    }

    @PutMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeID) {
        EmployeeDTO employee = employeeService.updateEmployeeById(employeeDTO, employeeID);
        return ResponseEntity.ok(employee);
    }

//    @PatchMapping(path = "/{employeeID}")
//    public EmployeeDTO updateEmployeePartially(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeID) {
//        return employeeService.updateEmployeePartially(employeeDTO, employeeID);
//    }

    @PatchMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeePartially(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeID) {
        EmployeeDTO employee = employeeService.updateEmployeePartially(employeeDTO, employeeID);
        return ResponseEntity.ok(employee);
    }

//    @DeleteMapping(path = "/{employeeID}")
//    public boolean deleteEmployeeById(@PathVariable Long employeeID) {
//        return employeeService.deleteEmployeeById(employeeID);
//    }

    @DeleteMapping(path = "/{employeeID}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long employeeID) {
        boolean deleted = employeeService.deleteEmployeeById(employeeID);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}