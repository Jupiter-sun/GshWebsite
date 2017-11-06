package cn.yfjz.website.domain;

import cn.yfjz.core.sys.domain.File;
import cn.yfjz.core.sys.domain.IModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by administrator on 2017/11/6.
 */
@Data
@Entity
@Table(name = "p_employee")
public class Employee extends IModel {

    @Column(length = 20)
    private String name;

    @ManyToOne
    private File photo;

    @Column(length = 255)
    private String description;
}
