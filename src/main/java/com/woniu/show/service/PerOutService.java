package com.woniu.show.service;

import com.woniu.show.pojo.Attendance;
import com.woniu.show.pojo.PerOut;

import java.sql.Date;
import java.util.List;

public interface PerOutService {
    void save(PerOut perOut);
    List<PerOut> findLast4();
    Iterable<PerOut> findAll();
    void deleteById(Integer id);
}
