package cn.yfjz.core.security.utils;

import cn.yfjz.core.security.service.Realm;
import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.util.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserAuthUtils {

	public static final String SYSTEM_ID = "SYSTEM_ID";
	public static final String SS_CURRENT_USER = "CURRENT_USER";
	public static final String CURRENT_ROLE = "CURRENT_ROLE";


	/**
	 * 获取当前登录用户
	 * @return ShiroUser
     */
	public static ShiroUser getCurrentUser(){
		Subject currUser= SecurityUtils.getSubject();
		ShiroUser user=(ShiroUser)currUser.getPrincipal();
		return user;
	}

	/**
	 * 获取当前用户使用角色
	 * @return
     */
	public static Role getCurrentRole(){
		Subject currUser= SecurityUtils.getSubject();
		Role role = (Role) getShiroAttribute(CURRENT_ROLE);
		return role;
	}

	public static String currentSystem() {
		return (String)getShiroAttribute(SYSTEM_ID);
	}
	
	public static void setShiroAttribute(String key, Object value){
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	}
	
	public static Object getShiroAttribute(String key){
		return SecurityUtils.getSubject().getSession().getAttribute(key);
	}

	public static Realm getRealm() {
		return (Realm) SpringContextUtil.getBean("realm");
	}
	public static void clearAuthorizationInfoCache() {
		getRealm().clearAuthorizationInfoCache();
	}
}
