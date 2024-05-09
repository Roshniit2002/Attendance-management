package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Attendance;
import com.example.demo.Service.ManagerService;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    
    
    @PostMapping("/api/managers/login")
    public ResponseEntity<String> loginManager(@RequestBody ManagerLoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required.");
        }

        boolean loggedIn = managerService.login(username, password);
        if (loggedIn) {
            return ResponseEntity.ok("Manager logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }


    @PutMapping("/api/managers/attendance/{attendanceId}/approve")
    public ResponseEntity<String> approveAttendance(@PathVariable long attendanceId) {
        managerService.approveAttendance(attendanceId);
        return ResponseEntity.ok("Attendance approved successfully.");
    }

    @PutMapping("/api/managers/attendance/{attendanceId}/reject")
    public ResponseEntity<String> rejectAttendance(@PathVariable long attendanceId) {
        managerService.rejectAttendance(attendanceId);
        return ResponseEntity.ok("Attendance rejected successfully.");
    }
    @GetMapping("/api/managers/attendance")
    public ResponseEntity<List<Attendance>> getAllAttendanceRecords() {
        List<Attendance> attendanceRecords = managerService.getAllAttendanceRecords();
        return ResponseEntity.ok(attendanceRecords);
    }
    @PutMapping("/api/managers/attendance/approveAll")
    public ResponseEntity<String> approveAllAttendance() {
        managerService.approveAllAttendance();
        return ResponseEntity.ok("All attendance records approved successfully.");
    }
}
