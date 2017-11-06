package cn.yfjz.core.sys.controller;

import cn.yfjz.core.sys.domain.Dept;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.sys.service.DeptService;
import cn.yfjz.core.sys.service.RoleService;
import cn.yfjz.core.sys.service.UserService;
import cn.yfjz.core.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

/**
 * Created by liwj on 2016/5/18.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/list")
    public String list(ModelMap m){
        Dept school = userService.findDept(1);
        m.put("school",school);
        return "/user/list";
    }

    @RequestMapping("/page")
    public String getPage(User user,HttpServletRequest request){
        Pager page = userService.queryPage(user,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/user/page";
    }


    @RequestMapping("/toAdd")
    public String toAdd(ModelMap m){
        List depts = userService.getDepts();
        m.put("depts",depts);
        return "/user/add";
    }

    @RequestMapping("/add")
    public String add(User user,ModelMap m){
        try {
            byte[] salt = Digests.generateSalt(8);
            user.setSalt(Encodes.encodeHex(salt));
            user.setPassword(CryptUtil.cryptPwd(user.getUsername(), user.getSalt()));
            user.setStatus("1");
            userService.add(user);
            msg.setSuccessMsg("新增成功,初始密码为【"+user.getUsername()+"】,请及时登录并修改密码！！");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setSuccessMsg("新增失败！");
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/toEdit")
    public String toEdit(long userid,ModelMap m){
        User user = userService.findById(userid);
        m.put("user",user);
        List depts = userService.getDepts();
        m.put("depts",depts);
        return "/user/edit";
    }
    @RequestMapping("/edit")
    public String edit(User user,ModelMap m){
        try {
            userService.edit(user);
            msg.setSuccessMsg("操作成功！");
        } catch (Exception e) {
            msg.setSuccessMsg("操作失败！");
            e.printStackTrace();
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }
    @RequestMapping("/remove")
    @ResponseBody
    public String remove(long userid,HttpServletRequest request,ModelMap m){
        try{
            userService.remove(userid);
            msg.setSuccessMsg("删除成功！");
        }catch(Exception e){
            msg.setErrorMsg("-1","删除失败！");
        }
        m.put("msg", msg);
        JSONObject jsonObject  = JSONObject.fromObject(m);
        return jsonObject.toString();
    }

    @RequestMapping("/isForbidden")
    @ResponseBody
    public String isForbidden(User user,HttpServletRequest request,ModelMap m){
        try{
            userService.edit(user);
            String message = "1".equals(user.getStatus())?"启用成功！":"禁用成功！";
            msg.setSuccessMsg(message);
        }catch(Exception e){
            msg.setErrorMsg("-1","操作失败！");
        }
        m.put("msg", msg);
        JSONObject jsonObject  = JSONObject.fromObject(msg);
        return jsonObject.toString();
    }

    @RequestMapping("/getDepts")
    @ResponseBody
    public List<Map<String, Object>> getDepts(String fid,HttpServletRequest request,ModelMap m){
        return deptService.tree(fid);
    }


    @RequestMapping("/password")
    public String password(){
        return "/user/password";
    }

    /**
     * 修改密码
     * */
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest request,ModelMap m){
        String oldPass=request.getParameter("oldPass");
        if(StringUtils.isEmpty(oldPass)){
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, "原密码不能为空！");
            m.put("msg", msg);
            return "/user/password";
        }
        String pass=request.getParameter("pass");
        if(StringUtils.isEmpty(pass)){
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"新密码不能为空！");
            m.put("msg", msg);
            return "/user/password";
        }
        String confirmPass=request.getParameter("pass2");
        if(StringUtils.isEmpty(confirmPass)){
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"确认密码不能为空！");
            m.put("msg", msg);
            return "/user/password";
        }
        if(!pass.equals(confirmPass)){
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"新密码和确认密码不相同！");
            m.put("msg", msg);
            return "/user/password";
        }
        Map<String,Object> map=userService.updatePassword(oldPass, confirmPass);
        if(!(Boolean) map.get("status")){
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,(String)map.get("msg"));
            m.put("msg", msg);
            return "/user/password";
        }
        msg.setSuccessMsg("修改成功！");
        m.put("msg", msg);
        return CodeConstant.RESULT_HTML;
    }

    @RequestMapping("/resetPwd")
    @ResponseBody
    public String resetPwd(User user,HttpServletRequest request,ModelMap m){
        try{
            user = userService.findById(user.getId());
            byte[] salt = Digests.generateSalt(8);
            user.setSalt(Encodes.encodeHex(salt));
            user.setPassword(CryptUtil.cryptPwd("123456", user.getSalt()));
            userService.edit(user);
            msg.setSuccessMsg("操作成功,密码初始化为【123456】");
        }catch(Exception e){
            msg.setErrorMsg("-1","操作失败！");
        }
        m.put("msg", msg);
        JSONObject jsonObject  = JSONObject.fromObject(msg);
        return jsonObject.toString();
    }

    @RequestMapping("/toGrant")
    public String toGrant(long userid, ModelMap m){
        List roles = roleService.queryPage(0,100).getRows();
        User user = userService.findById(userid);
        m.put("roles",roles);
        m.put("user",user);
        return "/user/grant";
    }

    @RequestMapping("/grant")
    public String grant(String id,HttpServletRequest request,ModelMap m){
        try {
            String[] roleIds = request.getParameterValues("roleid");
            userService.grant(Long.parseLong(id), roleIds);
            msg.setSuccessMsg("授权成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setSuccessMsg("授权失败");
        }
        m.put("msg", msg);
        return CodeConstant.RESULT_TOPIC_HTML;
    }

    @RequestMapping("/toBatchGrant")
    public String toBatchGrant(String ids, ModelMap m){
        List roles = roleService.queryPage(0,100).getRows();
        //User user = userService.findById(userid);
        m.put("roles",roles);
        m.put("userIds",ids);
        return "/user/batchgrant";
    }

    @RequestMapping("/batchGrant")
    public String batchGrant(String ids,HttpServletRequest request,ModelMap m){
        try {
            String[] roleIds = request.getParameterValues("roleid");
            String[] userIds = ids.split(",");
            for(String userId : userIds){
            	userService.grant(Long.parseLong(userId), roleIds);
            }
            msg.setSuccessMsg("授权成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setSuccessMsg("授权失败");
        }
        m.put("msg", msg);
        return CodeConstant.RESULT_TOPIC_HTML;
    }


}
