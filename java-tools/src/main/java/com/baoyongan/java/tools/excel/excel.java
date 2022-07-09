package com.baoyongan.java.tools.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class excel {

    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File("D:/tmp/excel/ExcelExportShaoguan_2022-05-15_1652504740866.xlsx")));

        XSSFCell cell = workbook.getSheetAt(0).getRow(3).getCell(0);
        cell.setCellValue("AAA");
        workbook.write(new FileOutputStream("D:/tmp/excel/2222.xlsx"));
    }
}
