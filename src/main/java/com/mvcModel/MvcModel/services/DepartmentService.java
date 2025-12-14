package com.mvcModel.MvcModel.services;

import com.mvcModel.MvcModel.dtos.DepartmentDto;
import com.mvcModel.MvcModel.entities.Department;
import com.mvcModel.MvcModel.exceptions.ResourceNotFoundException;
import com.mvcModel.MvcModel.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentList =departmentRepository.findAll();
        return departmentList
                .stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class)).toList();

    }

    public DepartmentDto createNewDepartment(DepartmentDto departmentDto) {
        Department department= modelMapper.map(departmentDto, Department.class);
        Department savedDepartment=departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public Optional<DepartmentDto> getDepartmentById(Long deptId) {
        return departmentRepository.findById(deptId).
                map(department -> modelMapper.map(department, DepartmentDto.class));
    }

    public DepartmentDto updateDepartment(Long deptId, DepartmentDto departmentDto) {
        boolean isExist= departmentRepository.existsById(deptId);
        if(!isExist)    throw new ResourceNotFoundException("Department not found");
        Department oldDepartment= modelMapper.map(departmentDto, Department.class);
        oldDepartment.setId(deptId);
        Department savedDepartment= departmentRepository.save(oldDepartment);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public boolean deleteDepartment(Long deptId) {
        boolean isExist= departmentRepository.existsById(deptId);
        if(!isExist) throw new ResourceNotFoundException("Department not found");
        departmentRepository.deleteById(deptId);
        return true;
    }
}
