package cn.springcloud.meisw.kafka.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportExcel {
	 public static boolean exportExcel(String excelName, String title, String[] headers, List<Map> dataset, String pattern, HttpServletResponse response) throws IOException {

	        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
	        String fileName = excelName + "-" + milliSecond + ".xls";
	            ServletOutputStream outputStream = response.getOutputStream();
	        response.setContentType("application/x-xls;charset=UTF-8");
	        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

	        boolean flag = false;
	        Workbook workbook = null;
	        if (fileName.endsWith("xlsx"))
	        {
	            workbook = new XSSFWorkbook();
	        } else if (fileName.endsWith("xls"))
	        {
	            workbook = new HSSFWorkbook();
	        } else
	        {
	            try
	            {
	                throw new Exception("invalid file name, should be xls or xlsx");
	            } catch (Exception e)
	            {
	                e.printStackTrace();
	            }

	        }

	        Sheet sheet = workbook.createSheet(title);
	        CellStyle style = workbook.createCellStyle();

	        // 列名
	        Row row = sheet.createRow(0);
	        for (int i = 0; i < headers.length; i++)
	        {
	            Cell cell = row.createCell(i);
	            sheet.setColumnWidth(i, 5000);
//	            style.setAlignment(CellStyle.ALIGN_CENTER);
	            cell.setCellValue(headers[i]);
	        }

	        Iterator<Map> it = dataset.iterator();
	        int index = 0;
	        while (it.hasNext())
	        {
	            index++;
	            row = sheet.createRow(index);

	            Map map = it.next();
//	            logger.info(map.toString());
	            Set<String> mapKey = (Set<String>)map.keySet();
//	            logger.info(mapKey.toString());
	            Iterator<String> iterator = mapKey.iterator();
//	            logger.info(iterator.toString());
	            int num  = 0;
	            while(iterator.hasNext()){
	                Cell cell = row.createCell(num);
	                num++;
	                String key = iterator.next();
//	                logger.info(key);
	                Object obj = map.get(key);
	                if (obj instanceof Date)
	                {
	                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    cell.setCellValue(sdf.format(obj));
	                } else if (obj instanceof Integer)
	                {
	                    cell.setCellValue((Integer) obj);
	                } else if (obj instanceof Double)
	                {
	                    cell.setCellValue((Double) obj);
	                } else
	                {
	                    cell.setCellValue((String) obj);
	                }
	            }
	        }
	        FileOutputStream fos;
	        try
	        {
	            workbook.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	            flag = true;
	        } catch (FileNotFoundException e)
	        {
//	            logger.info("文件不存在");
	            flag = false;
	            e.printStackTrace();
	        } catch (IOException e)
	        {
//	            logger.info("文件写入错误");
	            flag = false;
	            e.printStackTrace();

	        }
	        return flag;
	    }
	 
	 public static boolean exportExcel2(String excelName, String title, String[] headers, List<Map> dataset, String pattern, HttpServletResponse response) throws IOException {

	        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
	        String fileName = excelName + "-" + milliSecond + ".xlsx";
	            ServletOutputStream outputStream = response.getOutputStream();
	        response.setContentType("application/x-xls;charset=UTF-8");
	        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

	        boolean flag = false;
	        Workbook workbook = null;
	        if (fileName.endsWith("xlsx"))
	        {
	            workbook = new XSSFWorkbook();
	        } else if (fileName.endsWith("xls"))
	        {
	            workbook = new HSSFWorkbook();
	        } else
	        {
	            try
	            {
	                throw new Exception("invalid file name, should be xls or xlsx");
	            } catch (Exception e)
	            {
	                e.printStackTrace();
	            }

	        }

	        Sheet sheet = workbook.createSheet(title);
	        CellStyle style = workbook.createCellStyle();

	        // 列名
	        Row row = sheet.createRow(0);
	        for (int i = 0; i < headers.length; i++)
	        {
	            Cell cell = row.createCell(i);
	            sheet.setColumnWidth(i, 5000);
	            cell.setCellValue(headers[i]);
	        }

	        for(Map map:dataset){
	        	Set<String> mapKey = map.keySet();
	        	for(String key:mapKey){
	        		Cell cell= row.createCell(Integer.valueOf(key));
	        		Object obj = map.get(key);
	        		if (obj instanceof Date)
	                {
	                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    cell.setCellValue(sdf.format(obj));
	                } else if (obj instanceof Integer)
	                {
	                    cell.setCellValue((Integer) obj);
	                } else if (obj instanceof Double)
	                {
	                    cell.setCellValue((Double) obj);
	                } else
	                {
	                    cell.setCellValue((String) obj);
	                }
	        	}
	        }
	        
	        
//	        Iterator<Map> it = dataset.iterator();
//	        int index = 0;
//	        while (it.hasNext())
//	        {
//	            index++;
//	            row = sheet.createRow(index);
//
//	            Map map = it.next();
////	            logger.info(map.toString());
//	            Set<String> mapKey = (Set<String>)map.keySet();
////	            logger.info(mapKey.toString());
//	            Iterator<String> iterator = mapKey.iterator();
////	            logger.info(iterator.toString());
//	            int num  = 0;
//	            while(iterator.hasNext()){
//	                Cell cell = row.createCell(num);
//	                num++;
//	                String key = iterator.next();
////	                logger.info(key);
//	                Object obj = map.get(key);
//	                if (obj instanceof Date)
//	                {
//	                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//	                    cell.setCellValue(sdf.format(obj));
//	                } else if (obj instanceof Integer)
//	                {
//	                    cell.setCellValue((Integer) obj);
//	                } else if (obj instanceof Double)
//	                {
//	                    cell.setCellValue((Double) obj);
//	                } else
//	                {
//	                    cell.setCellValue((String) obj);
//	                }
//	            }
//	        }
//	        FileOutputStream fos;
//	        try
//	        {
//	            workbook.write(outputStream);
//	            outputStream.flush();
//	            outputStream.close();
//	            flag = true;
//	        } catch (FileNotFoundException e)
//	        {
////	            logger.info("文件不存在");
//	            flag = false;
//	            e.printStackTrace();
//	        } catch (IOException e)
//	        {
////	            logger.info("文件写入错误");
//	            flag = false;
//	            e.printStackTrace();
//
//	        }
//	        return flag;
//	    }
	        return true;
	 }
}
