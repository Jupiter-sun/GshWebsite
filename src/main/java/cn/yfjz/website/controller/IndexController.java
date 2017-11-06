package cn.yfjz.website.controller;

import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.website.domain.Folder;
import cn.yfjz.website.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by administrator on 2017/11/3.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private FolderService folderService;

    /**
     * 门户首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/home")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        List<Folder> folderList = folderService.findAll();
        request.setAttribute("folder",folderList);
        return new ModelAndView("/website/home");
    }
}
