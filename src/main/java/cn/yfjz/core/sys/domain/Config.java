package cn.yfjz.core.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwj on 16/8/6.
 */
@Entity
@Table(name = "p_config")
public class Config extends BaseModel {
    @Column(length = 50)
    private String key1;
    @Column(length = 300)
    private String value1;
    @Column(length = 300)
    private String remark;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
