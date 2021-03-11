package com.woniu.show.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 劝退信息表
 */
@Entity(name = "per_out")
public class PerOut {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;
    // 班级
    private String clo;
    // 入学时间
    private String intime;
    // 原因
    private String reason;

    @Override
    public String toString() {
        return "PerOut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clo='" + clo + '\'' +
                ", intime='" + intime + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClo() {
        return clo;
    }

    public void setClo(String clo) {
        this.clo = clo;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
