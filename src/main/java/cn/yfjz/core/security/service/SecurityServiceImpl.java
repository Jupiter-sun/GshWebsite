package cn.yfjz.core.security.service;

import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.sys.domain.RolePerm;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.util.*;
import com.avaje.ebean.EbeanServer;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liwj on 15/7/11.
 */
@Service
public class SecurityServiceImpl implements AccountManager,SecurityService {
    @Autowired
    protected EbeanServer ebeanServer;
    @Override
    public void login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        User user = findByName(username);
        if(user !=null){
            password = CryptUtil.cryptPwd(password, user.getSalt());
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        currentUser.getSession().setAttribute("ROLES", user.getRoles());
    }

    @Override
    public void loginCAS(String username) {
        Subject currentUser = SecurityUtils.getSubject();
        User user = findByName(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword());
        currentUser.login(token);
        currentUser.getSession().setAttribute("ROLES", user.getRoles());
    }

    @Override
    public Role setCurrentRole(String currentRoleIdStr) {
        Long currentRoleId = null;
        if(StringUtils.isNotEmpty(currentRoleIdStr)){
            currentRoleId = Long.parseLong(currentRoleIdStr);
        }

        Role currentRole = null;
        ShiroUser currentUser = UserAuthUtils.getCurrentUser();
        List<Role> myroles = currentUser.getRoles();
        //如果传入了指定的roleid,验证是否存在
        if(currentRoleId != null){
            for(Role role : myroles){
                if(currentRoleId==role.getId()){
                    currentRole = role;
                    break;
                }
            }
        }
        //没有找到指定的roleid或者roleid为null,返回列表的第一个值
        if(currentRole == null){
            if(myroles.size()>0){
                currentRole = myroles.get(0);
            }
        }
        currentUser.setCurrentRole(currentRole);
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(UserAuthUtils.CURRENT_ROLE, currentRole);
        UserAuthUtils.clearAuthorizationInfoCache();
        return currentRole;
    }

    @Override
    public User loginForClient(String username, String password) {
        User user = findByName(username);
        if(user !=null){
            password = CryptUtil.cryptPwd(password, user.getSalt());
            if(password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByName(String username){
        List<User> list = ebeanServer.find(User.class).where().eq("username", username).findList();
    	if(list !=null&& list.size()==1){
            return list.get(0);
        }
        return null;
    }
    @Override
    public AuthenticationInfo getAuthenticationInfo(String username, String realmName) {
        if(username == null)
            return null;
        User user = findByName(username);
        if  (user != null)  {
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(), realmName);
        }
        return null;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(String username, Object credentials, String realmName) {
        if(username == null)
            return null;
        User user = findByName(username);
        if  (user != null)  {
            ShiroUser shiroUser=new ShiroUser(user.getId(), user.getUsername(), user.getTruename(),user.getType());
            List<Role> roles = user.getRoles();
            shiroUser.setRoles(roles);
            if(user.getDept()!=null)shiroUser.setDeptId(user.getDept().getId());
            SimpleAuthenticationInfo info= new SimpleAuthenticationInfo(shiroUser, user.getPassword(), realmName);
            return info;
        }
        return null;
    }

    @Override
    public Set<String> getCurrentRoles() {
        // 先获取当前角色
        ShiroUser currentUser = UserAuthUtils.getCurrentUser();
        Role currentRole = currentUser.getCurrentRole();
        Set<String> roles = new HashSet<String>();
        if(currentRole != null){
            roles.add(currentRole.getId().toString());
        }
        return roles;
    }

    @Override
    public Set<String> getCurrentPerms() {
        ShiroUser currentUser = UserAuthUtils.getCurrentUser();
        Role currentRole = currentUser.getCurrentRole();
        Set<String> currentPerms = new HashSet<String>();
        if(currentRole !=null){
        	List<RolePerm> perms = ebeanServer.find(RolePerm.class).where().eq("role.id",currentRole.getId()).findList();
        	if(perms!=null && perms.size()>0){
        		for(RolePerm p:perms){
        			currentPerms.add(p.getPermString());
        		}
        	}
        	
        }
        return currentPerms;
    }
}
