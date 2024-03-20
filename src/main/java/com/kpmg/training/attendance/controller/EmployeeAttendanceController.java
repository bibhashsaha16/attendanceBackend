package com.kpmg.training.attendance.controller;

import com.kpmg.training.attendance.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees/{employeeId}/attendance")
public class EmployeeAttendanceController {

    private final AttendanceService attendanceService;

    public EmployeeAttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping public ResponseEntity<Map<String, String>> markAttendance(@PathVariable Long employeeId, @RequestParam LocalDate date, @RequestParam String status) {
        attendanceService.markAttendance(employeeId, date, status);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Attendance added");
        return ResponseEntity.ok().body(response);
    }



    @GetMapping("/{date}")
    public ResponseEntity<String> getAttendance(@PathVariable Long employeeId, @PathVariable LocalDate date) {
        String attendanceStatus = attendanceService.getAttendance(employeeId, date);
        if (attendanceStatus != null) {
            return ResponseEntity.ok(attendanceStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

