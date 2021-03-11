package com.woniu.show.pojo;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 教室表,记录每天打卡的人
 */
@Entity(name = "classroom2save")
public class ClassRoom2Save {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    // 哪间教室
    private String room;
    // 是否有人
    private boolean isEmpty;
    private String userAgent;
    private String ip;

    @CreatedDate
    @Column(name = "enter_time")
    private Date enterTime;

    @Override
    public String toString() {
        return "ClassRoom2Save{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", isEmpty=" + isEmpty +
                ", userAgent='" + userAgent + '\'' +
                ", ip='" + ip + '\'' +
                ", enterTime=" + enterTime +
                '}';
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

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }
}
