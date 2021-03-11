package com.woniu.show.service;

import com.woniu.show.dao.TeacherCrud;
import com.woniu.show.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherCrud teacherCrud;

    @Override
    public Iterable<Teacher> findAllTeacher() {
        return teacherCrud.findAll();
    }

    @Override
    public void saveTeacher(Teacher Teacher) {
        teacherCrud.save(Teacher);
    }

    @Override
    public Optional<Teacher> getTeacher(Integer id) {
        return  teacherCrud.findById(id);
    }

    @Override
    public String updateTeacher(Integer id, String name,String direction,String route) throws Exception {
        Teacher teacherOld = teacherCrud.getOne(id);
        Teacher teacher = new Teacher();
        teacher.setId(id);
        if (route == null)  {
            teacher.setImg(teacherOld.getImg());
        } else {
            teacher.setImg(route);
        }
        if (name == null)  {
            teacher.setName(teacherOld.getName());
        } else if( !name.equals(teacherOld.getName()) ){
            if (teacherCrud.findByNme(name) != null) return "讲师姓名重复";
        }else {
            teacher.setName(name);
        }
        if(direction == null){
            teacher.setDirection(teacherOld.getDirection());
        } else {
            teacher.setDirection(direction);
        }
        teacherCrud.save(teacher);
        return "更新讲师信息成功";
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherCrud.deleteById(id);
    }

    @Override
    public Teacher findByNme(String name) {
        return teacherCrud.findByNme(name);
    }
}