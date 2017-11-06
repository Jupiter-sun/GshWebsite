package cn.yfjz.api;

import cn.yfjz.core.security.service.JWTAuthService;
import cn.yfjz.core.security.service.SecurityService;
import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.util.JsonUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by liwj on 16/8/5.
 */
@Controller
@RequestMapping("/api/security")
public class ApiSecurityController extends BaseController {

    @Autowired
    private SecurityService securityService;
    @RequestMapping("/login")
    @ResponseBody
    public Map login(HttpServletRequest request, HttpServletResponse response){
        String username = get(request, "username");
        String password = get(request, "password");
        String cid = get(request, "cid");
        if ((StringUtils.isEmpty(username)) || (StringUtils.isEmpty(password))) {
            return JsonUtils.error("10001", "用户名或密码为空");
        }
        User user = securityService.loginForClient(username, password);
        if(user==null){
            return JsonUtils.error("10002", "密码错误");
        }
        user.setPassword("");
        user.setSalt("");
        List<Role> roles = user.getRoles();
        JSONArray roleArray = new JSONArray();
        if(roles!=null && roles.size()>0){
            for(Role role: roles){
                role.setUsers(null);
                roleArray.add(role);
            }
        }
        user.setRoles(null);
        Map map = JsonUtils.success();
        map.put("token", JWTAuthService.issue(user.getUsername()));
        map.put("user", user);
        map.put("roles", roleArray);
        this.saveLog(user, "登录", "登录系统", "成功登录系统");
        return map;
    }
}
