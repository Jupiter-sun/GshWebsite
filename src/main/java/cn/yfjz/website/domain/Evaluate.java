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
@Table(name = "p_evaluate")
public class Evaluate extends IModel {

    @Column(length = 20)
    private String title;

    @Column(length = 255)
    private String content;

    @Column(length = 500)
    private String descrption;

    @Column(length = 100)
    private String imageUrl;
}
