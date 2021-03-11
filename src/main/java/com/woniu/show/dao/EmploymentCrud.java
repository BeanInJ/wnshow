package com.woniu.show.dao;

import com.woniu.show.pojo.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 已就业学生信息
public interface EmploymentCrud extends CrudRepository<Employment, Integer>, JpaRepository<Employment, Integer> {
    @Query(value = "select * from employment where month(employment_time)=?1 and year(employment_time)=?2 ;", nativeQuery = true)
    List<Employment> findByMonth(Integer month,Integer year);
    @Query(value = "select * from employment where woniu_id=?;", nativeQuery = true)
    Employment findByWoniuId(String w);
}
