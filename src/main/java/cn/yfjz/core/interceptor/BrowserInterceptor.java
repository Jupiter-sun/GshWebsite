package cn.yfjz.core.interceptor;

import cn.yfjz.core.exception.BrowserNotSupportException;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liwj on 16/9/28.
 */
public class BrowserInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if(path.indexOf("/static/")>=0){
            return true;
        }
        String info = request.getHeader("user-agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(info);
        String browser = userAgent.getBrowser().getName();
        if(browser.contains("Internet Explorer")){
            String versionStr = userAgent.getBrowserVersion().getVersion();
            Float version = Float.parseFloat(versionStr);
            if(version<10){
                throw new BrowserNotSupportException(browser);
            }
        }
        return true;
    }
}
