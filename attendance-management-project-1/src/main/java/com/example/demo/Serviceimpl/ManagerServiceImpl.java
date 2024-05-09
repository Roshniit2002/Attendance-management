package com.example.demo.Serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Manager;
import com.example.demo.Repository.AttendanceRepository;
import com.example.demo.Repository.ManagerRepository;
import com.example.demo.Service.ManagerService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public boolean login(String username, String password) {
        
        Manager manager = managerRepository.findByUsername(username);
        if (manager == null) {
          
            return false;
        }
        
        return manager.getPassword().equals(password);
    }

    @Override
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }
    
    @Override
    @Transactional
    public void approveAttendance(long attendanceId) {
        
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new EntityNotFoundException("Attendance record not found with id: " + attendanceId));

      
        attendance.setApprovalStatus("APPROVED");

     
        attendanceRepository.save(attendance);
    }
    @Override
    public void rejectAttendance(long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found with id: " + attendanceId));

        
        attendance.setApprovalStatus("REJECTED");
        
        attendanceRepository.save(attendance);
    }
    @Override
    public void approveAllAttendance() {
        List<Attendance> attendanceList = attendanceRepository.findAll();
        for (Attendance attendance : attendanceList) {
            attendance.setApprovalStatus("APPROVED");
        }
        attendanceRepository.saveAll(attendanceList);
    }

	
}
