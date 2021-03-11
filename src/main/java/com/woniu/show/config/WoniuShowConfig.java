package com.woniu.show.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 主要用来调用 application.yaml 配置的参数
@Component
public class WoniuShowConfig {
    /**
     * 讲师 照片 的蜗牛访问访问地址
     */
    @Value("${woniushow.imgUrl}")
    private String imgUrl;
    /**
     * 讲师 照片 的本地保存路径
     */
    @Value("${woniushow.upload-url}")
    private String uploadurl;
    /**
     * 官网获取考勤信息的url
     */
    @Value("${woniushow.attendanceUrl}")
    private String attendanceUrl;

    @Value("${woniushow.nullImgURL}")
    private String nullImgURL;

    @Override
    public String toString() {
        return "WoniuShowConfig{" +
                "imgUrl='" + imgUrl + '\'' +
                ", uploadurl='" + uploadurl + '\'' +
                ", attendanceUrl='" + attendanceUrl + '\'' +
                ", nullImgURL='" + nullImgURL + '\'' +
                '}';
    }

    public String getUploadurl() {
        return uploadurl;
    }

    public void setUploadurl(String uploadurl) {
        this.uploadurl = uploadurl;
    }

    public String getNullImgURL() {
        return nullImgURL;
    }

    public void setNullImgURL(String nullImgURL) {
        this.nullImgURL = nullImgURL;
    }

    public String getAttendanceUrl() {
        return attendanceUrl;
    }

    public void setAttendanceUrl(String attendanceUrl) {
        this.attendanceUrl = attendanceUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
