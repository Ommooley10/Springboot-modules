package com.codingshuttle.om.module4.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;


    private String name;


    private String email;


    private Integer age;


    private String role; //ADMIN, USER


    private LocalDate dateOfJoining;


    private Boolean isActive;
}
