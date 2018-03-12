package com.ophylink;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Map;

/**
 * Created by MyPC on 2018/3/12.
 */
public class poiUtils {


    public static void createXSSH() throws IOException {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet =  xssfWorkbook.createSheet("alarm");
        //标头行，代表第一行
        XSSFRow header=sheet.createRow(0);
        header.createCell(0).setCellValue("资产编号");
        header.createCell(1).setCellValue("告警等级");
        header.createCell(2).setCellValue("告警状态");
        header.createCell(3).setCellValue("系统分类");
        header.createCell(4).setCellValue("设备类型");
        header.createCell(5).setCellValue("子类型");
        header.createCell(6).setCellValue("子类型编号");
        header.createCell(7).setCellValue("指标名称");
        header.createCell(8).setCellValue("恢复时间");
        header.createCell(9).setCellValue("触发值");
        header.createCell(10).setCellValue("恢复值");
        //设置列的宽度
        //getPhysicalNumberOfCells()代表这行有多少包含数据的列
        for(int i=0;i<header.getPhysicalNumberOfCells();i++){
            //POI设置列宽度时比较特殊，它的基本单位是1/255个字符大小，
            //因此我们要想让列能够盛的下20个字符的话，就需要用255*20
            sheet.setColumnWidth(i, 255*15);
        }
        //设置行高，行高的单位就是像素，因此30就是30像素的意思
        header.setHeightInPoints(30);
        //上面设置好了内容，我们当然是要输出到某个文件的，输出就需要有输出流
        FileOutputStream fos= new FileOutputStream(".//xlsx//alarm.xlsx");
        //向指定文件写入内容
        xssfWorkbook.write(fos);
        fos.close();
    }
    //删除
    public static void delete(){
        File file = new File(".//xlsx//alarm.xlsx");
        file.delete();
    }

    //获取文件
    public static File getFile()
    {
        File file = new File("./xlsx");
        if(file.isDirectory()){
            String[] strings = file.list();
            for (String S:strings){
                System.out.println(S);
            }
        }
        return file;
    }

    //添加数据
    public static void addData(ybAlarm alarm) throws IOException {

        FileInputStream fs = new FileInputStream(".//xlsx//alarm.xlsx");
        //
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fs);
        //获取工作表
       Sheet sheet= xssfWorkbook.getSheet("alarm");
       //获取全部的行数
       int num = sheet.getLastRowNum();
        System.out.println(num);
        XSSFRow row= (XSSFRow) sheet.createRow(num+1);
        row.createCell(0).setCellValue("1");
        row.createCell(1).setCellValue("1");
        row.createCell(2).setCellValue("1");
        row.createCell(3).setCellValue("1");
        row.createCell(4).setCellValue("1");
        row.createCell(5).setCellValue("1");
        row.createCell(6).setCellValue("1");
        row.createCell(7).setCellValue("1");
        row.createCell(8).setCellValue("1");
        row.createCell(9).setCellValue("1");
        row.createCell(10).setCellValue("1");
        //上面设置好了内容，追加的方式写入
        int num1 = sheet.getLastRowNum();
        System.out.println(num1);
        FileOutputStream fos= new FileOutputStream(".//xlsx//alarm.xlsx");
        //向指定文件写入内容
        xssfWorkbook.write(fos);
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        createXSSH();
        addData(new ybAlarm());
        //getFile();

    }

}
