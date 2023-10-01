package com.example.EmployeeManager.resource;

import com.example.EmployeeManager.model.Employee;
import com.example.EmployeeManager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource (EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping ("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List <Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity <> (employees, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable ("id") Long id) {
        Employee foundEmployee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById (@PathVariable("id")Long id){
       employeeService.deleteEmployee(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
