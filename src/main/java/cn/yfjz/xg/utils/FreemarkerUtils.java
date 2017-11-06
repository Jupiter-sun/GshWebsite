package cn.yfjz.xg.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by zhengs on 2016-11-30.
 */
public class FreemarkerUtils {

    /**
     * 替换变量
     * @param templateStr
     * @param params
     * @return
     */
    public static String process(String templateStr, Map params){
//        params.put("CURRENT_USER", CodeConstant.currentUser());
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String template = "content";
        stringLoader.putTemplate(template, templateStr);
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(stringLoader);
        try {
            Template templateCon = cfg.getTemplate(template);
            StringWriter writer = new StringWriter();
            templateCon.process(params, writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 模版路径/WEB-INF/classes/ftl
     * @param filename
     * @param paras
     * @return
     */
    public static String text(String filename, Map<String, Object> paras) {
        try {
            Configuration cf = new Configuration();
            cf.setDefaultEncoding("utf-8");
            cf.setDirectoryForTemplateLoading(new File(FreemarkerUtils.class.getClassLoader().getResource("ftl").getFile().replaceAll("%20", " ")));
            Template temp = cf.getTemplate(filename);
            StringWriter out = new StringWriter();
            temp.process(paras, out);
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }
}
