package com.woniu.show.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 教室表,每天关灯与否
 */
@Entity(name = "classroom2")
public class ClassRoom2 {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    // 哪间教室
    private String room;
    // 是否有人
    private boolean isEmpty;

    @Override
    public String toString() {
        return "ClassRoom2{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", isEmpty=" + isEmpty +
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
}
