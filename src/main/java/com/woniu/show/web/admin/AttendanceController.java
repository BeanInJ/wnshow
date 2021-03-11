package com.woniu.show.web.admin;

import com.woniu.show.pojo.Attendance;
import com.woniu.show.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * 后台管理 考勤信息
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    // 获取今天所有
    @GetMapping("/")
    public Iterable<Attendance> getAttendances() {
        return attendanceService.findToday(new Date(System.currentTimeMillis()));
    }

    // 手动添加一个
    @PostMapping("/")
    public String saveAttend(@RequestParam String student_name,
                             @RequestParam String teacher_name,
                             @RequestParam String major,
                             @RequestParam String class_no,
//                             @RequestParam String attendance_time,
                             @RequestParam String result) {

        // 不从前端传时间过来，直接从系统获取当前时间
        Attendance attendance = new Attendance();
        attendance.setId(0);
        attendance.setClass_no(class_no);
        attendance.setStudent_name(student_name);
        attendance.setTeacher_name(teacher_name);
        attendance.setAttendance_time(new Date(System.currentTimeMillis()));
        attendance.setMajor(major);
        attendance.setResult(result);
        attendanceService.save(attendance);
        return "新增成功";
    }

    // 删除一个
    @DeleteMapping("/{id}")
    public String delAt(@PathVariable Integer id) {
        attendanceService.deleteById(id);
        return "成功";
    }
}