package com.codingshuttle.om.module2.controllers;

import com.codingshuttle.om.module2.dto.EmployeeDTO;
import com.codingshuttle.om.module2.entities.EmployeeEntity;
import com.codingshuttle.om.module2.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    //DI in action
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    @GetMapping(path = "/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
//        return new EmployeeDTO(employeeID, "Om", "om@gmail.com", 27, LocalDate.of(2026, 11, 23), true);
//    }

    @GetMapping(path = "/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
        return employeeService.getEmployeeById(employeeID);
    }

//    @GetMapping
//    public String getAllEmployees(@RequestParam(required = false) Integer age,
//                                  @RequestParam(required = false) String sortBy){
//        return "Hi age "+age+" "+sortBy;
//    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
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
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }
}
