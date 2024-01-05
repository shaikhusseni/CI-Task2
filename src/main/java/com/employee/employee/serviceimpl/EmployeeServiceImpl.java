package com.employee.employee.serviceimpl;

import com.employee.employee.dtos.EmployeeDto;
import com.employee.employee.entity.Employee;
import com.employee.employee.exceptionhandlers.ApiErrorCodes;
import com.employee.employee.exceptionhandlers.ApiErrorResponse;
import com.employee.employee.exceptionhandlers.InspireNetException;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    //    method for  saving employee in Data Base
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws InspireNetException {


// checks the duplicate present or not
        if (employeeRepository.existsByEmpEmail(employeeDto.getEmpEmail())) {
            throw new InspireNetException(ApiErrorCodes.INVALID_REQUEST, "This mobile is Already Existed");

        }
        if (employeeRepository.existsByEmpPhno(employeeDto.getEmpPhno())) {


            throw new InspireNetException(ApiErrorCodes.INVALID_REQUEST, "This Email is Already Existed ");

        }
        Employee map = modelMapper.map(employeeDto, Employee.class);

        Employee save = employeeRepository.save(map);

        return modelMapper.map(save, EmployeeDto.class);


    }

    //to getAll Employees Data from DB
    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();

        // Mapping each Employee to EmployeeDto using Stream and collect into a new list
        return allEmployees.stream()
                .map(this::empToDto)
                .collect(Collectors.toList());


    }




//    to update existed Employee Data

    @Override
    public EmployeeDto updateById(Long empId, Employee updatedEmployee) throws InspireNetException {
        Employee existingEmployee = employeeRepository.findById(empId).orElse(null);

//         checks employee is present or not  next condition executes
        if (existingEmployee != null) {

//            checks employee Email is unique or not  if it is unique update will done else  throw Exception
            if (updatedEmployee.getEmpEmail() != null &&
                    !updatedEmployee.getEmpEmail().equals(existingEmployee.getEmpEmail()) &&
                    employeeRepository.existsByEmpEmail(updatedEmployee.getEmpEmail())) {
                throw new InspireNetException(ApiErrorCodes.INTERNAL_SERVER_ERROR, "Employee with this email already exists");
            }


            //            checks employee phone number  is unique or not  if it is unique update will done else  throw Exception

            if (updatedEmployee.getEmpPhno() != null &&
                    !updatedEmployee.getEmpPhno().equals(existingEmployee.getEmpPhno()) &&
                    employeeRepository.existsByEmpPhno(updatedEmployee.getEmpPhno())) {
                throw new InspireNetException(ApiErrorCodes.INTERNAL_SERVER_ERROR, "Employee with this phone number already exists");
            }

            // Update other fields except for email and phone
            if (updatedEmployee.getEmpName() != null) {
                existingEmployee.setEmpName(updatedEmployee.getEmpName());
            }
            if (updatedEmployee.getEmpAddress() != null) {
                existingEmployee.setEmpAddress(updatedEmployee.getEmpAddress());
            }

            // Save the updated employee to DB
            Employee updatedEntity = employeeRepository.save(existingEmployee);

            return modelMapper.map(updatedEntity, EmployeeDto.class);
        }
        else {
            return null;
        }

    }

//    this method use to delete employees based on  id and If the employee is not existed in the db it throws Error Code in postman console
        @Override
        public void deleteById (Long empId) throws InspireNetException {
            Optional<Employee> employeeOptional = employeeRepository.findById(empId);

            if (employeeOptional.isPresent()) {
                employeeRepository.deleteById(empId);
            } else {
                throw new InspireNetException(ApiErrorCodes.RESOURCE_NOT_FOUND, "Employee with ID " + empId + " not found");
            }
        }

    //    this method use to get employees based on  id and If the employee is not existed in the db it throws Error Code in postman console

    public EmployeeDto getById(Long empId) throws InspireNetException {
            Optional<Employee> employeeOptional = employeeRepository.findById(empId);

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                return modelMapper.map(employee, EmployeeDto.class);
            } else {
                throw new InspireNetException(ApiErrorCodes.RESOURCE_NOT_FOUND, "Employee with ID " + empId + " not found");
            }


        }

//        model mapper to convert EmployeeDto to Employee Entity
public EmployeeDto empToDto(Employee emp) {
    EmployeeDto empDto = this.modelMapper.map(emp, EmployeeDto.class);
    return empDto;
}



}






//    @Override
//    public EmployeeDto getById(Long empId) throws InspireNetException {
//        // Try to retrieve the employee
//        Employee getEmp = employeeRepository.getById(empId);
//
//        // Check if the employee exists
//        if (getEmp == null) {
//            // If not found, throw a specific InspireNetException with RESOURCE_NOT_FOUND error code
//            throw new InspireNetException(ApiErrorCodes.RESOURCE_NOT_FOUND, "Employee with ID " + empId + " not found");
//        }
//
//        // If found, map to EmployeeDto and return
//        return modelMapper.map(getEmp, EmployeeDto.class);
//    }
//



//
//    @Override
//    public EmployeeDto deleteById(Long empId) throws InspireNetException {
//
//        employeeRepository.deleteById(empId);
//         return
//
//    }

//    @Override
//    public ResponseEntity<ApiErrorResponse> deleteById(Long empId) {
//        try {
//            employeeRepository.deleteById(empId);
//            return ResponseEntity.ok(new ApiErrorResponse("EMPLOYEE_DELETED")); // Successful deletion
//        } catch (Exception ex) {
//            // Handle unexpected errors
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage()));
//        }
//    }


