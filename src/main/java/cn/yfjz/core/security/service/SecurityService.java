package cn.yfjz.core.security.service;

import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.sys.domain.User;

/**
 * Created by liwj on 15/7/11.
 */
public interface SecurityService {
    void login(String username, String password);
    void loginCAS(String username);
    Role setCurrentRole(String current_roleid);
    User loginForClient(String username, String password);
}
