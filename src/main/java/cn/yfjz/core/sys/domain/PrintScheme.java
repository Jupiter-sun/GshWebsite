package cn.yfjz.core.sys.domain;

import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by liwj on 16/10/9.
 */
@Entity
@Table(name = "p_print_scheme")
public class PrintScheme extends BaseModel {
    @Column(length = 40)
    private String name;
    @Column(columnDefinition = "CLOB")
    private String content;
    @Column(length = 10)
    private String type;
    @WhenCreated
    @Column(columnDefinition = "DATE")
    Timestamp whenCreated;
    @WhenModified
    @Column(columnDefinition = "DATE")
    Timestamp whenModified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Timestamp getWhenModified() {
        return whenModified;
    }

    public void setWhenModified(Timestamp whenModified) {
        this.whenModified = whenModified;
    }
}
