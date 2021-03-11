package com.woniu.show.service;

import com.woniu.show.dao.AttendanceStatisticsCrud;
import com.woniu.show.pojo.AttendanceStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AttendanceStatisticsServiceImpl implements AttendanceStatisticsService {
    @Autowired
    private AttendanceStatisticsCrud attendanceStatisticsCrud;
    @Override
    public void save(AttendanceStatistics attendanceStatistics) {
        attendanceStatisticsCrud.save(attendanceStatistics);
    }

    @Override
    public AttendanceStatistics findToday(Date attendance_time) {
        return attendanceStatisticsCrud.findToday(attendance_time);
    }


}
