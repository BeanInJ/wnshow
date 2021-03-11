package com.woniu.show.service;

import com.woniu.show.dao.AttendanceCrud;
import com.woniu.show.pojo.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    private AttendanceCrud attendanceCrud;
    @Override
    public void save(Attendance attendance) {
        attendanceCrud.save(attendance);
    }

    @Override
    public List<Attendance> findToday(Date attendance_time) {
        return attendanceCrud.findToday(attendance_time);
    }

    @Override
    public Attendance findStudent(Date attendance_time, String student_name) {
        return attendanceCrud.findStudent(attendance_time, student_name);
    }

    @Override
    public void deleteById(Integer id) {
        attendanceCrud.deleteById(id);
    }
}
