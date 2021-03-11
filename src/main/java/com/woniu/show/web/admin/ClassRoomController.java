package com.woniu.show.web.admin;

import com.woniu.show.pojo.ClassRoom;
import com.woniu.show.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 后台管理 教室信息
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/classroom")
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;


    // 获取 正在使用 的教室信息
    @GetMapping("/")
    public Iterable<ClassRoom> findAllClassRooms(){
        return classRoomService.findAllClassRooms();
    }
    // 新建一个 教室
    @PostMapping("/")
    public String saveClassRoom(@RequestParam("room")  String room,
                              @RequestParam("semester")  String semester,
                              @RequestParam("teacher")  String teacher,
                              @RequestParam("studentNumber")  Integer studentNumber,
                              @RequestParam("direction")  String direction,
                              @RequestParam("stage")  String stage,
                              @RequestParam("isUse")  String isUse){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setRoom(room);
        classRoom.setSemester(semester);
        classRoom.setDirection(direction);
        classRoom.setTeacher(teacher);
        classRoom.setStudentNumber(studentNumber);
        classRoom.setStage(stage);
        classRoom.setIsUse(isUse);
        classRoomService.saveClassRoom(classRoom);
        return "新增教室成功";
   }

    // 更新一个 教室
    @PostMapping("/{id}")
    public String saveClassRoom(@PathVariable  Integer id,
                              @RequestParam("room")  String room,
                              @RequestParam("semester")  String semester,
                              @RequestParam("teacher")  String teacher,
                              @RequestParam("studentNumber")  Integer studentNumber,
                              @RequestParam("direction")  String direction,
                              @RequestParam("stage")  String stage,
                              @RequestParam("isUse")  String isUse){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(id);
        classRoom.setRoom(room);
        classRoom.setSemester(semester);
        classRoom.setDirection(direction);
        classRoom.setTeacher(teacher);
        classRoom.setStudentNumber(studentNumber);
        classRoom.setStage(stage);
        classRoom.setIsUse(isUse);
        classRoomService.saveClassRoom(classRoom);
        return "更新教室成功";
    }
    // 根据id删除单个
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            classRoomService.deleteClassRoom(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败";
        }
    }
}