package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 16/10/12.
 */
@Entity
@Table(name = "std_field_role")
public class StdFieldRole extends BaseModel {
    @ManyToOne
    private StdField field;
    @ManyToOne
    private Role role;
    //1可维护,0不能维护
    @Column(length = 1)
    private String isManage;

    public StdField getField() {
        return field;
    }

    public void setField(StdField field) {
        this.field = field;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getIsManage() {
        return isManage;
    }

    public void setIsManage(String isManage) {
        this.isManage = isManage;
    }
}
