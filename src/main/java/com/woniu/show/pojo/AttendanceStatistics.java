package com.woniu.show.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 学生考勤统计表
 */
@Entity(name = "attendance_statistics")
public class AttendanceStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 考勤时间
    private Date attendance_time;
    // 迟到、请假人数
    private Integer num;

    @Override
    public String toString() {
        return "AttendanceStatistics{" +
                "id=" + id +
                ", attendance_time=" + attendance_time +
                ", num=" + num +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAttendance_time() {
        return attendance_time;
    }

    public void setAttendance_time(Date attendance_time) {
        this.attendance_time = attendance_time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
