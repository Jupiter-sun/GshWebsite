package cn.yfjz.core.sys.service;

import java.util.List;
import java.util.Map;

import cn.yfjz.core.sys.domain.Role;
import cn.yfjz.core.util.Pager;

/**
 * Created by liwj on 2016/5/19.
 */
public interface RoleService {
    Pager queryPage(int pageNum, int pageSize);
    Role findById(Long id);
    void add(Role role);
    void edit(Role role);
    void remove(String[] ids);
    List querySysByIds(String[] ids);
    List selectPermModelByRoleId(Long roleid);
    List query(Map<String, Object> paramMap);
    void grantPerms(String roleId, String[] permStrings);
    void addRoleUser(String roleid,String[] usersid);
    boolean checkRoleUser(String roleid,String[] usersid);
    void removeRoleUser(String roleid,String[] usersid);
    Role findRoleByCode(String code);
}
