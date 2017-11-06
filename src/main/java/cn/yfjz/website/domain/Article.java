package cn.yfjz.website.domain;

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
@Table(name = "p_article")
public class Article extends IModel{

    @ManyToOne
    private Folder folder;

    @Column(length = 200)
    private String title;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Column(length = 20)
    private String status;

    @Column(length = 11)
    private Integer sort;

    @Column(length = 256)
    private String jumpUrl;

    @Column(length = 256)
    private String imageUrl;

    @Column(length = 256)
    private String icon;

}
