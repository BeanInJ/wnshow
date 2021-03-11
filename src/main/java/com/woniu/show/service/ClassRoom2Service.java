package com.woniu.show.service;

import com.woniu.show.pojo.ClassRoom2;

import java.util.Optional;

public interface ClassRoom2Service {
    Iterable<ClassRoom2> findAllClassRooms2();
    void save(ClassRoom2 classRoom2);
    Optional<ClassRoom2> findById(int id);
}
