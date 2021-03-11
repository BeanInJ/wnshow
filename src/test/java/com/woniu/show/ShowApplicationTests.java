package com.woniu.show;

import com.woniu.show.util.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShowApplicationTests {
    @Autowired
    private ExcelUtil excelUtil;
    @Test
    void contextLoads() throws Exception {

//        System.out.println(excelUtil.getLastMonth());
//        excelUtil.save();

    }

}
