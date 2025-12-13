package com.mvcModel.MvcModel.controllers;
import com.mvcModel.MvcModel.dtos.EmployeeDto;
import com.mvcModel.MvcModel.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmpolyeeById(@PathVariable(name="employeeId") Long id)
    {
        Optional<EmployeeDto> employeeDto=employeeService.getEmpolyeeById(id);
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElse(ResponseEntity.notFound().build());
    }

    // request param is to pass some values in the url to perform sorting and filtering the data, and it can be optional
    @GetMapping(path="")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false) String str)
    {
        return ResponseEntity.ok(employeeService.getAllEMployee());
    }

    // Request Body is to pass the whole object as a param in controller
    @PostMapping("")
    public ResponseEntity<EmployeeDto> createANewEmployee(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto createdEmployee=employeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  deleteEmployeeById(@PathVariable Long id)
    {
        boolean found=employeeService.deleteEmployeeById(id);
        if(found)   return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateFieldsOfEmployee(@PathVariable Long id, @RequestBody Map<String,Object> employeeField)
    {
        EmployeeDto updatedEmployee=employeeService.updateFieldsOfEmployee(id, employeeField);
        if(updatedEmployee==null)   return ResponseEntity.notFound().build();
        else    return ResponseEntity.ok(updatedEmployee);
    }
}
