package com.woniu.show.service;

import com.woniu.show.pojo.ClassRoom2Save;

import java.util.List;
import java.util.Optional;

public interface ClassRoom2SaveService {
    Iterable<ClassRoom2Save> findAll();
    void save(ClassRoom2Save c);
    Optional<ClassRoom2Save> findById(int id);
    List<ClassRoom2Save> find(Integer i);
}
