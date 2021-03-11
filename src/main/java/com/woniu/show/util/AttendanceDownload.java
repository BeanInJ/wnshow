package com.woniu.show.util;

import com.woniu.show.config.WoniuShowConfig;
import com.woniu.show.pojo.Attendance;
import com.woniu.show.pojo.AttendanceStatistics;
import com.woniu.show.service.AttendanceService;
import com.woniu.show.service.AttendanceStatisticsService;
import com.woniu.show.util.utilPojo.AttendanceDownloadReturn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Component
public class AttendanceDownload {
    @Autowired
    WoniuShowConfig woniuShowConfig;
    @Autowired
    AttendanceStatisticsService attendanceStatisticsService;
    @Autowired
    AttendanceService attendanceService;

    public String getData() throws MalformedURLException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(woniuShowConfig.getAttendanceUrl());
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("错误信息：" + e);
            // woniuShowConfig.getAttendanceUrl()蜗牛官网，下载讲师照片的地址
            System.out.println("连接蜗牛官网：" + woniuShowConfig.getAttendanceUrl() + "这个地址失败");
            return "失败";
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public AttendanceDownloadReturn weNeedLateStudents() throws Exception {

        AttendanceDownloadReturn result = new AttendanceDownloadReturn();
        String data = getData();
        if (data.equals("失败")) {
            result.setStatus("400");
            result.setMsg("未能从官网获取到数据");
            return result;
        }
        // 把获取到的数据转为jsonArray
        JSONArray js = JSONArray.fromObject(data);
        // 今天的日期
        Date today = new Date(System.currentTimeMillis());
        // 获取数据库里今日的考勤
//        List<Attendance> todayAttendances = attendanceService.findToday(today);
        // 循环官网的数据
        for (Object o : js) {
            JSONObject jo = (JSONObject) o;
            // 如果不正常、数据库也没有，新建
            if (!jo.get("result").equals("正常")) {
                Attendance attendanceByStudent = attendanceService.findStudent(today, jo.getString("student_name"));
                if (attendanceByStudent == null) {
                    Attendance attendanceOne = new Attendance();
                    attendanceOne.setResult(jo.getString("result"));
                    attendanceOne.setStudent_name(jo.getString("student_name"));
                    attendanceOne.setTeacher_name(jo.getString("teacher_name"));
                    attendanceOne.setMajor(jo.getString("major"));
                    attendanceOne.setAttendance_time(today);
                    attendanceOne.setClass_no(jo.getString("class_no"));
                    attendanceService.save(attendanceOne);
                }
            }

        }
        // 重新查今天的
        List<Attendance> newTodayAttendances = attendanceService.findToday(today);
        // 如果 今日考勤数据为空
        if (newTodayAttendances.isEmpty()) {
            result.setStatus("201");
            result.setMsg("官网数据，今日无人迟到，或刷新后再试");
            return result;
        }
        // 如果 新增考勤数据>0

        AttendanceStatistics assOld = attendanceStatisticsService.findToday(today);
        AttendanceStatistics ass = new AttendanceStatistics();
        // 考勤统计表更新
        if (assOld != null) {
            ass.setId(assOld.getId());
            ass.setNum(newTodayAttendances.size());
            System.out.println("更新 考勤统计表");
        } else {
            ass.setNum(newTodayAttendances.size());
            System.out.println("新建 考勤统计表");
        }
        ass.setAttendance_time(today);
        attendanceStatisticsService.save(ass);

        result.setStatus("200");
        result.setMsg("获取数据成功");
        result.setAttendances(newTodayAttendances);
        return result;
    }
}
