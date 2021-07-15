package com.emma.demo;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class PoiTest{
    //xls形式上传数据
    @Test
    public void writeExcel() throws Exception{
        //1 创建workbook
        //HSSFWorkbook 03版本excel   xls
        //XSSFWorkbook 07版本excel  xlsx
        //Workbook workbook = new XSSFWorkbook();
        Workbook workbook = new HSSFWorkbook();
        //2 创建sheet
        Sheet sheet = workbook.createSheet("用户管理");
        //3 创建row
        Row row = sheet.createRow(0);
        //4 创建cell
        Cell cell = row.createCell(0);
        //5 设置内容
        cell.setCellValue("name");

        //6 通过输出流
        OutputStream out = new FileOutputStream("C:/0607/write.xls");
        workbook.write(out);
        out.close();
    }

    //xlsx形式上传数据
    @Test
    public void writexExcel() throws Exception{
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet = xssfWorkbook.createSheet();
        //创建row
        XSSFRow row = sheet.createRow(0);
        //创建cell
        XSSFCell cell = row.createCell(0);
        //设置值
        cell.setCellValue("age");
        //通过输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:/0607/xwrite.xlsx");
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    //读取excel的内容
    @Test
    public void readExcel() throws Exception{
       InputStream inputStream = new FileInputStream("C:/0607/write.xls");
        Workbook xssfWorkbook = new HSSFWorkbook(inputStream);
        //获取sheet
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        //获取row
        Row row = sheet.getRow(0);
        //获取cell
        Cell cellOne = row.getCell(0);
        Cell cellTwo = row.getCell(1);
        String stringCellValue = cellOne.getStringCellValue();
        String stringCellValue1 = cellTwo.getStringCellValue();
        System.out.println("cellone:"+stringCellValue);
        System.out.println("celltwo:"+stringCellValue1);
    }
}