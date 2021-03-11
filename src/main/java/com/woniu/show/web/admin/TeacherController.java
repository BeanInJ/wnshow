package com.woniu.show.web.admin;

import com.woniu.show.pojo.Teacher;
import com.woniu.show.service.TeacherService;
import com.woniu.show.util.TeacherImgDownload;
import com.woniu.show.util.ToPinyin;
import com.woniu.show.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


/**
 * 后台管理 教师信息
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
//    @Autowired
//    private TeacherImgUpload teacherImgUpload;
    @Autowired
    private TeacherImgDownload teacherImgDownload;
    @Autowired
    private Upload upload;

    // 获取全部教师
    @GetMapping("/")
    public Iterable<Teacher> getTeachers() {
        return teacherService.findAllTeacher();
    }

    // 新建单个教师
    @PostMapping("/")
    public String postTeacher(@RequestParam("name") String name,
                              @RequestParam("direction") String direction,
                              MultipartFile file) throws Exception {
        // 新建讲师时，可以选择上传一张讲师的照片，也可以选择从蜗牛官网去下载一张照片（根据讲师姓名的全拼下载）
        String filename = "img/"+ ToPinyin.toPinyinMain(name) + ".png";
        try {
            // 姓名必须有
            if (name.length() <= 1) return "请输入正确的姓名";
            // 方向可以没有
            if (direction.length() <= 1) direction = "其他";
            try {
                if (file.getBytes().length > 10) {
//                    teacherImgUpload.upload(file,filename);
                    upload.upload(file,ToPinyin.toPinyinMain(name) + ".png","img/");
                } else {
                    if (!teacherImgDownload.downloadImg(name)) return "官网没有此讲师照片，请选择本地上传";
                }
            } catch (Exception e) {
                System.out.println("保存照片过程中出现的异常：" + e);
                if (!teacherImgDownload.downloadImg(name)) return "官网没有此讲师照片，请选择本地上传";

            }

            // 检查该姓名是否重复
            if (teacherService.findByNme(name) != null) return "已有该讲师,如需提交同名讲师，请在讲师姓名后面添加1";
            Teacher teacher = new Teacher();
            teacher.setId(0);
            teacher.setName(name);
            teacher.setDirection(direction);
            teacher.setImg(filename);
            teacherService.saveTeacher(teacher);
            return "新增成功";
        } catch (Exception e) {
//            throw e;
            System.out.println("新建讲师数据过程中报异常：" + e);
            return "新增失败";
        }
    }

    // 根据id获取单个教师
    @GetMapping("/{id}")
    public Optional<Teacher> getTeacher(@PathVariable Integer id) {
        return teacherService.getTeacher(id);
    }

    // 根据id删除单个教师
    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Integer id) {
        try {
            teacherService.deleteTeacher(id);
            return "删除成功";
        } catch (Exception e) {
            return "删除失败";
        }
    }

    // 更新教师信息
    @PutMapping("/{id}")
    public String updateTeacher(@PathVariable Integer id, String name, String direction, MultipartFile file) throws Exception {
        String filename = "img/"+ ToPinyin.toPinyinMain(name) + ".png";
        // 判断是否上传了照片，没有就从官网找，官网也没有就用以前的
        if (file.getBytes().length > 10) {
//            teacherImgUpload.upload(file,filename);
            upload.upload(file,ToPinyin.toPinyinMain(name) + ".png","img/");
        } else{
            if (!teacherImgDownload.downloadImg(name)) filename = null;
        }
        // 这里面有检查更新是否重名
        return teacherService.updateTeacher(id, name, direction,filename);
    }

}