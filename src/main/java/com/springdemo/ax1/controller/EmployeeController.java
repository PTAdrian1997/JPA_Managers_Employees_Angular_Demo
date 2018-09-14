package com.springdemo.ax1.controller;

import com.springdemo.ax1.exception.ResourceNotFoundException;
import com.springdemo.ax1.model.Employee;
import com.springdemo.ax1.repository.EmployeeRepository;
import com.springdemo.ax1.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){return this.employeeRepository.findAll();}

    @GetMapping("/managers/{managerId}/employees")
    public List<Employee> getEmployeesById(
            @PathVariable (value = "managerId") Long managerId
    ){
        return this.employeeRepository.findAll().stream()
                .filter(employee -> employee.getManager().getId() == managerId)
                .collect(Collectors.toList());
    }

    @PostMapping("/managers/{managerId}/employees")
    public Employee createEmployee(
            @PathVariable (value = "managerId") Long managerId,
            @Valid @RequestBody Employee newEmployee
    ){
        return this.managerRepository.findById(managerId).map(manager -> {
            newEmployee.setManager(manager);
            return this.employeeRepository.save(newEmployee);
        }).orElseThrow(() -> new ResourceNotFoundException("manager id " + managerId + " not found"));
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable (value = "employeeId") Long employeeId
    ){
        return this.employeeRepository.findById(employeeId).map(employee -> {
            this.employeeRepository.delete(employee);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("employee id " + employeeId + " not found"));
    }

}
