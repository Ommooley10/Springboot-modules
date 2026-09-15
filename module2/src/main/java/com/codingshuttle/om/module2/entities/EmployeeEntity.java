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

    @Id //used for setting the primary key here Long "id" is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //tell hibernate to generate auto incremented key
    private Long id;

    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
