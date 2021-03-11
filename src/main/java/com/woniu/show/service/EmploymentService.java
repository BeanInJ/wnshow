package com.woniu.show.service;

import com.woniu.show.pojo.Employment;

import java.util.List;

public interface EmploymentService {
    void save(Employment em);
    List<Employment> findByMonth(Integer month,Integer year);
    void deleteById(Integer id);
    Employment findByWoniuId(String w);
}
