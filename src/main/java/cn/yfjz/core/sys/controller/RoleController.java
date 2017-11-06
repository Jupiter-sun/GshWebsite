package cn.yfjz.core.sys.controller;

import cn.yfjz.core.sys.domain.*;
import cn.yfjz.core.sys.service.RoleService;
import cn.yfjz.core.sys.service.UserService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.PermUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static cn.yfjz.core.util.CodeConstant.RESULT_TOPIC_HTML;

import java.util.*;

/**
 * Created by liwj on 2016/5/18.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(){
        return "/role/list";
    }

    @RequestMapping("/page")
    public String getPage(HttpServletRequest request){
        Pager page = roleService.queryPage(this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/role/page";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/role/add";
    }

    @RequestMapping("/add")
    public String add(Role role,ModelMap m){
        try {
            roleService.add(role);
            msg.setSuccessMsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }
    @RequestMapping("/toEdit")
    public String toEdit(String ids, ModelMap m,HttpServletRequest request){
        try {
            if(StringUtils.isNotEmpty(ids)){
                Long id = Long.parseLong(ids);
                Role role = roleService.findById(id);
                m.put("role", role);
                msg.setSuccessMsg("ok");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"未找到符合条件的数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return "/role/edit";
    }

    @RequestMapping("/edit")
    public String edit(Role role, ModelMap m){
        try {
            roleService.edit(role);
            msg.setSuccessMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Msg remove(String[] ids){
        try {
            if(ArrayUtils.isNotEmpty(ids)){
                List list = roleService.querySysByIds(ids);
                if(list!=null && list.size()>0){
                    String roles = "";
                    for(int i=0; i<list.size(); i++){
                        if(i>0){
                            roles+=",";
                        }
                        roles+=((Map)list.get(i)).get("name");
                    }
                    msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, roles+"是系统定制的角色，不能删除！");
                }
                else{
                    roleService.remove(ids);
                    msg.setSuccessMsg("删除成功");
                    this.saveLog(CodeConstant.LOG_REMOVE, "删除", "成功:"+ids);
                }
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }
    @RequestMapping("/toGrantPerm")
    public String toGrantPerm(String ids,ModelMap m, HttpServletRequest request){
        if(StringUtils.isNotEmpty(ids)){
            Long id = Long.parseLong(ids);
            List<PermModel> perms = PermUtil.getAllPermsForRole(request.getSession().getServletContext());
            List rolePerms = roleService.selectPermModelByRoleId(id);
            m.put("perms", perms);
            m.put("rolePerms", rolePerms);
            m.put("id", ids);
            msg.setSuccessMsg("ok");
        }
        else{
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"未找到符合条件的数据");
        }
        return "/role/grant_perm";
    }

    //给角色关联资源
    @RequestMapping("/grantPerms")
    public String grantPerms(String id, String[] permIds,ModelMap m){
        try {
            roleService.grantPerms(id,permIds);
            msg.setSuccessMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        m.put("msg", msg);
        return RESULT_TOPIC_HTML;
    }

    //分配用户
    @RequestMapping("/toGrant")
    public String toGrant(String ids, ModelMap m){
        m.put("roleId", ids);
        return "/role/grant";
    }

    //查询已分配角色roleId的用户
    @RequestMapping("/getPagerByRole")
    public String getPagerByRole(String roleId,HttpServletRequest request){
        Pager page = userService.getPagerByRole(Long.parseLong(roleId),new HashMap<String,Object>(),this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        request.setAttribute("roleId", roleId);
        return "/role/roleuserpage";
    }
    //查询符合条件的用户[JIE.LI 2016/10/9]
    @RequestMapping("/userspage")
    public String getUsersPage(User user, HttpServletRequest request){
        Pager page = userService.queryPage(user,this.getPageNum(request), this.getPageSize(request));
        request.setAttribute("page",page);
        return "/role/userspage";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(String roleId,String usersid) {
        if(usersid.isEmpty()){
            msg.setErrmsg("请选择一项！");
            return msg;
        }
        String[] userId=usersid.split(",");
        try {
            if(roleService.checkRoleUser(roleId,userId)) {
                roleService.addRoleUser(roleId, userId);
                msg.setSuccessMsg("添加用户成功！");
            }else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"该用户已经分配！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }

    @RequestMapping("/removeRoleUsers")
    @ResponseBody
    public Msg remove(String roleId,String[] ids ,ModelMap m){
        try {
            if(ArrayUtils.isNotEmpty(ids)){
                roleService.removeRoleUser(roleId,ids);
                msg.setSuccessMsg("删除成功");
            }
            else{
                msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR,"id不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setErrorMsg(CodeConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return msg;
    }












}
