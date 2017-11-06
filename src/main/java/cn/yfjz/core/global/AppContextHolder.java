package cn.yfjz.core.global;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

public class AppContextHolder {

    private static ApplicationContext context = null;
    private static ServletContext servletContext=null;

    public static Object getServletCtxAttr(String key){
        return servletContext.getAttribute(key);
    }
    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static void setServletContext(ServletContext servletContext) {
        AppContextHolder.servletContext = servletContext;
    }

    public static void setContext(ApplicationContext webApplicationContext) {
        AppContextHolder.context = webApplicationContext;
    }

    public static ApplicationContext getContext() {
        return AppContextHolder.context;
    }

}
