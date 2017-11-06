package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.util.Expression;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liwj on 16/9/27.
 */
@Entity
@Table(name="std_query_condition")
public class StdQueryCondition extends BaseModel {
    @Column(length = 200)
    private String name ;

    @Column(length = 3000)
    private String sqlWhere ;

    @Column(length = 100)
    private String type ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlWhere() {
        return sqlWhere;
    }

    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public List<Expression> getCondition(StdQueryCondition condition){
    	List<Expression> params = new ArrayList<Expression>();
		if(!StringUtils.isEmpty(this.getName())){
			params.add(new Expression(" name "," like ","%"+this.getName()+"%"));
        }
		if(!StringUtils.isEmpty(this.getType())){
			params.add(new Expression(" type "," = ",this.getType()));
        }
		return params;
    }
}
