package cn.yfjz.core.listener;

import cn.yfjz.core.global.AppContextHolder;
import cn.yfjz.core.sys.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by liwj on 15/7/13.
 */
public class SpringInit implements ServletContextListener {
    private static WebApplicationContext springContext;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ServletContext context = servletContextEvent.getServletContext();
            AppContextHolder.setContext(WebApplicationContextUtils.getWebApplicationContext(context));
            springContext = WebApplicationContextUtils.getWebApplicationContext(context);
            ConfigService configService = (ConfigService) springContext.getBean("configService");
            context.setAttribute("SYSTEM_NAME", configService.findValueByKey("SYSTEM_NAME", ""));
            context.setAttribute("COPYRIGHT", configService.findValueByKey("COPYRIGHT", "2016 Sibe. All rights reserved."));
            String servletContext=context.getContextPath();
            String vfsImgPath="";
            String vfsFilePath="";
            if(!StringUtils.isEmpty(servletContext)){
                vfsImgPath+=servletContext;
                vfsFilePath+=servletContext;
            }
            vfsImgPath+="/resource/showImg?path=/";
            vfsFilePath+="/resource/downloadFile?path=/";
            context.setAttribute("VFS_IMG_PATH",vfsImgPath);
            context.setAttribute("VFS_FILE_PATH",vfsFilePath);

        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
