package com.mvcModel.MvcModel.controllers;

import com.mvcModel.MvcModel.dtos.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

    @GetMapping(path="/{employeeId}")
    public EmployeeDto getEmpolyeeById(@PathVariable(name="employeeId") Long id)
    {
        return new EmployeeDto(id,"Nupur","nupur@gmail.com",23, LocalDate.of(2000,2,24),true);
    }

    // request param is to pass some values in the url to perform sortinf and filtering the data, and it can be optional
    @GetMapping(path="")
    public String getAllEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false) String str)
    {
        return "All Employee"+age+" "+str;
    }

    // Request Body is to pass the whole object as a param in controller
    @PostMapping("")
    public EmployeeDto createANewEmployee(@RequestBody EmployeeDto employeeDto)
    {
        employeeDto.setId(120L);
        return  employeeDto;
    }

}
