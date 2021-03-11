package com.woniu.show.service;

import com.woniu.show.pojo.AttendanceStatistics;

import java.sql.Date;

public interface AttendanceStatisticsService {
    void save(AttendanceStatistics attendanceStatistics);
    AttendanceStatistics findToday(Date attendance_time);
}
