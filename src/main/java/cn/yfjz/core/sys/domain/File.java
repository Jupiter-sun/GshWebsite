package cn.yfjz.core.sys.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liwj on 2016/5/16.
 */
@Data
@Entity
@Table(name = "p_file")
public class File extends IModel {

    @Column(length = 50)
    private String name;

    @ManyToOne
    private Long objectId;

    @Column(length = 50)
    private String objectName;

    @Column(length = 200)
    private String path;

    @Column(length = 200)
    private String note;



}
