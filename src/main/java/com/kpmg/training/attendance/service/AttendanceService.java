package com.kpmg.training.attendance.service;

import com.kpmg.training.attendance.entity.Attendance;
import com.kpmg.training.attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void markAttendance(Long employeeId, LocalDate date, String status) {
        Optional<Attendance> attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
        if (attendance.isPresent()) {
            Attendance attendanceRecord = attendance.get();
            attendanceRecord.setStatus(status);
            attendanceRepository.save(attendanceRecord);
        } else {
            Attendance newAttendanceRecord = new Attendance();
            newAttendanceRecord.setEmployeeId(employeeId);
            newAttendanceRecord.setDate(date);
            newAttendanceRecord.setStatus(status);
            attendanceRepository.save(newAttendanceRecord);
        }
    }

    public String getAttendance(Long employeeId, LocalDate date) {
        Optional<Attendance> attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
        if (attendance.isPresent()) {
            return attendance.get().getStatus();
        } else {
            return null;
        }
    }
}
