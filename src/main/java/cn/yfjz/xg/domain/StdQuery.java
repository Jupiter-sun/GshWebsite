package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.util.Expression;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liwj on 16/9/27.
 */
@Entity
@Table(name = "std_query")
public class StdQuery extends BaseModel {
    @Column(length = 300)
    private String name ;

    @Column(length = 32)
    private String categoryName ;

    @ManyToOne
    private User createUser;

    @Column(length = 1)
    private String isPublic ;

    @Column(length = 300)
    private String orderBy ;

    @Column(length = 5)
    private Long orderNo ;

    @Column(length = 300)
    private String remark ;

    @Column(length = 200)
    private String expression ;

    @Column(length = 1)
    private String isSystem ;

    @Column(length = 1000)
    private String whereSql ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getWhereSql() {
        return whereSql;
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }
    
    public List<Expression> getCondition(StdQuery query){
    	List<Expression> params = new ArrayList<Expression>();
		if(!StringUtils.isEmpty(this.getName())){
			params.add(new Expression(" name "," like ","%"+this.getName()+"%"));
        }
		if(!StringUtils.isEmpty(this.getCategoryName())){
			params.add(new Expression(" category_name "," = ",this.getCategoryName()));
        }
		return params;
    }
}
