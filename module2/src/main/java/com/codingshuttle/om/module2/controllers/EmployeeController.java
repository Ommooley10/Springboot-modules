package com.codingshuttle.om.module2.controllers;

import com.codingshuttle.om.module2.dto.EmployeeDTO;
import com.codingshuttle.om.module2.entities.EmployeeEntity;
import com.codingshuttle.om.module2.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    //DI in action
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping(path = "/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
//        return new EmployeeDTO(employeeID, "Om", "om@gmail.com", 27, LocalDate.of(2026, 11, 23), true);
//    }

    @GetMapping(path = "/{employeeID}")
    public EmployeeEntity getEmployeeByID(@PathVariable Long employeeID){
        return employeeRepository.findById(employeeID).orElse(null);
    }

//    @GetMapping
//    public String getAllEmployees(@RequestParam(required = false) Integer age,
//                                  @RequestParam(required = false) String sortBy){
//        return "Hi age "+age+" "+sortBy;
//    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hi from PUT";
    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
//    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
}
