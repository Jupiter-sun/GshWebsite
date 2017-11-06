package cn.yfjz.xg.domain;

import cn.yfjz.core.util.PinyinUtil;
import com.avaje.ebean.annotation.Sql;

import javax.persistence.Entity;

/**
 * Created by liwj on 16/9/24.
 */
@Entity
@Sql
public class Xueyuan {

    private Long xyId;
    private String xy;
    private String xyh;
    private String pinyin;
    private Long orderNumber;


    public String getPinyin() {
        return PinyinUtil.getPinYinHeadChar(xy);
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Long getXyId() {
        return xyId;
    }

    public void setXyId(Long xyId) {
        this.xyId = xyId;
    }

    public String getXy() {
        return xy;
    }

    public void setXy(String xy) {
        this.xy = xy;
    }

    public String getXyh() {
        return xyh;
    }

    public void setXyh(String xyh) {
        this.xyh = xyh;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
