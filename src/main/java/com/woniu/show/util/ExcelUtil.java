package com.woniu.show.util;

import com.woniu.show.config.WoniuShowConfig;
import com.woniu.show.pojo.Employment;
import com.woniu.show.service.EmploymentService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * 路径：com.example.demo.utils
 * 类名：
 * 功能：导入导出
 * 备注：
 * 创建人：typ
 * 创建时间：2018/10/19 11:21
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
public class ExcelUtil {
    @Autowired
    private EmploymentService employmentService;
    @Autowired
    private WoniuShowConfig woniuShowConfig;
//    /**
//     * 方法名：importExcel
//     * 功能：导入
//     */
//    public static List<Object[]> importExcel(String fileName) {
////        log.info("导入解析开始，fileName:{}", fileName);
//        try {
//            List<Object[]> list = new ArrayList<>();
//            InputStream inputStream = new FileInputStream(fileName);
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0);
//            //获取sheet的行数
//            int rows = sheet.getPhysicalNumberOfRows();
//            for (int i = 0; i < rows; i++) {
//                //过滤表头行
//                if (i == 0) {
//                    continue;
//                }
//                //获取当前行的数据
//                Row row = sheet.getRow(i);
//                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
//                int index = 0;
//                for (Cell cell : row) {
//                    if (cell.getCellType().equals(NUMERIC)) {
//                        System.out.println(cell.getDateCellValue());
////                        System.out.println(cell.getCellType());
//                        System.out.println("紧接着正确类型");
//                        if(DateUtil.isCellDateFormatted(cell)){
//                            System.out.println(new java.sql.Date(((Date)cell.getDateCellValue()).getTime())+": 时间");
////                            if ()
//                            objects[index] = cell.getDateCellValue();
//                        }else {
//                            System.out.println(cell.getNumericCellValue()+": int");
//                            objects[index] = (int) cell.getNumericCellValue();
//                        }
//                    }
//                    if (cell.getCellType().equals(STRING)) {
//                        objects[index] = cell.getStringCellValue();
//                    }
//                    if (cell.getCellType().equals(BOOLEAN)) {
//                        objects[index] = cell.getBooleanCellValue();
//                    }
//                    if (cell.getCellType().equals(ERROR)) {
//                        objects[index] = cell.getErrorCellValue();
//                    }
//
//                    index++;
//                }
//                list.add(objects);
//            }
////            log.info("导入文件解析成功！");
//            return list;
//        } catch (Exception e) {
////            log.info("导入文件解析失败！");
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void saveData(){
//        try {
//            String fileName =woniuShowConfig.getUploadurl()+ "xls/1.xlsx";
//            List<Object[]> list = importExcel(fileName);
//            // 先检查最后1个，已经有，就停止循环
//            // 入库
//            for (int i = list.size()-1; i > 0; i--) {
//
//                String woniuid = (String) list.get(i)[2];
//                Employment emm = employmentService.findByWoniuId(woniuid);
//
//                // 如果没有该同学
//                if(emm == null){
//                    System.out.println(list.get(i)[13]);
//                    try{
//                        Date dd = (Date) list.get(i)[13];
//                        java.sql.Date sqlDate =new java.sql.Date(dd.getTime());
//                        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
//                        Employment et = new Employment();
//                        et.setId(0);
//                        et.setName((String) list.get(i)[3]);
//                        et.setEmploymentTime(sqlDate);
//                        et.setEnterTime(today);
//                        et.setSalary(String.valueOf((int) list.get(i)[11]));
//                        et.setPosition((String) list.get(i)[10]);
//                        et.setCompany((String) list.get(i)[9]);
//                        et.setEducation((String) list.get(i)[5]);
//                        et.setWoniuId(woniuid);
//                        employmentService.save(et);
//                        System.out.println("就业学员正在入库"+et.toString());
//                    }catch (Exception e){
////                        System.out.println("错误提示："+e);
//                        System.out.println("一条数据出现错误,正在修改后录入");
//                        Date dd = (Date) list.get(i-1)[13];
//                        java.sql.Date sqlDate =new java.sql.Date(dd.getTime());
//                        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
//                        Employment et = new Employment();
//                        et.setId(0);
//                        et.setName((String) list.get(i)[3]);
//                        et.setEmploymentTime(sqlDate);
//                        et.setEnterTime(today);
//                        et.setSalary(String.valueOf((int) list.get(i)[11]));
//                        et.setPosition((String) list.get(i)[10]);
//                        et.setCompany((String) list.get(i)[9]);
//                        et.setEducation((String) list.get(i)[5]);
//                        et.setWoniuId(woniuid);
//                        employmentService.save(et);
//                        System.out.println("就业学员正在入库"+et.toString());
//                    }
//                }else{
//                    break;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    // 获取上个月月份
//    public String getLastMonth() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date); // 设置为当前时间
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
//        date = calendar.getTime();
//
//        return format.format(date);
//    }


    public void save() throws Exception {
        // 读取文件
        String fileName =woniuShowConfig.getUploadurl()+ "xls/1.xlsx";
        InputStream inputStream = new FileInputStream(fileName);
        Workbook workbook = WorkbookFactory.create(inputStream);

        // 读取每行
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        SimpleDateFormat  sdf =new  SimpleDateFormat("yyyy年MM月dd");
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
        // rows 所有行，row每一行，cell一个格
        for(int j= rows-1 ; j > 1;j--){
            Row row = sheet.getRow(j);
            java.sql.Date sde = null;
            // 判断时间，因为表里有String类型的时间格式
            Cell cell = row.getCell(13);
            try {
                sde = new java.sql.Date((cell.getDateCellValue()).getTime());
            }catch (Exception e){
                String stringCell = cell.toString().replace("n","");
                stringCell = stringCell.replace("日","");
                stringCell = stringCell.replace(" ","");
                sde = new java.sql.Date(sdf.parse(stringCell).getTime());
            }

            // 按照学号查找，如果存在该同学，就跳过
            Employment emm = employmentService.findByWoniuId(row.getCell(2).toString());
            if (emm != null) continue;

            Employment et = new Employment();
            et.setId(0);
            et.setName(row.getCell(3).toString());
            et.setEmploymentTime(sde);
            et.setEnterTime(today);
            et.setSalary(row.getCell(11).toString());
            et.setPosition(row.getCell(10).toString());
            et.setCompany(row.getCell(9).toString());
            et.setEducation(row.getCell(5).toString());
            et.setWoniuId(row.getCell(2).toString());
            employmentService.save(et);
            System.out.println("就业学员正在入库"+et.toString());
        }
    }



}
