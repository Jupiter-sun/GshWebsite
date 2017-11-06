package cn.yfjz.website.domain;

import cn.yfjz.core.sys.domain.File;
import cn.yfjz.core.sys.domain.IModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
@Data
@Entity
@Table(name = "p_project_server")
public class ProjectServer extends IModel {

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "objectId")
    private List<File> fiter;
}
