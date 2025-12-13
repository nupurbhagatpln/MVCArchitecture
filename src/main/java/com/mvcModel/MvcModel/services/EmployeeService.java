package com.mvcModel.MvcModel.services;

import com.mvcModel.MvcModel.dtos.EmployeeDto;
import com.mvcModel.MvcModel.entities.Employee;
import com.mvcModel.MvcModel.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDto getEmpolyeeById(Long id) {
        Employee newEmployee=employeeRepository.findById(id).orElse(null);
        return modelMapper.map(newEmployee,EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEMployee() {
       List<Employee> employeeList= employeeRepository.findAll();
       return employeeList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class) ).collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee=employeeRepository.save(newEmployee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);

    }
}
