package com.woniu.show.service;

import com.woniu.show.dao.DutyCrud;
import com.woniu.show.pojo.Duty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyServiceImpl implements DutyService{
    @Autowired
    private DutyCrud dutyCrud;

    @Override
    public List<Duty> findFront7() {
        return dutyCrud.findFront7();
    }

    @Override
    public void saveDuty(Duty duty) {
        dutyCrud.save(duty);
    }

    @Override
    public void deleteById(Integer id) {
        dutyCrud.deleteById(id);
    }

}
