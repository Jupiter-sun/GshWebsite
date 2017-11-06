package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwj on 16/11/14.
 */
@Entity
@Table(name = "std_info")
public class StdInfo extends BaseModel{
    private String xm;
    private String xh;

    private String bjid;


    public String getXm() {
        return xm;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }


    public String getBjid() {
        return bjid;
    }

    public void setBjid(String bjid) {
        this.bjid = bjid;
    }

    public void setXm(String xm) {

        this.xm = xm;
    }
}
