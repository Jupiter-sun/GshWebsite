package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.IModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 杨茗心 on 2016/10/28.
 */
@Entity
@Table(name = "p_demo")
public class Demo extends IModel {
    @Column(length = 10)
    private String ld;
    @Column(length = 10)
    private String lc;
    @Column(length = 10)
    private String fjh;
    @Column(length = 10)
    private String zj;
    @Column(length = 10)
    private String xm;
    @Column(length = 10)
    private String xb;
    @Column(length = 10)
    private String starttime;
    @Column(length = 10)
    private String endtime;
    @Column(length = 10)
    private String tel;
    @Column(length = 10)
    private String zjh;
    @Column(length = 10)
    private String fzjz;
    @Column(length = 10)
    private String bz;

    public Demo() {
    }

    public Demo(String ld, String lc,
                String fjh, String zj,
                String xm, String xb,
                String starttime, String endtime,
                String tel, String zjh,
                String fzjz, String bz) {
        this.ld = ld;
        this.lc = lc;
        this.fjh = fjh;
        this.zj = zj;
        this.xm = xm;
        this.xb = xb;
        this.starttime = starttime;
        this.endtime = endtime;
        this.tel = tel;
        this.zjh = zjh;
        this.fzjz = fzjz;
        this.bz = bz;
    }

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh;
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZjh() {
        return zjh;
    }

    public void setZjh(String zjh) {
        this.zjh = zjh;
    }

    public String getFzjz() {
        return fzjz;
    }

    public void setFzjz(String fzjz) {
        this.fzjz = fzjz;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
