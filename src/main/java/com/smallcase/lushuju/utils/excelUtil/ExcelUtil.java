package com.smallcase.lushuju.utils.excelUtil;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Author: smallcase
 * @Date: 2018/12/19 21:46
 * @Package com.smallcase.lushuju.utils.excelUtil
 */
public class ExcelUtil {

    /**
     *
     * @param sheetName
     * @param values
     * @param titles
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,
                                               String[][] values,String[] titles){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//创建居中格式


        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);//创建sheet
        HSSFRow row = sheet.createRow(0);//创建第0行，标题

        //设置标题
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cellStyle);
        }
        
        //设置内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);//设置内容所在的行

            for (int j = 0; j < values[i].length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(values[i][j]);
            }
        }
        return hssfWorkbook;


    }


    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
