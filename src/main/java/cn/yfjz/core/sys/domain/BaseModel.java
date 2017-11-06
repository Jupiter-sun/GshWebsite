package cn.yfjz.core.sys.domain;

import cn.yfjz.core.util.Identities;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.WhenCreated;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liwj on 16/11/14.
 */
@MappedSuperclass
public abstract class BaseModel extends Model implements Serializable {
    @Id
    @Column(length = 32)
    String id;
    @WhenCreated
    @Column(columnDefinition = "DATE")
    Timestamp whenCreated;

    public void setExpressions(ExpressionList expressionList){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void genId(){
        this.id = Identities.uuid();
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }
}
