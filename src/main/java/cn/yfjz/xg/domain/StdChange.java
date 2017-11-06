package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liwj on 16/10/14.
 */
@Entity
@Table(name = "std_change")
public class StdChange extends BaseModel {
    @Column(length = 50)
    private String tableId;
    @Column
    private Long studentId;
    @ManyToOne
    private User czy;
    @Column(length = 10)
    private String flag;
    @Column
    private Long dataId;
    @Column(length = 1)
    private String status;
    @ManyToOne
    private User shr;
    @Column(columnDefinition = "DATE")
    private Date shsj;
    @Column(length = 500)
    private String shyj;
    @Column(length = 1000)
    private String remark;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public User getCzy() {
        return czy;
    }

    public void setCzy(User czy) {
        this.czy = czy;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getShr() {
        return shr;
    }

    public void setShr(User shr) {
        this.shr = shr;
    }

    public Date getShsj() {
        return shsj;
    }

    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
