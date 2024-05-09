package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Attendance;
import com.example.demo.Model.Manager;

public interface ManagerService {
	void approveAttendance(long attendanceId);

	void rejectAttendance(long attendanceId);
	List<Attendance> getAllAttendanceRecords();

	boolean login(String username, String password);
    void approveAllAttendance();

	}
