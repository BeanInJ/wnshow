package com.woniu.show.web.admin;

import com.woniu.show.pojo.PerOut;
import com.woniu.show.service.PerOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


/**
 * 后台管理 劝退信息
 * restful风格
 *
 * @CrossOrigin 允许跨域请求
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/perout")
public class PerOutController {
    @Autowired
    private PerOutService perOutService;

    // 获取最近4条劝退信息
    @GetMapping("/")
    public Iterable<PerOut> get4() {
        return perOutService.findLast4();
    }

    // 手动添加一个
    @PostMapping("/")
    public String savePerOut(@RequestParam String name,
                             @RequestParam String clo,
                             @RequestParam String intime,
                             @RequestParam String reason) {
        String pattern ="\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";
        boolean isMatchDate1 = Pattern.matches(pattern, intime);
        if(!isMatchDate1) return "时间格式有问题";

        PerOut perOut = new PerOut();
        perOut.setName(name);
        perOut.setClo(clo);
        perOut.setId(0);
        perOut.setIntime(intime);
        perOut.setReason(reason);
        perOutService.save(perOut);
        return "新增成功";
    }

    // 删除一个
    @DeleteMapping("/{id}")
    public String del(@PathVariable int id) {
        perOutService.deleteById(id);
        return "成功";
    }
}