package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.*;

/**
 * Created by liwj on 16/10/14.
 */
@Entity
@Table(name = "std_change_detail")
public class StdChangeDetail extends BaseModel {
    @ManyToOne @JoinColumn(name="apply_id")
    private StdChange stdChange;
    @Column(length = 50)
    private String fieldId;
    @Column(length = 1000)
    private String oldValue;
    @Column(length = 1000)
    private String oldDesc;
    @Column(length = 1000)
    private String newValue;
    @Column(length = 1000)
    private String newDesc;

    public StdChange getStdChange() {
        return stdChange;
    }

    public void setStdChange(StdChange stdChange) {
        this.stdChange = stdChange;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getOldDesc() {
        return oldDesc;
    }

    public void setOldDesc(String oldDesc) {
        this.oldDesc = oldDesc;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getNewDesc() {
        return newDesc;
    }

    public void setNewDesc(String newDesc) {
        this.newDesc = newDesc;
    }
}
