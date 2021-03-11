package com.woniu.show.service;

import com.woniu.show.dao.ClassRoomCrud;
import com.woniu.show.pojo.ClassRoom;
import com.woniu.show.pojo.ClassRoom2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService{

    @Autowired
    ClassRoomCrud classRoomCrud;


    @Override
    public Iterable<ClassRoom> findAllClassRooms() {
        return classRoomCrud.findAll();
    }

    @Override
    public List<ClassRoom> findByIsUse() {
        return classRoomCrud.findByIsUse();
    }

    @Override
    public void saveClassRoom(ClassRoom classRoom) {
        classRoomCrud.save(classRoom);
    }

    @Override
    public void deleteClassRoom(Integer id) {
        classRoomCrud.deleteById(id);
    }

}
