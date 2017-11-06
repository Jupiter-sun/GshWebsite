package cn.yfjz.core.sys.domain;

import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.PinyinUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwj on 16/9/22.
 */
@Data
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
