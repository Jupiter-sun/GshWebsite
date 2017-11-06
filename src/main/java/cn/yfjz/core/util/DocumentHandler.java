package cn.yfjz.core.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by liwj on 2015-2-10.
 */
public class DocumentHandler {
    private Configuration configuration = null;

    // 模板文件的包路径
    private String sourcePackgeName = "/WEB-INF/classes/ftl";

    // 模板文件名
    private String sourceFileName;

    // 目标路径
    private OutputStream outputStream;

    private static String ENCODING = "UTF-8";

    // 要填入模版的数据文件
    private Map dataMap = new HashMap();

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding(ENCODING);
    }
    public static String getTplPath(String name) {
        return "/html/"+name;
    }
    public void createDoc(ServletContext servletContext) {
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在ftl目录下面
        configuration.setServletContextForTemplateLoading(servletContext, sourcePackgeName);
        Template t = null;
        try {
            t = configuration.getTemplate(sourceFileName, ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(outputStream, ENCODING));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            t.process(dataMap, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 注意dataMap里存放的数据Key值要与模板中的参数相对应
     *
     * @param dataMap
     *
     */
    public void setData(Map dataMap) {
        this.dataMap = dataMap;
    }
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getSourcePackgeName() {
        return sourcePackgeName;
    }

    public void setSourcePackgeName(String sourcePackgeName) {
        this.sourcePackgeName = sourcePackgeName;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Map getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map dataMap) {
        this.dataMap = dataMap;
    }
}
