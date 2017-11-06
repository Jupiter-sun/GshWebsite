package cn.yfjz.core.security.service;

import cn.yfjz.core.sys.domain.User;
import org.apache.shiro.authc.AuthenticationInfo;

import java.util.Set;

/**
 * Created by liwj on 15/7/10.
 */
public interface AccountManager {
    AuthenticationInfo getAuthenticationInfo(String username, String realmName);
    AuthenticationInfo getAuthenticationInfo(String username, Object credentials, String realmName);
    Set<String> getCurrentRoles();
    Set<String> getCurrentPerms();
    User findByName(String username);
}
