package com.woniu.show.dao;

import com.woniu.show.pojo.Duty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 每周值班信息
public interface DutyCrud extends CrudRepository<Duty, Integer> {
    @Query(value = "select * from duty limit 7;", nativeQuery = true)
    List<Duty> findFront7();
}
