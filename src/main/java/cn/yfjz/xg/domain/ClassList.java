package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.xg.utils.EbeanUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016-12-5.
 */
@Entity
@Table(name="p_class")
public class ClassList extends BaseModel {
    public static final EbeanUtil<ClassList> ebeanUtil = new EbeanUtil<ClassList>();
    @Column(name="bh")
    private String bh;
    @Column(name="bjmc")
    private String bjmc;
    @Column(name="major_id")
    private String majorid;
    @Column(name="xy_id")
    private String xyid;
    @Column(name="nj")
    private String nj;
    @Column(name="bz")
    private String bz;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid;
    }

    public String getXyid() {
        return xyid;
    }

    public void setXyid(String xyid) {
        this.xyid = xyid;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
