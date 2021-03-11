package com.woniu.show.web.admin;

import com.woniu.show.pojo.Duty;
import com.woniu.show.service.DutyService;
import com.woniu.show.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



/**
 * 后台管理 值班信息
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/duty")
public class DutyController {
    @Autowired
    private DutyService dutyService;
    @Autowired
    private PurifyNameCheck purifyNameCheck;

    // 获取全部值班信息
    @GetMapping("/")
    public List<Duty> getDuty() {
        List<Duty> duties =dutyService.findFront7();
        int count = 0; //定义一个统计器
        for (Duty duty : duties) {
            count = count + 1;//没循环一次，就让count加1.
        }
        if (count < 7){
            int num = 7 - count;
            for (int j=0 ; j<num ;j++){
                Duty dNew = new Duty();
                dNew.setId(60+j);
                dNew.setWeek("新增");
                dutyService.saveDuty(dNew);
            }
            duties =dutyService.findFront7();
        }
        return duties;
    }

    @PostMapping("/")
    public String saveDuty(@RequestParam("w1") String w1,
                               @RequestParam("w2") String w2,
                               @RequestParam("w3") String w3,
                               @RequestParam("w4") String w4,
                               @RequestParam("w5") String w5,
                               @RequestParam("w6") String w6,
                               @RequestParam("w7") String w7){
        // 剔除重复的，返回有的
        List<String> ss = Arrays.asList(purifyNameCheck.check(w1),
                purifyNameCheck.check(w2),
                purifyNameCheck.check(w3),
                purifyNameCheck.check(w4),
                purifyNameCheck.check(w5),
                purifyNameCheck.check(w6),
                purifyNameCheck.check(w7));
        // 在这里判断数据库里前七条数据是否存在
        List<Duty> duties =dutyService.findFront7();
        int count = 0; //定义一个统计器
        for (Duty duty : duties) {
            count = count + 1;//没循环一次，就让count加1.
        }
        if (count < 7){
            int num = 7 - count;
            for (int j=0 ; j<num ;j++){
                Duty dNew = new Duty();
                dNew.setId(60+j);
                dNew.setWeek("新增");
                dutyService.saveDuty(dNew);
            }
            duties =dutyService.findFront7();
        }
        for (int i = 0; i < 7; i++){
            Duty d1 = new Duty();
            d1.setId(duties.get(i).getId());
            d1.setTeachers(ss.get(i));
            System.out.println("插入的值班信息："+ss.get(i));
            dutyService.saveDuty(d1);
        }
        return "更新成功";

    }
}