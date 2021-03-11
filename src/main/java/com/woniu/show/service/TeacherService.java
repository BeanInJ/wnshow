package com.woniu.show.service;

import com.woniu.show.pojo.Teacher;
import java.util.Optional;

public interface TeacherService {
    Iterable<Teacher> findAllTeacher();
    void saveTeacher(Teacher teacher);
    Optional<Teacher> getTeacher(Integer id);
    String updateTeacher(Integer id, String name, String direction, String route) throws Exception;
    void deleteTeacher(Integer id);
    Teacher findByNme(String name);
}
