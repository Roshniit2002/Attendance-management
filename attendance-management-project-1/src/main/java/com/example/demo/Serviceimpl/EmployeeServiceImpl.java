package com.example.demo.Serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.AttendanceStatus;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.AttendanceRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    

    @Override
    public void registerEmployee(Employee employee) {
    	
        if (employee == null || employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Employee email is required.");
        }

       
        employeeRepository.save(employee);
    }    
    
    @Override
    public boolean login(String username, String password) {
        
        Employee employee = employeeRepository.findByUsername(username);
        
        
        return employee != null && employee.getPassword().equals(password);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
   
    @Override
    public Attendance markAttendance(long employeeId, long managerId, AttendanceStatus status) {
      
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

       
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(LocalDate.now());
        attendance.setStatus(status);
        attendance.setApprovalStatus("PENDING");
        attendance.setManager_id(managerId); 
        
        attendanceRepository.save(attendance);
        return attendance;
    }
 
    @Transactional
    public void deleteEmployeeAndAttendance(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

      
        attendanceRepository.deleteByEmployee(employee);

      
        employeeRepository.delete(employee);
    }

        
        
    }



