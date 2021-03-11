package com.woniu.show.dao;

import com.woniu.show.pojo.ClassRoom2Save;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 教室关灯打卡，记录每天打卡人信息
public interface ClassRoom2CrudSave extends CrudRepository<ClassRoom2Save, Integer> {
    @Query(value = "select * from classRoom2save limit ?;", nativeQuery = true)
    List<ClassRoom2Save> find(Integer i);
}
