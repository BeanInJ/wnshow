package com.woniu.show.web.show;

import com.woniu.show.config.WoniuShowConfig;
import com.woniu.show.pojo.*;
import com.woniu.show.service.*;
import com.woniu.show.util.AttendanceDownload;
import com.woniu.show.util.UserRequest;
import com.woniu.show.util.utilPojo.AttendanceDownloadReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.*;


/**
 * 展示页面获取的接口
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DutyService dutyService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private AttendanceDownload attendanceDownload;
    @Autowired
    private WoniuShowConfig woniuShowConfig;
    @Autowired
    private PerOutService perOutService;
    @Autowired
    private EmploymentService employmentService;
    @Autowired
    private DanmuService danmuService;
    @Autowired
    private ClassRoom2Service classRoom2Service;
    @Autowired
    private ClassRoom2SaveService classRoom2SaveService;

    // 获取今天值班教师
    @GetMapping("/teachersImg/")
    public List<String> getTeachers() {

        Calendar calendar = Calendar.getInstance();
        List<Duty> duties = dutyService.findFront7();

        String[] teachers = duties.get(calendar.get(Calendar.DAY_OF_WEEK) - 2).getTeachers().split("，");
        List<String> imgs = new ArrayList<>();
        if (teachers.length <= 0) return new ArrayList<>();

        for (String t : teachers) {
            if (t == null) return new ArrayList<>();
            if (teacherService.findByNme(t) == null) return new ArrayList<>();
            String img = teacherService.findByNme(t).getImg();
            if (img.length() > 1) {
                imgs.add(img);
            }
        }

        return imgs;
    }

    // 获取全部值班信息
    @GetMapping("/dutys/")
    public List<Duty> getDuty() {
        return dutyService.findFront7();
    }

    // 获取 正在使用 的教室信息
    @GetMapping("/classrooms/")
    public Iterable<ClassRoom> getClassRooms() {
        return classRoomService.findByIsUse();
    }

    // 获取考勤信息
    @GetMapping("/attendances/")
    public List<Attendance> getAttendances() throws Exception {
        AttendanceDownloadReturn adr = attendanceDownload.weNeedLateStudents();
        if (adr.getStatus().equals("200")) return attendanceDownload.weNeedLateStudents().getAttendances();
        System.out.println(adr.getMsg());
        return null;
    }

    // 获取学生总人数
    @GetMapping("/numOfStudents/")
    public Integer numberOfStudents() {
        Integer num = 0;
        for (ClassRoom classRoom : classRoomService.findByIsUse()) {
            num = classRoom.getStudentNumber() + num;
        }
        return num;
    }

    // 劝退信息
    @GetMapping("/perout/")
    public Iterable<PerOut> getPerOut4() {
        return perOutService.findLast4();
    }

    // 获取最近一个月就业信息
    @GetMapping("/employment/")
    public List<Employment> getEm() {
        // month为上月月份
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if (month == 0) month = 12;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (month == 12) year = year - 1;

        // 将公司、薪资模糊后展示
        List<Employment> ems = employmentService.findByMonth(month, year);
        List<Employment> ems_return = new ArrayList<>();

        for (Employment em : ems) {
            em.setCompany(em.getCompany().substring(0, 4) + "***");

            int intSalary = Double.valueOf(em.getSalary()).intValue();
            em.setSalary(intSalary / 1000 + "k +");

            ems_return.add(em);
        }
        return ems_return;
    }

    // 获取上个月就业信息人数
    @GetMapping("/employment/num/")
    public int getEmNum() {
        // month为上月月份
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if (month == 0) month = 12;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (month == 12) year = year - 1;

        // 将公司、薪资模糊后展示
        List<Employment> ems = employmentService.findByMonth(month, year);
        return ems.size();
    }

    @PostMapping("/danmu/")
    public void danmu(HttpServletRequest request) throws IOException {
        Map<String, String> ms = UserRequest.get(request);
//        System.out.println(ms.get("user-agent"));
        System.out.println(ms.get("ip")+":"+request.getParameter("text"));
//        System.out.println(request.);
        String message = request.getParameter("text");
        message = message.replace("sb","");
        message = message.replace("操你妈","");
        message = message.replace("卧槽","");
        message = message.replace("你妈逼","");
        message = message.replace("逼","");
        message = message.replace("操你妈","");
        message = message.replace("日你妈","");
        message = message.replace("爸爸","");
        message = message.replace("恶心","");
        message = message.replace("骂人","");
        message = message.replace("wc","");
        message = message.replace("你好骚","");
        Danmu danmu = new Danmu();
        danmu.setId(0);
        danmu.setIp(ms.get("ip"));
        danmu.setUserAgent(ms.get("user-agent"));
        danmu.setText(message);
        danmu.setEnterTime(new java.util.Date());
        danmuService.saveDanmu(danmu);
    }

    // 获取最后20条弹幕
    @GetMapping("/danmu20/")
    public List<Danmu> danmu20() {
        return danmuService.find20();
    }

    // 获取所有教室信息
    @GetMapping("/allClassRoom2/")
    public Iterable<ClassRoom2> allClassRoom2(){
        List<ClassRoom2> allC2 = (List<ClassRoom2>) classRoom2Service.findAllClassRooms2();
        if (allC2.size()<9){
            for(int i =0;i < 9-allC2.size(); i++) {

                classRoom2Service.save(new ClassRoom2());
            }
        }

        return allC2;
    }

    // 保存教室信息
    @PostMapping("/saveClassRoom2/")
    public String saveClassRoom2(@RequestParam Integer id,
                                 @RequestParam boolean isEmpty,
                                 HttpServletRequest req){
        if(classRoom2Service.findById(id).isPresent()){

            // 保存教室状态
            ClassRoom2 c2 = new ClassRoom2();
            c2.setId(id);
            c2.setEmpty(isEmpty);
            System.out.println(id);
            classRoom2Service.save(c2);

            // 保存点击记录
            ClassRoom2Save c2s = new ClassRoom2Save();
            Map<String, String> ms = UserRequest.get(req);
            c2s.setId(0);
            c2s.setEmpty(isEmpty);
            c2s.setIp(ms.get("ip"));
            c2s.setUserAgent(ms.get("user-agent"));
            c2s.setRoom(id+"");
            c2s.setEnterTime(new java.util.Date());
            classRoom2SaveService.save(c2s);

            List<ClassRoom2> allC2 = (List<ClassRoom2>) classRoom2Service.findAllClassRooms2();
            int m = 0;
            for(ClassRoom2 c:allC2){
                if(c.isEmpty()) {
                    m++;
                }
            }
            System.out.println("当前无人教室数量："+m);
            if (m > 8){
                return "你是最后一个走的同学，注意查看各个教室后关灯、关电视、锁门";
            }else if (m > 7){
                return "你可能最后是一个走的同学，注意查看各个教室后关灯、关电视、锁门";
            }
            return "打卡成功";
        }
        return "信息有误";
    }
    // 将每个教室的打开状态清空
    @GetMapping("/clearC2s/")
    public String clearC2s() {
        List<ClassRoom2> allC2 = (List<ClassRoom2>) classRoom2Service.findAllClassRooms2();
        for(ClassRoom2 a : allC2) {
            a.setEmpty(false);
            classRoom2Service.save(a);
        }
        return "清空成功";
    }

    // 获取每天打卡的人的信息
    @GetMapping("/findC2s/{num}")
    public List<ClassRoom2Save> findC2s(@PathVariable Integer num) {
        return classRoom2SaveService.find(num);
    }
}