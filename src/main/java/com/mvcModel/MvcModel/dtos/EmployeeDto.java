package com.mvcModel.MvcModel.dtos;

import com.mvcModel.MvcModel.annotations.EmployeeRoleValidationAnnotation;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class EmployeeDto {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 32,message = "Name should be atleast 3 character")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid Email")
    private String email;

    @NotNull(message = "Age is required")
    @Max(value=80, message = "Age should be below 80")
    @Min(value = 18, message = "Age should be above 18")
    private Integer age;

    @NotBlank(message = "Role is required")
//    @Pattern(regexp = "^(Admin|User)$",message = "Enter role: Admin or User")
    @EmployeeRoleValidationAnnotation
    private String role;

    @NotNull(message="Salary is required")
    @Positive(message = "Salary should be positive")
    @Digits(integer = 6, fraction = 2, message = "Salary should be in the format XXXXXX.YY")
    @DecimalMin(value = "100.00")
    @DecimalMax(value = "999999.99")
    private Double salary;

    @PastOrPresent(message="Date cannot be future date")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;

    public EmployeeDto(){

    }

    public EmployeeDto(Long id, String name, String email, Integer age, String role, Double salary, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
