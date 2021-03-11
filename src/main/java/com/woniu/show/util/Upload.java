package com.woniu.show.util;

import com.woniu.show.config.WoniuShowConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件上传工具类
 *
 * */
@Component
public class Upload {
    @Autowired
    WoniuShowConfig woniuShowConfig;
    // 传入文件及文件名
    public Boolean upload(MultipartFile file,String filename,String routeInner) throws IOException {

        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();

        // 保存地址
        String route =  woniuShowConfig.getUploadurl() +routeInner+ filename;
//        String routeOl =
        System.out.println("正上传文件："+ file.getOriginalFilename()) ;
        System.out.println("保存路径："+route);

        File file1 =new File(woniuShowConfig.getUploadurl() +routeInner);
        if  (!file1.exists()  && !file1.isDirectory())
        {
            System.out.println("文件目录不存在，正在创建");
            if(file1.mkdirs()) {
                System.out.println("目录创建成功");
            }else {
//                file.mkdir();
                System.out.println("目录创建失败");
                return false;
            }
        }
        try {
            //获取输出流
            OutputStream os = new FileOutputStream(route);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return false;
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("文件上传工具类的运行时间："+ String.valueOf(endTime-startTime)+"ms");

        return true;
    }
}
