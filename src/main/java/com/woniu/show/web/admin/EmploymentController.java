package com.woniu.show.web.admin;

import com.woniu.show.config.WoniuShowConfig;
import com.woniu.show.pojo.Employment;
import com.woniu.show.pojo.PerOut;
import com.woniu.show.service.EmploymentService;
import com.woniu.show.service.PerOutService;
import com.woniu.show.util.ExcelUtil;
import com.woniu.show.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 后台管理 就业信息
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/employment")
public class EmploymentController {
    @Autowired
    private EmploymentService employmentService;
    @Autowired
    private ExcelUtil excelUtil;
    @Autowired
    private Upload upload;
    @Autowired
    private WoniuShowConfig woniuShowConfig;

    // 获取最近一个月就业信息
    @GetMapping("/")
    public List<Employment> getEm() {
        // month为上月月份

        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month == 0) month = 12 ;
        int year =  Calendar.getInstance().get(Calendar.YEAR);
        if(month == 12) year = year - 1 ;
        return employmentService.findByMonth(month,year);
    }

    // 手动添加一个
    @PostMapping("/")
    public String saveEm(@RequestParam String name,
                         @RequestParam String company,
                         @RequestParam String education,
                         @RequestParam String position,
                         @RequestParam String salary,
                         @RequestParam String employmentTime
    ) throws ParseException {
        // 检查格式
        String pattern = "\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}";
        boolean isMatchDate1 = Pattern.matches(pattern, employmentTime);
        if (!isMatchDate1) return "时间格式有问题";

        // 转为data储存
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date data = new Date(format.parse(employmentTime).getTime());

        Employment employment = new Employment();
        employment.setName(name);
        employment.setCompany(company);
        employment.setEducation(education);
        employment.setPosition(position);
        employment.setSalary(salary);
        employment.setEmploymentTime(data);

        employmentService.save(employment);
        return "新增成功";
    }

    // 删除一个
    @DeleteMapping("/{id}")
    public String del(@PathVariable Integer id) {
       employmentService.deleteById(id);
        return "删除成功";
    }

    // 上传一个表格文件
    @PostMapping("/excel/")
    public String postExcel(MultipartFile file) throws Exception {
        // 保存上传的xlsx文件
        if(upload.upload(file,"1.xlsx","xls/")) {
            // 重新读取xlsx的数据，并保存到数据库
            excelUtil.save();
            return "数据保存成功";
        }else {
            return "文件上传失败";
        }
    }

}