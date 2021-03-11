package com.woniu.show.dao;

import com.woniu.show.pojo.ClassRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 教室信息表
public interface ClassRoomCrud extends CrudRepository<ClassRoom, Integer> {
    @Query(value = "select * from classroom where is_use = 1;", nativeQuery = true)
    List<ClassRoom> findByIsUse();
}
