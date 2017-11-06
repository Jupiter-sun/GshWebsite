package cn.yfjz.core.sys.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Expression;

/**
 * Created by liwj on 2016/5/16.
 */
@Entity
@Table(name="p_dept")
public class Dept extends IModel {
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100)
    private String nickName;
    @Column(length = 50, nullable = false)
    private String code;
    @Column(length = 20)
    private String type;
    @Column(length = 2, nullable = false)
    private String status;
    @Column
    private Long orderNumber;
    @Column(length = 50)
    private String email;
    @Column(length = 50)
    private String lxr;
    @Column(length = 2, nullable = false)
    private String isJxdw;
    @ManyToOne @JoinColumn(name="father_id")
    private Dept father;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Dept getFather() {
        return father;
    }

    public void setFather(Dept father) {
        this.father = father;
    }
    
    public List<Expression> getCondition(Dept dept){
    	List<Expression> params = new ArrayList<Expression>();
		if(!StringUtils.isEmpty(this.getName())){
			params.add(new Expression(" name "," like ","%"+this.getName()+"%"));
        }
		if(!StringUtils.isEmpty(this.getType())){
			params.add(new Expression(" type "," = ",this.getName()));
        }
		if(!StringUtils.isEmpty(this.getCode())){
			params.add(new Expression(" code "," = ",this.getCode()));
        }
		if(!StringUtils.isEmpty(this.getIsJxdw())){
			params.add(new Expression(" is_jxdw "," = ",this.getIsJxdw()));
        }
		if(this.getFather()!=null &&this.getFather().getId() != null){
			params.add(new Expression(" father_id "," = ",this.getFather().getId()));
        }else{
        	params.add(new Expression(" father_id "," = ",CodeConstant.DEPT_ROOT_DEFAULT_ID));
        }
		return params;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getIsJxdw() {
        return isJxdw;
    }

    public void setIsJxdw(String isJxdw) {
        this.isJxdw = isJxdw;
    }
}
