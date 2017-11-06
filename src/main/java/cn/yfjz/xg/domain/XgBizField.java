package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 16/9/22.
 */
@Entity
@Table(name="xg_biz_field")
public class XgBizField extends BaseModel {
    @ManyToOne
    private XgBiz biz;
    @Column(length = 32)
    private String fieldid;
    @Column(length = 100)
    private String fielddesc;
    @Column(length = 5)
    private Long orderno;
    @Column(length = 1)
    private String isdisplay;
    @Column(length = 1)
    private String isexport;
    @Column(length = 5)
    private Long displaywidth;
    @Column(length = 100)
    private String url;
    @Column(length = 32)
    private String fundid;

    public XgBiz getBiz() {
        return biz;
    }

    public void setBiz(XgBiz biz) {
        this.biz = biz;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getFielddesc() {
        return fielddesc;
    }

    public void setFielddesc(String fielddesc) {
        this.fielddesc = fielddesc;
    }

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public String getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(String isdisplay) {
        this.isdisplay = isdisplay;
    }

    public String getIsexport() {
        return isexport;
    }

    public void setIsexport(String isexport) {
        this.isexport = isexport;
    }

    public Long getDisplaywidth() {
        return displaywidth;
    }

    public void setDisplaywidth(Long displaywidth) {
        this.displaywidth = displaywidth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }
}
