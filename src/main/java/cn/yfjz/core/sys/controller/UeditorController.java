package cn.yfjz.core.sys.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liwj on 16/9/12.
 */
@Controller
public class UeditorController {
    @RequestMapping(value = "/static/ueditor/jsp/controller")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        response.getWriter().write( new ActionEnter( request, rootPath ).exec() );
    }
}
