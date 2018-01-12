package com.chance.commen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author 付龙
 * @date 2018年1月9日 下午6:16:03
 *
 */
public class Util {

	@SuppressWarnings("resource")
    public static void writer(String path, String fileName,String fileType,List<CarMess> list,List<String> titleRow) throws Exception {  
        Workbook wb = null; 
        String excelPath = path+File.separator+fileName+"."+fileType;
        File file = new File(excelPath);
        Sheet sheet =null;
        //创建工作文档对象   
        if (!file.exists()) {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();
                
            } else if(fileType.equals("xlsx")) {
                
                    wb = new XSSFWorkbook();
            } else {
                throw new Exception("文件格式不正确");
            }
            //创建sheet对象   
            sheet = (Sheet) wb.createSheet("sheet1");  
            OutputStream outputStream = new FileOutputStream(excelPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
            
        } else {
            if (fileType.equals("xls")) {  
                wb = new HSSFWorkbook();  
                
            } else if(fileType.equals("xlsx")) { 
                wb = new XSSFWorkbook();  
                
            } else {  
                throw new Exception("文件格式不正确");
            }  
        }
         //创建sheet对象   
        if (sheet==null) {
            sheet = (Sheet) wb.createSheet("sheet1");  
        }
        Row row;
        Cell cell;
        //添加表头  
        /* = sheet.createRow(0);
        Cell cell = row.createCell(0);
        row.setHeight((short) 540); 
        cell.setCellValue("被保险人员清单");*/    //创建第一行    
        
        CellStyle style = wb.createCellStyle(); // 样式对象      
        // 设置单元格的背景颜色为淡蓝色  
/*        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index); 
        
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直      
        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平   
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
*/       
        /*cell.setCellStyle(style); // 样式，居中
*/        
//        Font font = wb.createFont();  
//        font.setBoldweight(Font.BOLDWEIGHT_BOLD);  
//        font.setFontName("宋体");  
//        font.setFontHeight((short) 200);  
//        style.setFont(font);  
        // 单元格合并      
        // 四个参数分别是：起始行，起始列，结束行，结束列      
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));  
//        sheet.autoSizeColumn(5200);
        
        row = sheet.createRow(0);    //创建第一行    
        for(int i = 0;i < titleRow.size();i++){  
            cell = row.createCell(i);  
            cell.setCellValue(titleRow.get(i));  
//            cell.setCellStyle(style); // 样式，居中
            sheet.setColumnWidth(i, 20 * 200); 
        }  
//        row.setHeight((short) 540); 

        //循环写入行数据   
        for (int i = 0; i < list.size(); i++) {  
            row = (Row) sheet.createRow(i+1);  
//            row.setHeight((short) 25); 
            row.createCell(0).setCellValue(list.get(i).getOriginalText());
            row.createCell(1).setCellValue(( list.get(i)).getLink());
            row.createCell(2).setCellValue(( list.get(i)).getWebsite());
            row.createCell(3).setCellValue(( list.get(i)).getThread_or_praise());
            row.createCell(4).setCellValue(( list.get(i)).getYear());
            row.createCell(5).setCellValue(( list.get(i)).getQuar());
            row.createCell(6).setCellValue(( list.get(i)).getMonth());
            row.createCell(7).setCellValue(( list.get(i)).getBrand());
            row.createCell(8).setCellValue(( list.get(i)).getCarName());
            row.createCell(9).setCellValue(( list.get(i)).getCarModelVersion());
            row.createCell(10).setCellValue(( list.get(i)).getFirstProperty());
            row.createCell(11).setCellValue(( list.get(i)).getSecondProperty());
            row.createCell(12).setCellValue(( list.get(i)).getThirdClass());
            row.createCell(13).setCellValue(( list.get(i)).getCommentKey());
            row.createCell(14).setCellValue(( list.get(i)).getRealFeel());
            row.createCell(15).setCellValue(( list.get(i)).getNation());
            row.createCell(16).setCellValue(( list.get(i)).getMarketMess());
        }  
        
        //创建文件流   
        OutputStream stream = new FileOutputStream(excelPath);  
        //写入数据   
        wb.write(stream);  
        //关闭文件流   
        stream.close();  
    }  
	
}
