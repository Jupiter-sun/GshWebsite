package cn.yfjz.core.sys.domain;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by liwj on 2016/5/17.
 */
@Entity
@Table(name = "p_role_perm")
public class RolePerm extends Model {

    @ManyToOne
    private Role role;
    @Column
    private String permString;

    public String getPermString() {
        return permString;
    }

    public void setPermString(String permString) {
        this.permString = permString;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    
}
