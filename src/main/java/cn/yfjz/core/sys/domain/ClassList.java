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
@Table(name="p_class")
public class ClassList extends IModel {
    @Column(length = 32)
    private String bh ;

    @Column(length = 100)
    private String bjmc ;

    @ManyToOne
    private Major major;

    @ManyToOne
    private Dept xy;

    @Column(length = 20)
    private String nj ;

    @Column(length = 300)
    private String bz;
    @Transient
    private String pinyin;
    public String getPinyin() {
        return PinyinUtil.getPYHeader(bjmc);
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Dept getXy() {
        return xy;
    }

    public void setXy(Dept xy) {
        this.xy = xy;
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
    
    public List<Expression> getCondition(ClassList classList){
    	List<Expression> params = new ArrayList<Expression>();
		if(!StringUtils.isEmpty(this.getBjmc())){
			params.add(new Expression(" bjmc "," like ","%"+this.getBjmc()+"%"));
        }
		if(!StringUtils.isEmpty(this.getBh())){
			params.add(new Expression(" bh "," = ",this.getBh()));
        }
		if(!StringUtils.isEmpty(this.getNj())){
			params.add(new Expression(" nj "," = ",this.getNj()));
        }
		if(this.getXy()!=null &&this.getXy().getId() != null){
			params.add(new Expression(" xy_id "," = ",this.getXy().getId()));
        }
		if(this.getMajor()!=null &&this.getMajor().getId() != null){
			params.add(new Expression(" major_id "," = ",this.getMajor().getId()));
        }
		return params;
    }
}
