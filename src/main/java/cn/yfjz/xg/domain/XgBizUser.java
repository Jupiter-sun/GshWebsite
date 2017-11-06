package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;
import cn.yfjz.core.sys.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 16/9/22.
 */
@Entity
@Table(name = "xg_biz_user")
public class XgBizUser extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private XgBizField bizField;
    @Column(length = 5)
    private Long orderno;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public XgBizField getBizField() {
		return bizField;
	}

	public void setBizField(XgBizField bizField) {
		this.bizField = bizField;
	}

	public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }
}
