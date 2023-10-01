package com.example.EmployeeManager.service;

import com.example.EmployeeManager.exception.UserNotFoundException;
import com.example.EmployeeManager.model.Employee;
import com.example.EmployeeManager.repo.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    //Necessary field in this class to talk to repo layer
    private final EmployeeRepo employeeRepo;

    //Dependency injecting EmployeeRepo object in constructor
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    //To find all employees
    public List <Employee> findAllEmployees () {
        return employeeRepo.findAll();
    }
    //To add a new employee
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    //To update an employee
    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }
    //To find an employee by Id
    public Employee findEmployeeById (Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(()-> new UserNotFoundException("Employee by id "+id+" was not found"));
    }

    public void deleteEmployee (Long id){
        employeeRepo.deleteEmployeeById(id);
    }
}
