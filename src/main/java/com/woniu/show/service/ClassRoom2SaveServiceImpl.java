package com.woniu.show.service;

import com.woniu.show.dao.ClassRoom2CrudSave;
import com.woniu.show.pojo.ClassRoom2Save;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoom2SaveServiceImpl implements ClassRoom2SaveService{

    @Autowired
    ClassRoom2CrudSave c2s;


    @Override
    public Iterable<ClassRoom2Save> findAll() {
        return c2s.findAll();
    }

    @Override
    public void save(ClassRoom2Save c) {
        c2s.save(c);
    }

    @Override
    public Optional<ClassRoom2Save> findById(int id) {
        return c2s.findById(id);
    }

    @Override
    public List<ClassRoom2Save> find(Integer i) {
        return c2s.find(i);
    }

}
