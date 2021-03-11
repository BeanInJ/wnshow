package com.woniu.show.pojo;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

/**
 * 就业信息表
 */
@Entity(name = "employment")
public class Employment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    // 学生姓名
    private String name;
    // 学生学号
    private String woniuId;
    // 就业单位
    private String company;
    // 学生学历
    private String education;
    // 就业职位
    private String position;
    // 薪资
    private String salary;

    @Override
    public String toString() {
        return "Employment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", woniuId='" + woniuId + '\'' +
                ", company='" + company + '\'' +
                ", education='" + education + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", employmentTime=" + employmentTime +
                ", enterTime=" + enterTime +
                '}';
    }

    public String getWoniuId() {
        return woniuId;
    }

    public void setWoniuId(String woniuId) {
        this.woniuId = woniuId;
    }

    // 就业日期
    @Column(name = "employment_time")
    private Date employmentTime;

    // 录入日期
    @CreatedDate
    @Column(name = "enter_time")
    private Date enterTime;

    public Date getEmploymentTime() {
        return employmentTime;
    }

    public void setEmploymentTime(Date employmentTime) {
        this.employmentTime = employmentTime;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
