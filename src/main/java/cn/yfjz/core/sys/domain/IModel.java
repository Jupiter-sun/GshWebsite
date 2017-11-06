package cn.yfjz.core.sys.domain;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liwj on 2016/5/15.
 */
@MappedSuperclass
public abstract class IModel extends Model implements Serializable {
    @Id @Column(length = 10)
    Long id;
    @Version @Column(length = 10)
    Long version;
    @WhenCreated
    @Column(columnDefinition = "DATE")
    Timestamp whenCreated;
    @WhenModified
    @Column(columnDefinition = "DATE")
    Timestamp whenModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
