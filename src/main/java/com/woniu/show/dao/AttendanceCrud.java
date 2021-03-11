package com.woniu.show.dao;

import com.woniu.show.pojo.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

// 学生考勤
public interface AttendanceCrud extends CrudRepository<Attendance, Integer>, JpaRepository<Attendance, Integer> {
    @Query(value = "select * from attendance where attendance_time=?;", nativeQuery = true)
    List<Attendance> findToday(Date attendance_time);
    @Query(value = "select * from attendance where attendance_time=?1 and student_name=?2 limit 1;", nativeQuery = true)
    Attendance findStudent(Date attendance_time,String student_name);
}