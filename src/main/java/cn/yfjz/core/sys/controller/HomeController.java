package cn.yfjz.core.sys.controller;

import cn.yfjz.core.security.utils.RoleUtil;
import cn.yfjz.core.sys.domain.Config;
import cn.yfjz.core.sys.domain.MenuModel;
import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.sys.service.ConfigService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.JsonUtils;
import cn.yfjz.core.util.MenuUtil;
import cn.yfjz.core.security.utils.UserAuthUtils;

import cn.yfjz.core.util.Pager;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController extends BaseController{

    @RequestMapping(value = "/")
    public String index() throws Exception {
        return redirect + "/index";
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws Exception {
        Role role = UserAuthUtils.getCurrentRole();
        if(role==null){
            return redirect+"/unauth";
        }
        if(RoleUtil.isStudent()){
            return redirect+"/desktop/index";
        }

        Subject currentUser = SecurityUtils.getSubject();
        List<MenuModel> menus = MenuUtil.getAllMenu(request.getSession().getServletContext());
        currentUser.getSession().setAttribute("currMenus",menus);
        return "/index";
    }

}
