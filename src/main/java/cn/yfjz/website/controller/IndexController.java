package cn.yfjz.website.controller;

import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.website.domain.*;
import cn.yfjz.website.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by administrator on 2017/11/3.
 */
@Controller
public class IndexController extends BaseController {

    private final static Logger logger = Logger.getLogger(IndexController.class.getName());

    @Autowired
    private FolderService folderService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    /**
     * 门户首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/home")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        List<Folder> folderList = folderService.queryAll();
        List<Article> articleList = articleService.queryAll();
        List<Accounting> accountingList = accountService.queryAll();
        List<Employee> employeeList = employeeService.queryAll();
        List<ProjectServer> projectServerList = projectService.queryAll();
        request.setAttribute("folder",folderList);
        request.setAttribute("article",articleList);
        request.setAttribute("account",accountingList);
        request.setAttribute("employee",employeeList);
        request.setAttribute("project",projectServerList);
        logger.info("[article]="+articleList);
        return new ModelAndView("/website/home");
    }
}
