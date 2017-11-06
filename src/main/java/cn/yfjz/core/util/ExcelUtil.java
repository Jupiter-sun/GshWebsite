package cn.yfjz.core.util;


 import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

 import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil
 {
   public static HSSFCell getCell(HSSFSheet sheet, int row, int col)
   {
      HSSFRow sheetRow = sheet.getRow(row);
     if (null == sheetRow) {
       sheetRow = sheet.createRow(row);
     }
     HSSFCell cell = sheetRow.getCell((short)col);
     if (null == cell) {
       cell = sheetRow.createCell((short)col);
     }
     return cell;
   }
 
   public static void setText(HSSFCell cell, String text)
   {
     cell.setCellType(1);
//     cell.setEncoding(1);
     cell.setCellValue(text);
   }
   /**
    * 插入一行数据到工作表中
    * @param sheet 工作表
    * @param data 一行数据
    * @param row 行号
    */
   public static void setRow(HSSFSheet sheet, Map data, int row,
	    HSSFCellStyle cellStyle) {
	   Set<String> key = data.keySet();
		Iterator it = key.iterator();
		int col = 0;
		HSSFCell cell;
		while (it.hasNext()) {
		    cell = getCell(sheet, row, col++);
		    cell.setCellStyle(cellStyle);
		    Object item = it.next();
		    
		    String text = "";
		    Converter converter = new StringConverter();
		    text = (String) converter.convert(String.class, item);
		    setText(cell, text);
		}
   }
   /**
    * 插入一行数据到工作表中
    * @param sheet 工作表
    * @param data 一行数据
    * @param row 行号
    */
   public static void setRow(HSSFSheet sheet, List data, int row,
	    HSSFCellStyle cellStyle) {
	Iterator it = data.iterator();
	int col = 0;
	HSSFCell cell;
	while (it.hasNext()) {
	    cell = getCell(sheet, row, col++);
	    cell.setCellStyle(cellStyle);
	    Object item = it.next();
	    String text = "";
	    Converter converter = new StringConverter();
	    text = (String) converter.convert(String.class, item);
	    setText(cell, text);
	}
   }
   public static void setRow(HSSFSheet sheet, List data, int row)
   {
     Iterator it = data.iterator();
     int col = 0;
 
     while (it.hasNext()) {
       HSSFCell cell = getCell(sheet, row, col++);
       Object item = it.next();
       String text = "";
        Converter converter = new StringConverter();
       text = (String)converter.convert(String.class, item);
       setText(cell, text);
     }
   }
 
   public static void exportWorkBook(HSSFWorkbook wb, OutputStream output)
     throws Exception
   {
     try
     {
       wb.write(output);
     } catch (IOException e) {
       e.printStackTrace();
       throw new Exception("Excel文件没找到", e);
     }
   }
 
   public static void exportWorkBook2Response(HSSFWorkbook wb, HttpServletResponse response)
     throws Exception
   {
     try
     {
       ServletOutputStream out = response.getOutputStream();
       wb.write(out);
       out.flush();
     } catch (IOException e) {
       e.printStackTrace();
       throw new Exception("Excel文件没找到", e);
     }
   }
   
   public static void exportList(List list,List header,String sheetName,HttpServletResponse response,String fileName){
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet = wb.createSheet(sheetName);
		//fit sheet to one page
		HSSFPrintSetup ps = sheet.getPrintSetup();
		sheet.setAutobreaks(true);
		ps.setFitHeight((short) 1);
		ps.setFitWidth((short) 1);

		HSSFCellStyle dataCellStyle = wb.createCellStyle();
		dataCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dataCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		int dataLineStartRowIndex = 0;
		//设置表头
		ExcelUtil.setRow(sheet, header, dataLineStartRowIndex++, dataCellStyle);
		for (int i = 0; i < list.size(); i++)
		{
				List row = (List)list.get(i);
//				Object obj =list.get(i); 
				//填充row 数据
				ExcelUtil.setRow(sheet, row, dataLineStartRowIndex++, dataCellStyle);
		}
		
		try {
			OutputStream output = response.getOutputStream();
			 response.reset();
			response.setContentType("application/ms-excel; charset=\"utf-8\"");
			response.setHeader("Content-disposition", "attachment; filename="+java.net.URLEncoder.encode(fileName+".xls","utf-8"));
			ExcelUtil.exportWorkBook2Response(wb, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
   
   public static List genDtoExportData(List dtos, List fids) throws Exception {
	   if(dtos==null||dtos.size()==0)return new ArrayList();
		//获得全部数据的属性信息
		PropertyDescriptor[] thisDescriptors = PropertyUtils
		       .getPropertyDescriptors(dtos.get(0));

	 
		//定义导出列需要的readmethod
		Method[] methods = new Method[fids.size()];
		
		for (int i = 0; i < fids.size(); i++)
		   for (int j = 0; j < thisDescriptors.length; j++) {
		       //一定会找到一个，否则就是有问题了
		       if (((String)fids.get(i)).equalsIgnoreCase(thisDescriptors[j].getName())) {
		            //获得method
		           methods[i] = thisDescriptors[j].getReadMethod();
		           break;
		       }
		   }
       
			
		
	
		//创建需要导出的数据的list
		List data = new ArrayList();
		
		//循环每一行，每一个需要导出的列
		for (int i = 0; i < dtos.size(); i++) {
		   List row = new ArrayList();
		   for (int j = 0; j < fids.size(); j++) {
			       Object colValue = null;
			       //map
			   try {
		           //读取某一个行的某一个列
				   String name = (String)fids.get(j);
			       if(name.indexOf(".")>0){
			    	   name = name.replace(".", "-");
			    	   colValue = PropertyUtils.getMappedProperty(dtos.get(i), name.split("-")[0], name.split("-")[1]);
			       }else  if (methods[j] != null)
		               colValue = methods[j].invoke(dtos.get(i), new Object[0]);
		       } catch (IllegalArgumentException e) {
		          
		           colValue = null;
		       } catch (IllegalAccessException e) {
		          
		           colValue = null;
		       } catch (InvocationTargetException e) {
		           
		           colValue = null;
		       }
		
		       row.add(colValue);
		
		   }
		
		   data.add(row);
		
		}
		return data;
	}

   
   public static List genMapExportData(List maps, List fids) throws Exception
   {
     if(maps==null||maps.size()==0)return new ArrayList();
		//创建需要导出的数据的list
		List data = new ArrayList();
		
	//循环每一行，每一个需要导出的列
	for (int i = 0; i < maps.size(); i++) {
	   List row = new ArrayList();
	   Map map = (Map) maps.get(i);
	   for (int j = 0; j < fids.size(); j++) {
		       String colValue = null;
		       //map
		   try {
	           //读取某一个行的某一个列
			   String name = (String)fids.get(j);
			   Object value = map.get(name);
			   if(value instanceof BigDecimal){   
					BigDecimal d = (BigDecimal)value;
					if(d!=null){
						colValue =d.toString();;
					}
	     		}else if(value instanceof String){   
				   colValue = (String)value;
	     		}else if(value instanceof Date){   
				   colValue =TimeUtils.getStrByDefaultDayPattern(value);
	     		}else if(value instanceof Long){
	     			colValue =map.get(name).toString();
	     		}
			  
		       
	       } catch (Exception e) {
	          
	           colValue = null;
	       }
	
	       row.add(colValue);
	
	   }
	
	   data.add(row);
	
	}
	return data;
}
   
   
   
 }
