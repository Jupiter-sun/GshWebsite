package cn.yfjz.core.security.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by liwj on 15/7/10.
 */
public class Realm extends AuthorizingRealm {
	@Autowired
	private AccountManager accountManager;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = accountManager.getCurrentRoles();
		Set<String> perms = accountManager.getCurrentPerms();
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo(roles);
		sai.setStringPermissions(perms);

		return sai;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		return accountManager.getAuthenticationInfo(upToken.getUsername(), null, getName());
	}

	public void clearAuthorizationInfoCache(){
		Subject user = SecurityUtils.getSubject();
		clearCachedAuthorizationInfo(user.getPrincipals());
	}

}