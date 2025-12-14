package com.mvcModel.MvcModel.dtos;

import com.mvcModel.MvcModel.annotations.PasswordValidationAnnotation;
import com.mvcModel.MvcModel.annotations.PrimeNumberValidationAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @PrimeNumberValidationAnnotation
    private Long even;

    @PasswordValidationAnnotation
    private String password;

    private Boolean isActive;

    @PastOrPresent(message = "Date should not be future date")
    private LocalDate createdAt;
}
