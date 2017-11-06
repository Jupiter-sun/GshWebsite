package cn.yfjz.core.sys.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by liwj on 2016/5/16.
 */
@Entity
@Table(name="p_role")
public class Role extends IModel {
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 20)
    private String code;
    @Column(length = 2)
    private String type;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "role")
    private List<RolePerm> perms;
    
    @Column(length = 20)
    private String scope;
    @Column(length = 2)
    private String isStdInfo;

    public String getIsStdInfo() {
        return isStdInfo;
    }

    public void setIsStdInfo(String isStdInfo) {
        this.isStdInfo = isStdInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RolePerm> getPerms() {
        return perms;
    }

    public void setPerms(List<RolePerm> perms) {
        this.perms = perms;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
