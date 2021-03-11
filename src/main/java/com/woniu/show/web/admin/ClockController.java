package com.woniu.show.web.admin;

import com.woniu.show.util.MyClock;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author BeanInJ
 * @Date 17:34 2021/3/5
 * 此类用于手动设置一个闹钟设置
 **/
@CrossOrigin
@RestController
@RequestMapping("/set")
public class ClockController {
    @PostMapping("/clock")
    public void setClock(int hh,int mm){
        new MyClock(hh,mm);
    }
}
