package com.woniu.show.util;

import com.woniu.show.config.WoniuShowConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Component
public class TeacherImgDownload {
    @Autowired
    WoniuShowConfig woniuShowConfig;
    private String namePng;
    public String getUrl(String name){

        namePng = ToPinyin.toPinyinMain(name) +".png";
        try {
            String url =  woniuShowConfig.getImgUrl() + namePng;
            System.out.println(url);
            URL u = new URL(url);
            InputStream in = u.openStream();
            return url;
        } catch (Exception e1) {
            System.out.println("官网没有这个讲师的照片，请手动上传");
            return null;
        }

    }
    public Boolean downloadImg(String name) throws IOException {
        String url = getUrl(name);
        //  c://woniushow2.0/html/ 图片保存地址
        String route = woniuShowConfig.getUploadurl()+"img/";
        if (url == null) return false;

        System.out.println("正从官网下载照片");
        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        InputStream inputStream = uc.getInputStream();

        File file =new File(route);
        if  (!file.exists()  && !file.isDirectory())
        {
            System.out.println("文件目录不存在，正在创建");
            if(file.mkdirs()) {
                System.out.println("目录创建成功");
            }else {
//                file.mkdir();
                System.out.println("目录创建失败");
                return false;
            }
        }
        FileOutputStream out = new FileOutputStream(route+namePng);
        int j = 0;
        while ((j = inputStream.read()) != -1) {
            out.write(j);
        }
        inputStream.close();
        return true;
    }

}
