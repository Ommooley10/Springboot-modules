package com.codingshuttle.om.module2.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentEntity {

    @Id //used to define primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentID;

    private String title;

    private Boolean isActive;

    private LocalDateTime createdAt;
}
