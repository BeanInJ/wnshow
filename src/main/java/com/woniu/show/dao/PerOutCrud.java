package com.woniu.show.dao;

import com.woniu.show.pojo.PerOut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 劝退学生信息
public interface PerOutCrud extends CrudRepository<PerOut, Integer> {
    @Query(value = "select * from per_out order by id DESC limit 4;", nativeQuery = true)
    List<PerOut> findLast4();
}
