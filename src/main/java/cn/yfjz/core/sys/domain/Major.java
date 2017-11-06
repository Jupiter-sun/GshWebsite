package cn.yfjz.core.sys.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import cn.yfjz.core.util.PinyinUtil;
import org.apache.commons.lang3.StringUtils;

import cn.yfjz.core.util.Expression;

/**
 * Created by liwj on 16/9/22.
 */
@Entity
@Table(name = "p_major")
public class Major extends IModel {
    @Column(length = 32)
    private String zyh;
    @Column(length = 100)
    private String zy;
    @Column(length = 10)
    private String xz;
    @Column(length = 100)
    private String xkml;
    @ManyToOne
    private Dept xy;

    @Transient
    private String pinyin;
    public String getPinyin() {
        return PinyinUtil.getPinYinHeadChar(zy);
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getXkml() {
        return xkml;
    }

    public void setXkml(String xkml) {
        this.xkml = xkml;
    }

    public Dept getXy() {
        return xy;
    }

    public void setXy(Dept xy) {
        this.xy = xy;
    }
    
    public List<Expression> getCondition(Major major){
    	List<Expression> params = new ArrayList<Expression>();
		if(!StringUtils.isEmpty(this.getZy())){
			params.add(new Expression(" zy "," like ","%"+this.getZy()+"%"));
        }
		if(!StringUtils.isEmpty(this.getZyh())){
			params.add(new Expression(" zyh "," = ",this.getZyh()));
        }
		if(this.getXy()!=null &&this.getXy().getId() != null){
			params.add(new Expression(" xy_id "," = ",this.getXy().getId()));
        }
		return params;
    }
}
