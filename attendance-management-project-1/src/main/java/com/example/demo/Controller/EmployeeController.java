package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.AttendanceStatus;
import com.example.demo.Model.Employee;

import com.example.demo.Service.EmployeeService;
import com.example.demo.exception.EmployeeRegistrationException;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/api/employees/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeRequest employeeRequest) {
  
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setUsername(employeeRequest.getUsername());
        employee.setPassword(employeeRequest.getPassword());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setRole(employeeRequest.getRole());
        employee.setManagerId(employeeRequest.getManager_id());
        employeeService.registerEmployee(employee);
        return ResponseEntity.ok("Employee registered successfully.");
    }

    @PostMapping("/api/employees/login")
    public ResponseEntity<String> loginEmployee(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required.");
        }

        boolean loggedIn = employeeService.login(username, password);
        if (loggedIn) {
            return ResponseEntity.ok("Employee logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(employees);
        }
    }
    @PostMapping("/api/employees/{employeeId}/mark")
    public ResponseEntity<Attendance> markAttendance(@PathVariable long employeeId, @RequestParam long managerId, @RequestParam AttendanceStatus status) {
        try {
            Attendance attendance = employeeService.markAttendance(employeeId, managerId, status);
            return new ResponseEntity<>(attendance, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/api/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) {
        try {
            employeeService.deleteEmployeeAndAttendance(employeeId);
            return ResponseEntity.ok("Employee and associated attendance records deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}

