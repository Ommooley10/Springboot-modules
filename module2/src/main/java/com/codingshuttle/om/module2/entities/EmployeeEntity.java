package com.codingshuttle.om.module2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {

    @Id //used to define primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //used to generate auto-incremented key
    private Long id;

    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;
    private String role;
}