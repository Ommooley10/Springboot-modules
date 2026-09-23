package com.codingshuttle.om.module2.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long departmentID;

    @NotBlank(message = " Department name cannot be empty")
    private String title;

    @AssertTrue(message = "Department should be active")
    private Boolean isActive;

    private LocalDateTime createdAt;
}
