package cn.yfjz.website.domain;

import cn.yfjz.core.sys.domain.IModel;
import cn.yfjz.xg.domain.Content;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by administrator on 2017/11/5.
 */
@Data
@Entity
@Table(name = "p_folder")
public class Folder extends IModel{

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private Content content;

    @Column(length = 11)
    private Integer sort;

    @Column(length = 11)
    private Integer status;

    @Column(length = 200)
    private String jumpUrl;

}
