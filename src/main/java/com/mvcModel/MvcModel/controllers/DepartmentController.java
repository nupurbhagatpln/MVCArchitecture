package com.mvcModel.MvcModel.controllers;

import com.mvcModel.MvcModel.dtos.DepartmentDto;
import com.mvcModel.MvcModel.entities.Employee;
import com.mvcModel.MvcModel.exceptions.ResourceNotFoundException;
import com.mvcModel.MvcModel.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long deptId)
    {
        Optional<DepartmentDto> departmentDto= departmentService.getDepartmentById(deptId);
        return departmentDto.map(departmentDto1 -> ResponseEntity.ok(departmentDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Department not found"));
    }

    @PostMapping("")
    public ResponseEntity<DepartmentDto> createNewDepartment(@RequestBody @Valid DepartmentDto departmentDto)
    {
        DepartmentDto newDepartmentDto=departmentService.createNewDepartment(departmentDto);
        return new ResponseEntity<>(newDepartmentDto, HttpStatus.CREATED);
    }

    @PutMapping("/{deptId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long deptId, @RequestBody @Valid DepartmentDto departmentDto)
    {
        DepartmentDto  updatedDepartment= departmentService.updateDepartment(deptId,departmentDto);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<Boolean> deleteDepartemmt(@PathVariable Long deptId)
    {
        boolean departmentDeleted= departmentService.deleteDepartment(deptId);
        if(departmentDeleted) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

}
