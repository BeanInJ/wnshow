package com.woniu.show.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 教室表
 */
@Entity(name = "classroom")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    // 哪间教室
    private String room;
    // 第多少期的学生
    private String semester;
    private String teacher;
    // 学生数量
    private Integer studentNumber;
    // 学习方向
    private String direction;
    // 学习阶段
    private String stage;
    // 是否在用
    private String isUse;

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", semester='" + semester + '\'' +
                ", teacher='" + teacher + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", direction='" + direction + '\'' +
                ", stage='" + stage + '\'' +
                ", isUse='" + isUse + '\'' +
                '}';
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
