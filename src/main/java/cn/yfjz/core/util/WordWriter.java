package cn.yfjz.core.util;



import org.apache.commons.lang.StringUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by 杨茗心 on 2016/12/2.
 */
public class WordWriter {
    /**
     * HTML转为word
     * @param response
     * @param htmlContent html内容
     * @param filename 导出文件名，为空是默认系统毫秒数
     */
    public static void htmlToWord(HttpServletResponse response, String htmlContent, String filename){
        ByteArrayInputStream bais = null;
        try {
            byte contents[] = htmlContent.getBytes();
            bais = new ByteArrayInputStream(contents);
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            directory.createDocument("WordDocument", bais);
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-word;charset=utf-8");
            if(StringUtils.isEmpty(filename)){
                filename = String.valueOf(System.currentTimeMillis());
            }
            response.addHeader("Content-Disposition", "attachment;filename="+ HttpUtils.convert(filename + ".doc"));	//设置应答头信息
            poifs.writeFilesystem(outputStream);
            bais.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据ftl模版导出word
     * @param request
     * @param response
     * @param data 导出数据
     * @param templateName 模版路径
     * @param filename
     */
    public static void word(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data, String templateName, String filename){
        if(StringUtils.isEmpty(filename)){
            filename = String.valueOf(System.currentTimeMillis());
        }
        try {
             DocumentHandler dh=new DocumentHandler();
            dh.setSourceFileName(templateName);
            OutputStream outputStream = response.getOutputStream();	//创建输出流对象
            response.setContentType("application/vnd.ms-word;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename="+HttpUtils.convert(filename+".doc"));	//设置应答头信息
            dh.setOutputStream(outputStream);
            dh.setData(data);
            dh.createDoc(request.getSession().getServletContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
