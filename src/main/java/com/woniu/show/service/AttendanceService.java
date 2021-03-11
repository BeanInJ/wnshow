package com.woniu.show.service;

import com.woniu.show.pojo.Attendance;

import java.sql.Date;
import java.util.List;

public interface AttendanceService {
    void save(Attendance attendance);
    List<Attendance> findToday(Date attendance_time);
    Attendance findStudent(Date attendance_time,String student_name);
    void deleteById(Integer id);
}
