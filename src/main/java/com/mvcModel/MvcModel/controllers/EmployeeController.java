package com.mvcModel.MvcModel.controllers;

import com.mvcModel.MvcModel.dtos.EmployeeDto;
import com.mvcModel.MvcModel.entities.Employee;
import com.mvcModel.MvcModel.repositories.EmployeeRepository;
import com.mvcModel.MvcModel.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employee")
@RequiredArgsConstructor
public class EmployeeController {

//    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @GetMapping(path="/{employeeId}")
    public EmployeeDto getEmpolyeeById(@PathVariable(name="employeeId") Long id)
    {
//        return new EmployeeDto(id,"Nupur","nupur@gmail.com",23, LocalDate.of(2000,2,24),true);
        return  employeeService.getEmpolyeeById(id);
    }

    // request param is to pass some values in the url to perform sorting and filtering the data, and it can be optional
    @GetMapping(path="")
    public List<EmployeeDto> getAllEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false) String str)
    {
        //        return "All Employee"+age+" "+str;
        return employeeService.getAllEMployee();
    }

    // Request Body is to pass the whole object as a param in controller
    @PostMapping("")
    public EmployeeDto createANewEmployee(@RequestBody EmployeeDto employeeDto)
    {
//        employeeDto.setId(120L);
        return  employeeService.createNewEmployee(employeeDto);
    }

}
