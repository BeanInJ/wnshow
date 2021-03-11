package com.woniu.show.service;

import com.woniu.show.dao.EmploymentCrud;
import com.woniu.show.pojo.Employment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentServiceImpl implements EmploymentService{
    @Autowired
    private EmploymentCrud employmentCrud;

    @Override
    public void save(Employment em) {
        employmentCrud.save(em);
    }

    @Override
    public List<Employment> findByMonth(Integer month,Integer year) {
        return employmentCrud.findByMonth(month,year);
    }

    @Override
    public void deleteById(Integer id) {
        employmentCrud.deleteById(id);
    }

    @Override
    public Employment findByWoniuId(String w) {
        return employmentCrud.findByWoniuId(w);
    }
}
