package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liwj on 16/11/21.
 */
@Entity
@Table(name = "std_card")
public class StdCard extends BaseModel{
    @Column(length = 32)
    private String studentId;
    //办理类别
    @Column(length = 60)
    private String bllb;
    //数据来源
    @Column(length = 60)
    private String sjly;
    //办理时间
    @Column(columnDefinition = "DATE")
    private Date blsj;
    //办理原因
    @Column(length = 500)
    private String blyy;
    //办理人
    @ManyToOne
    private String blr;
    //申请时间
    @Column(columnDefinition = "DATE")
    private Date sqsj;
    //学年
    @Column(length = 100)
    private String xn;
    //学期
    @Column(length = 100)
    private String xq;
    //审批状态
    @Column(length = 10)
    private String spzt;
    //审批人
    @ManyToOne
    private User spr;
    //审批时间
    @Column(columnDefinition = "DATE")
    private Date spsj;
    //打印状态
    @Column(length = 10)
    private String dyzt;
    //打印时间
    @Column(columnDefinition = "DATE")
    private Date dysj;
    //补办金额
    @Column(length = 5)
    private Float bbje;
    //是否缴费
    @Column(length = 10)
    private String sfjf;
    //是否需要火车优惠卡
    @Column(length = 10)
    private String sfxyyhk;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBllb() {
        return bllb;
    }

    public void setBllb(String bllb) {
        this.bllb = bllb;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public Date getBlsj() {
        return blsj;
    }

    public void setBlsj(Date blsj) {
        this.blsj = blsj;
    }

    public String getBlyy() {
        return blyy;
    }

    public void setBlyy(String blyy) {
        this.blyy = blyy;
    }

    public String getBlr() {
        return blr;
    }

    public void setBlr(String blr) {
        this.blr = blr;
    }

    public Date getSqsj() {
        return sqsj;
    }

    public void setSqsj(Date sqsj) {
        this.sqsj = sqsj;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getSpzt() {
        return spzt;
    }

    public void setSpzt(String spzt) {
        this.spzt = spzt;
    }

    public User getSpr() {
        return spr;
    }

    public void setSpr(User spr) {
        this.spr = spr;
    }

    public Date getSpsj() {
        return spsj;
    }

    public void setSpsj(Date spsj) {
        this.spsj = spsj;
    }

    public String getDyzt() {
        return dyzt;
    }

    public void setDyzt(String dyzt) {
        this.dyzt = dyzt;
    }

    public Date getDysj() {
        return dysj;
    }

    public void setDysj(Date dysj) {
        this.dysj = dysj;
    }

    public Float getBbje() {
        return bbje;
    }

    public void setBbje(Float bbje) {
        this.bbje = bbje;
    }

    public String getSfjf() {
        return sfjf;
    }

    public void setSfjf(String sfjf) {
        this.sfjf = sfjf;
    }

    public String getSfxyyhk() {
        return sfxyyhk;
    }

    public void setSfxyyhk(String sfxyyhk) {
        this.sfxyyhk = sfxyyhk;
    }
}
