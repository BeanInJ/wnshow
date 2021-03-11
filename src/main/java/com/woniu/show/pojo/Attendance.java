package com.woniu.show.pojo;


import javax.persistence.*;
import java.sql.Date;

/**
 * 学生考勤表
 * 本考勤表并不会记录从官网下载的考勤信息，
 * 会记录手动录入的考勤信息
 */
@Entity(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 学生姓名
    private String student_name;
    // 带班老师
    private String teacher_name;
    // 学习方向
    private String major;
    // 第多少期的学生
    private String class_no;
    // 考勤时间
    private Date attendance_time;

    // 逻辑删除
    private String isDelete;
    // 状态
    private String result;

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", student_name='" + student_name + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", major='" + major + '\'' +
                ", class_no='" + class_no + '\'' +
                ", attendance_time=" + attendance_time +
                ", isDelete='" + isDelete + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    public Date getAttendance_time() {
        return attendance_time;
    }

    public void setAttendance_time(Date attendance_time) {
        this.attendance_time = attendance_time;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
