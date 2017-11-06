package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwj on 16/11/14.
 */
@Entity
@Table(name = "xg_module")
public class XgModule extends BaseModel {
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
