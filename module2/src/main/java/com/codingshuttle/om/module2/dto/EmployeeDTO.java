package com.codingshuttle.om.module2.dto;

import com.codingshuttle.om.module2.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be of valid format")
    private String email;

    @NotNull(message = "Age cannot be blank")
    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role cannot be empty")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee can be USER or ADMIN") //READYMADE ANNOTATION
    @EmployeeRoleValidation //CUSTOM VALIDATION USED
    private String role; //ADMIN, USER

    @PastOrPresent(message = "The data of joining cannot be in future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;
}