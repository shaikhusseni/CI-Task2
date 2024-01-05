package com.employee.employee.controller;


import com.employee.employee.dtos.EmployeeDto;
import com.employee.employee.entity.Employee;
import com.employee.employee.exceptionhandlers.InspireNetException;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    EmployeeRepository empRepo;


    //    To get all employees from DB
    @GetMapping("/getAll")
    public List<EmployeeDto> findAll() {
        return employeeService.getEmployees();
    }


    //    To save the object  into  to DB
    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employee) throws InspireNetException {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    //To get singel Employee Data based on employee Id  from data base this mapping we do

    @GetMapping("/getby/{empId}")
    public EmployeeDto getById(@PathVariable("empId") Long empId) throws InspireNetException {
        return employeeService.getById(empId);
    }


    //    To delete particular Employee Data Based on Id
    @DeleteMapping("/deleteby/{empId}")
    public void deleteEmployeeById(@PathVariable("empId") Long empId) throws InspireNetException {
        employeeService.deleteById(empId);
    }



    //    To update the Employee particular Employee based on his Id
    @PutMapping("/updateby/{empId}")
    public EmployeeDto updateEmployeeById(@PathVariable("empId") Long empId, @RequestBody Employee updatedEmployee) throws InspireNetException {
        return employeeService.updateById(empId, updatedEmployee);
    }






}