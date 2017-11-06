package cn.yfjz.core.shiro;

import cn.yfjz.core.sys.domain.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 * Created by liwj on 2016/4/28.
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public Long id;
    public String loginName;
    public String name;
    private List<Role> roles;
    private Role currentRole;
    private String usertype;

    private Long deptId;


    public ShiroUser(Long id, String loginName, String name, String usertype) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.usertype=usertype;
    }

    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }
    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "loginName");
    }

    /**
     * 重载equals,只比较loginName
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "loginName");
    }

    public String getLoginName() {
        return loginName;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public Long getDeptId() {
        return deptId;
    }

}
