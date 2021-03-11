package com.woniu.show;

import com.woniu.show.util.MyClock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowApplication.class, args);
        // 上下课闹钟
        new MyClock();
    }

}
