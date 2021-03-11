package com.woniu.show.service;

import com.woniu.show.pojo.Duty;

import java.util.List;

public interface DutyService {
    List<Duty> findFront7();
    void saveDuty(Duty duty);
    void deleteById(Integer id);
}
