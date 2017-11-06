package cn.yfjz.core.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liwj on 15/7/21.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getServletPath();
        HttpSession session = request.getSession();
        if(path.contains("/index")){
            session.removeAttribute("_menuid_");
            session.removeAttribute("_submenuid_");
        }
        else{

            String currentMenuId = request.getParameter("_menuid_");
            if(StringUtils.isNotEmpty(currentMenuId)){
                session.setAttribute("_menuid_", currentMenuId);
            }
            String currentSubMenuId = request.getParameter("_submenuid_");
            if(StringUtils.isNotEmpty(currentSubMenuId)){
                session.setAttribute("_submenuid_", currentSubMenuId);
            }
        }
//        HttpSession session = request.getSession();
//        String currentMenuId = request.getParameter("_menuid_");
//        if(StringUtils.isNotEmpty(currentMenuId)){
//            session.setAttribute("_menuid_", currentMenuId);
//        }
//        else{
//            session.removeAttribute("_menuid_");
//        }
//        String currentSubMenuId = request.getParameter("_submenuid_");
//        if(StringUtils.isNotEmpty(currentSubMenuId)){
//            session.setAttribute("_submenuid_", currentSubMenuId);
//        }
//        else {
//            session.removeAttribute("_submenuid_");
//        }
//        request.setAttribute("isMobile", MobileUtils.judgeIsMoblie(request));
        return true;
    }
}
