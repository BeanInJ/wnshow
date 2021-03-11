package com.woniu.show.util;

import com.woniu.show.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurifyNameCheck {
    @Autowired
    private TeacherService teacherService;

    // 检查输入的值班讲师 是否在数据库中，把没在的剔除
    public String check(String names) {
        String[] purifyNames = PurifyName.replace(names);

        StringBuilder nameTrue = new StringBuilder();
        for (String name : purifyNames) {
            if (teacherService.findByNme(name) != null) nameTrue.append(name).append("，");
        }
        if(nameTrue.length() >=1) nameTrue = new StringBuilder(nameTrue.substring(0, nameTrue.length() - 1));
        return nameTrue.toString();
    }

}