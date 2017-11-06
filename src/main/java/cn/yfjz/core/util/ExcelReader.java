package cn.yfjz.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwj on 16/11/21.
 */
public class ExcelReader {
    private Logger log = Logger.getLogger(ExcelReader.class);
    private Workbook wb;
    /**
     * 总行数
     */
    private int totalRows = 0;
    /**
     * 总列数
     */
    private int totalCells = 0;
    /**
     * 错误信息
     */
    private String errorInfo;

    private Sheet sheet;

    public ExcelReader(InputStream inputStream, String filePath) throws IOException {
        if (isExcel2003(filePath)) {
            wb = new HSSFWorkbook(inputStream);
        } else if (isExcel2007(filePath)) {
            wb = new XSSFWorkbook(inputStream);
        }
        /** 得到第一个shell */
        sheet = wb.getSheetAt(0);
        /** 得到Excel的行数 */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
    }

    public List<String> getTitle(){
        Row row = sheet.getRow(0);
        List<String> rowList = new ArrayList<String>();
        for (int c = 0; c < this.totalCells; c++) {
            Cell cell = row.getCell(c);
            rowList.add(getCellValue(cell));
        }
        return rowList;
    }

    public List<List<String>> getContent() throws IOException {
        List<List<String>> dataList = new ArrayList<List<String>>();
        /** 循环Excel的行 */
        for (int r = 1; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowList = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                rowList.add(getCellValue(cell));
            }
            /** 保存第r行的第c列 */
            dataList.add(rowList);
        }
        return dataList;
    }
    private String getCellValue(Cell cell){
        String cellValue = "";
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    String tmpValue = cell.getNumericCellValue()+ "";
                    if(StringUtils.isNotEmpty(tmpValue) && tmpValue.indexOf("E") != -1){
                        DecimalFormat df = new DecimalFormat("0");
                        cellValue = df.format(cell.getNumericCellValue());
                    }else{
                        cellValue = tmpValue;
                    }
                    break;

                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;

                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;

                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;

                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;

                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;

                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }
    public boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
