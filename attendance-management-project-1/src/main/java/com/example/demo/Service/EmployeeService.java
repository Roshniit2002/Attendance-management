package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.AttendanceStatus;
import com.example.demo.Model.Employee;
import com.example.demo.Model.Manager;

public interface EmployeeService {
    void registerEmployee(Employee employeeDTO);
    boolean login(String username, String password);
    List<Employee> getAllEmployees();
	Attendance markAttendance(long employeeId, long managerId, AttendanceStatus status);
	void deleteEmployeeAndAttendance(long employeeId);
    
}

