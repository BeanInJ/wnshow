package com.woniu.show.dao;

import com.woniu.show.pojo.Danmu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

// 弹幕
public interface DanmuCrud extends CrudRepository<Danmu, Integer>, JpaRepository<Danmu, Integer> {
    @Query(value = "SELECT * FROM danmu order by id DESC limit 40 ;", nativeQuery = true)
    List<Danmu> find20();
}