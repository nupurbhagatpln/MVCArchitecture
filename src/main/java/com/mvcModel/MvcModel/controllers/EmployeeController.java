package com.mvcModel.MvcModel.controllers;

import com.mvcModel.MvcModel.dtos.EmployeeDto;
import com.mvcModel.MvcModel.entities.Employee;
import com.mvcModel.MvcModel.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping(path="/{employeeId}")
    public Employee getEmpolyeeById(@PathVariable(name="employeeId") Long id)
    {
//        return new EmployeeDto(id,"Nupur","nupur@gmail.com",23, LocalDate.of(2000,2,24),true);
        return  employeeRepository.findById(id).orElse(null);
    }

    // request param is to pass some values in the url to perform sortinf and filtering the data, and it can be optional
    @GetMapping(path="")
    public List<Employee> getAllEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false) String str)
    {
        //        return "All Employee"+age+" "+str;
        return employeeRepository.findAll();
    }

    // Request Body is to pass the whole object as a param in controller
    @PostMapping("")
    public Employee createANewEmployee(@RequestBody Employee employee)
    {
//        employeeDto.setId(120L);
        return  employeeRepository.save(employee);
    }

}
