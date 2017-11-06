package cn.yfjz.core.util;

import cn.yfjz.xg.domain.ImportColumn;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 2015-2-4.
 */
public class ExcelWriter {
    public static void exportTemplate(HttpServletResponse response, String[] headers){
        try {
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + HttpUtils.convert("导入模版")+ ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("Sheet1", 0); // sheet名称
            // 给工作表中所有的列设置默认的列的宽度
            wsheet.getSettings().setDefaultColumnWidth(30);
            // 设置头部字体格式
            WritableFont fontHeader = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            // 应用字体
            WritableCellFormat wcfHeader = new WritableCellFormat(fontHeader);
            wcfHeader.setAlignment(jxl.format.Alignment.CENTRE);// 水平对齐
            wcfHeader.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直对齐
            wcfHeader.setWrap(false);// 不自动换行
            // 开始生成主体内容
            for(int i=0; i<headers.length; i++){
                wsheet.addCell(new Label(i, 0, headers[i], wcfHeader));
            }
            // 主体内容生成结束
            wbook.write(); // 写入文件
            wbook.close();
            os.close(); // 关闭流
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void exportTemplate(HttpServletResponse response, List<ImportColumn> columns){
        try {
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + HttpUtils.convert("导入模版")+ ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            response.setCharacterEncoding("UTF-8");//编码
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("Sheet1", 0); // sheet名称
            // 给工作表中所有的列设置默认的列的宽度
            wsheet.getSettings().setDefaultColumnWidth(30);
            // 设置头部字体格式
            WritableFont fontHeader = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            // 应用字体
            WritableCellFormat wcfHeader = new WritableCellFormat(fontHeader);
            wcfHeader.setAlignment(jxl.format.Alignment.CENTRE);// 水平对齐
            wcfHeader.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直对齐
            wcfHeader.setWrap(false);// 不自动换行
            // 开始生成主体内容
            for(int i=0; i<columns.size(); i++){
                wsheet.addCell(new Label(i, 0, columns.get(i).getShowName(), wcfHeader));
            }
            // 主体内容生成结束
            wbook.write(); // 写入文件
            wbook.close();
            os.close(); // 关闭流
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 根据模版导出excel
//     * @param request
//     * @param response
//     * @param template 模版路径，例如/WEB-INF/template/xx.xls
//     * @param filename 导出文件名，不要后缀
//     * @param params
//     */
//    public static void export(HttpServletRequest request,HttpServletResponse response, String template, String filename, Map params){
//        try {
//            response.reset();// 清空输出流
//            response.setHeader("Content-disposition", "attachment; filename=" + HttpUtils.convert(filename) + ".xls");// 设定输出文件头
//            response.setContentType("application/msexcel");// 定义输出类型
//            ExcelUtils.addValue("params", params);//设置数据
//            ExcelUtils.export(request.getSession().getServletContext(), template, response.getOutputStream());
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 导出excel
     * @param response
     * @param filename 导出文件名，不要后缀
     * @param header excel标题行，集合元素格式例如 new String[]{"xh","姓名"}
     * @param data 导出的数据
     */
    @SuppressWarnings("deprecation")
	public static void export(HttpServletResponse response, String filename, List<String[]> header, List<Map<String, Object>> data){
        try {
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + HttpUtils.convert(filename) + ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            OutputStream os = response.getOutputStream();
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("Sheet1", 0); // sheet名称
            // 给工作表中所有的列设置默认的列的宽度
            wsheet.getSettings().setDefaultColumnWidth(30);
            // 设置头部字体格式
            WritableFont fontHeader = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            // 应用字体
            WritableCellFormat wcfHeader = new WritableCellFormat(fontHeader);
            wcfHeader.setAlignment(jxl.format.Alignment.CENTRE);// 水平对齐
            wcfHeader.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直对齐
            wcfHeader.setWrap(false);// 不自动换行
            // 开始生成主体内容
            if(header!=null && header.size()>0){
                // 插入表第一行
                for (int i=0; i < header.size(); i++) {
                    Label label = new Label(i, 0, header.get(i)[1], wcfHeader);// 创建单元格
                    wsheet.addCell(label);// 添加到行中
                }
            }
            // 设置列表样式
            WritableCellFormat wcfCell = new WritableCellFormat();
            wcfCell.setAlignment(jxl.format.Alignment.CENTRE);// 水平对齐
            wcfCell.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直对齐
            wcfCell.setWrap(false);// 不自动换行
            wcfCell.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  
            // 插入表内容
            if (data!=null && data.size()>0) {
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < header.size(); j++) {
                        Map<String, Object> map = data.get(i);
                        Object value = map.get(header.get(j)[0].toUpperCase());
                        String cell = (value==null)?"":value.toString();
                        Label label = new Label(j, i + 1, cell, wcfCell);
                        wsheet.addCell(label);
                        wsheet.mergeCells(0, 1, 0, 3);
                        wsheet.mergeCells(2, 1, 2, 3);
                        if(i%3==0&&i>0){
                        	wsheet.mergeCells(0, i+1, 0, i+3);
                        	wsheet.mergeCells(2, i+1, 2, i+3);
                        }
                    }
                }
            }
            
            // 主体内容生成结束
            wbook.write();// 写入文件
            wbook.close();
            os.close(); // 关闭流
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出excel
     * @param response
     * @param filename 导出文件名，不要后缀
     * @param header excel标题行，集合元素格式例如 new String[]{"xh","姓名"}
     * @param data 导出的数据
     */
    public static void exportByObject(HttpServletResponse response, String filename, List<String[]> header, List data){
        try {
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + HttpUtils.convert(filename) + ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            OutputStream os = response.getOutputStream();
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("Sheet1", 0); // sheet名称
            // 给工作表中所有的列设置默认的列的宽度
            wsheet.getSettings().setDefaultColumnWidth(30);
            // 设置头部字体格式
            WritableFont fontHeader = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            // 应用字体
            WritableCellFormat wcfHeader = new WritableCellFormat(fontHeader);
            wcfHeader.setAlignment(jxl.format.Alignment.CENTRE);// 水平对齐
            wcfHeader.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直对齐
            wcfHeader.setWrap(false);// 不自动换行
            // 开始生成主体内容
            if(header!=null && header.size()>0){
                // 插入表第一行
                for (int i=0; i < header.size(); i++) {
                    Label label = new Label(i, 0, header.get(i)[1], wcfHeader);// 创建单元格
                    wsheet.addCell(label);// 添加到行中
                }
            }
            // 设置列表样式
            WritableCellFormat wcfCell = new WritableCellFormat();
            wcfCell.setAlignment(jxl.format.Alignment.CENTRE);// 水平对齐
            wcfCell.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 垂直对齐
            wcfCell.setWrap(false);// 不自动换行

          //获得全部数据的属性信息
    		PropertyDescriptor[] thisDescriptors = PropertyUtils
    		       .getPropertyDescriptors(data.get(0));


    		//定义导出列需要的readmethod
    		Method[] methods = new Method[header.size()];

    		for (int i = 0; i < header.size(); i++)
    		   for (int j = 0; j < thisDescriptors.length; j++) {
    		       //一定会找到一个，否则就是有问题了
    		       if ((header.get(i)[0]).equalsIgnoreCase(thisDescriptors[j].getName())) {
    		            //获得method
    		           methods[i] = thisDescriptors[j].getReadMethod();
    		           break;
    		       }
    		   }

            // 插入表内容
            if (data!=null && data.size()>0) {
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < header.size(); j++) {
                        String name = header.get(j)[0];
                        Object value = null;
     			       if(name.indexOf(".")>0){
     			    	   name = name.replace(".", "-");
     			    	  value = PropertyUtils.getMappedProperty(data.get(i), name.split("-")[0], name.split("-")[1]);
     			       }else  if (methods[j] != null)
     			    	  value = methods[j].invoke(data.get(i), new Object[0]);
                        String cell = (value==null)?"":value.toString();
                        Label label = new Label(j, i + 1, cell, wcfCell);
                        wsheet.addCell(label);
                    }
                }
            }
            // 主体内容生成结束
            wbook.write();// 写入文件
            wbook.close();
            os.close(); // 关闭流
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
