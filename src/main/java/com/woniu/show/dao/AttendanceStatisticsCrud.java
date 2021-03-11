package com.woniu.show.dao;

import com.woniu.show.pojo.AttendanceStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.sql.Date;

// 学生考勤统计
public interface AttendanceStatisticsCrud extends CrudRepository<AttendanceStatistics, Integer>, JpaRepository<AttendanceStatistics, Integer> {
    @Query(value = "select * from attendance_statistics where attendance_time=? limit 1;", nativeQuery = true)
    AttendanceStatistics findToday(Date attendance_time);
}
