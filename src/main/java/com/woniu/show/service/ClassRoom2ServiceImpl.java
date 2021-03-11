package com.woniu.show.service;

import com.woniu.show.dao.ClassRoom2Crud;
import com.woniu.show.pojo.ClassRoom2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassRoom2ServiceImpl implements ClassRoom2Service{

    @Autowired
    ClassRoom2Crud classRoom2Crud;

    @Override
    public Iterable<ClassRoom2> findAllClassRooms2() {
        return classRoom2Crud.findAll();
    }


    @Override
    public void save(ClassRoom2 classRoom2) {
       classRoom2Crud.save(classRoom2);
    }

    @Override
    public Optional<ClassRoom2> findById(int id) {
        return classRoom2Crud.findById(id);
    }
}
