package com.woniu.show.service;

import com.woniu.show.dao.PerOutCrud;
import com.woniu.show.pojo.PerOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PerOutServiceImpl implements PerOutService {
   @Autowired
    private PerOutCrud perOutCrud;

    @Override
    public void save(PerOut perOut) {
        perOutCrud.save(perOut);
    }

    @Override
    public List<PerOut> findLast4() {
        return perOutCrud.findLast4();
    }

    @Override
    public Iterable<PerOut> findAll() {
        return perOutCrud.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        perOutCrud.deleteById(id);
    }
}