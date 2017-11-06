package cn.yfjz.core.sys.domain;


import javax.persistence.*;

/**
 * Created by liwj on 2016/5/16.
 */
@Entity
@Table(name = "p_file")
public class File extends IModel {

    @Column(length = 50)
    private String name;
    @Column
    private Long objectId;
    @Column(length = 50)
    private String objectName;

    @Column(length = 200)

    private String path;


    @Column(length = 200)

    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
