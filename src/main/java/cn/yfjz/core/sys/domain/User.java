package cn.yfjz.core.sys.domain;

import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Expression;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwj on 2016/5/15.
 */
@Entity
@Table(name="p_user")
public class User extends IModel {
    @Column(length = 50)
    private String userid;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 100)
    private String password;
    @Column(length = 200)
    private String truename;
    @Column(length = 50)
    private String salt;
    @Column(length = 2)
    private String type;
    @Column(length = 20)
    private String sex;
    @Column(length = 10)
    private String status;
    @Column(length = 50)
    private String email;
    @Column(length = 50)
    private String mobile;
    @Column(length = 200)
    private String address;

    @ManyToOne
    private File photo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "p_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


    @ManyToOne
    private Dept dept;

    public User(){

    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public User(Long id){
        this.id=id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
    
    public List<Expression> getCondition(User user){
    	List<Expression> params = new ArrayList<Expression>();
		if(!StringUtils.isEmpty(this.getUsername())){
			params.add(new Expression(" username "," like ","%"+this.getUsername()+"%"));
        }
		if(!StringUtils.isEmpty(this.getTruename())){
			params.add(new Expression(" truename "," like ","%"+this.getTruename()+"%"));
        }
		if(!StringUtils.isEmpty(this.getType())){
			params.add(new Expression(" type "," = ",this.getType()));
        }
		if(!StringUtils.isEmpty(this.getSex())){
			params.add(new Expression(" sex "," = ",this.getSex()));
        }
		if(this.getDept()!=null &&this.getDept().getId() != null && this.getDept().getId() != Long.parseLong(CodeConstant.DEPT_ROOT_DEFAULT_ID)){
			params.add(new Expression(" dept_id "," = ",this.getDept().getId()));
        }
		return params;
    }
}
