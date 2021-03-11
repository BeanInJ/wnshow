package com.woniu.show.util.utilPojo;

import com.woniu.show.pojo.Attendance;

import java.util.List;

public class AttendanceDownloadReturn {
    private List<Attendance> attendances;
    private String msg;
    private String status;

    @Override
    public String toString() {
        return "AttendanceDownloadReturn{" +
                "attendances=" + attendances +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
