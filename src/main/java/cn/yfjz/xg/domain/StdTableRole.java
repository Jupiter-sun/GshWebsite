package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 16/8/17.
 */
@Entity
@Table(name="std_table_role")
public class StdTableRole extends BaseModel {
    @ManyToOne
    private StdTable table;
    @ManyToOne
    private Role role;
    @Column(length = 2)
    private String isManage;
    @Column(length = 2)
    private String isAudit;

    public StdTable getTable() {
        return table;
    }

    public void setTable(StdTable table) {
        this.table = table;
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

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }
}
