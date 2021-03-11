package com.woniu.show.pojo;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
//import java.sql.Date;

@Entity(name = "danmu")
public class Danmu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String text;
//    private String color;
    private String userAgent;
    private String ip;

    @CreatedDate
    @Column(name = "enter_time")
    private Date enterTime;

    @Override
    public String toString() {
        return "Danmu{" +
                "id=" + id +
                ", text='" + text + '\'' +
//                ", color='" + color + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", ip='" + ip + '\'' +
                ", enterTime=" + enterTime +
                '}';
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }
}
