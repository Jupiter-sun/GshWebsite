package cn.yfjz.xg.domain;

import cn.yfjz.core.sys.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwj on 16/8/17.
 */
@Entity
@Table(name = "std_table_type")
public class StdTableType extends BaseModel {
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String imgClass;

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgClass() {
        return imgClass;
    }

    public void setImgClass(String imgClass) {
        this.imgClass = imgClass;
    }
}
