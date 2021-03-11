package com.woniu.show.dao;

import com.woniu.show.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// 讲师信息
public interface TeacherCrud extends CrudRepository<Teacher, Integer>, JpaRepository<Teacher, Integer> {
    @Query(value = "select * from teacher where name = ? limit 1;", nativeQuery = true) // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    Teacher findByNme(String name);
}
