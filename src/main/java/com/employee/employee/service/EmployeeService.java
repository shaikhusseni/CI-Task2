package com.employee.employee.service;

import com.employee.employee.dtos.EmployeeDto;
import com.employee.employee.entity.Employee;
import com.employee.employee.exceptionhandlers.ApiErrorResponse;
import com.employee.employee.exceptionhandlers.InspireNetException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {


    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws InspireNetException;

    public List<EmployeeDto> getEmployees();

    public EmployeeDto getById(Long empId) throws InspireNetException;


    EmployeeDto updateById(Long empId, Employee updatedEmployee) throws InspireNetException;

//    public ResponseEntity<ApiErrorResponse> deleteById(Long empId);

    public void deleteById(Long empId) throws InspireNetException;

}
