package cn.yfjz.xg.domain;

import cn.yfjz.core.util.PinyinUtil;
import com.avaje.ebean.annotation.Sql;

import javax.persistence.Entity;

/**
 * Created by liwj on 16/9/27.
 */
@Entity
@Sql
public class XgCode {
    private String id;
    private String value;
    private String pinyin;
    public String getPinyin() {
        return PinyinUtil.getPinYinHeadChar(value);
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
