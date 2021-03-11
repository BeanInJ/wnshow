package com.woniu.show.service;

import com.woniu.show.pojo.ClassRoom;
import com.woniu.show.pojo.ClassRoom2;

import java.util.List;

public interface ClassRoomService {
    Iterable<ClassRoom> findAllClassRooms();
    List<ClassRoom> findByIsUse();
    void saveClassRoom(ClassRoom classRoom);
    void deleteClassRoom(Integer id);
}
