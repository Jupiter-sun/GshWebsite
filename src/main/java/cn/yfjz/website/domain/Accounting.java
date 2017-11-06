package cn.yfjz.website.domain;

import cn.yfjz.core.sys.domain.IModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by administrator on 2017/11/6.
 */
@Data
@Entity
@Table(name = "p_accounting")
public class Accounting extends IModel {

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String value;

    @Column(length = 25)
    private String icon;
}
