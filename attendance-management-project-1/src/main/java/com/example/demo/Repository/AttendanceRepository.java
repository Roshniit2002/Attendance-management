package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	void deleteByEmployee(Employee employee);
    
}