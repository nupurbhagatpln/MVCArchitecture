package com.mvcModel.MvcModel.services;

import com.mvcModel.MvcModel.dtos.EmployeeDto;
import com.mvcModel.MvcModel.entities.Employee;
import com.mvcModel.MvcModel.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public Optional<EmployeeDto> getEmpolyeeById(Long id) {
        return employeeRepository.
                findById(id).map(employee -> modelMapper.map(employee,EmployeeDto.class));
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

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee oldEmployee= modelMapper.map(employeeDto, Employee.class);
        oldEmployee.setId(id);
        Employee savedEmployee=employeeRepository.save(oldEmployee);
        return modelMapper.map(oldEmployee, EmployeeDto.class);

    }

    public Boolean deleteEmployeeById(Long id) {
        boolean exits= employeeRepository.existsById(id);
        if(!exits)  return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDto updateFieldsOfEmployee(Long id, Map<String, Object> updates) {

        boolean exits= employeeRepository.existsById(id);
        if(!exits)  return null;

        Employee oldEmployee=employeeRepository.findById(id).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated= ReflectionUtils.findField(Employee.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,oldEmployee,value);
        });
        employeeRepository.save(oldEmployee);
        return modelMapper.map(oldEmployee,EmployeeDto.class);

    }
}
