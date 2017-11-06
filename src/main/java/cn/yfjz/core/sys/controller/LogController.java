package cn.yfjz.core.sys.controller;

import cn.yfjz.core.sys.service.LogService;
import cn.yfjz.core.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liwj on 2016/5/18.
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @RequestMapping("/list")
    public String main(){
        return "/log/list";
    }
    @RequestMapping("/page")
    public String getPage(HttpServletRequest request){
        String ksrq=request.getParameter("ksrq");
        String jsrq=request.getParameter("jsrq");
        Pager page = logService.queryPage(ksrq,jsrq,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/log/page";
    }
}
