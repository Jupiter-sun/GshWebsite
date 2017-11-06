package cn.yfjz.core.sys.domain;

/**
 * Created by sibetech on 2015-2-4.
 */
public class SqlParam {
    /**
     * 参数 in，out
     */
    private String typeName;
    /**
     * 字段类型：Types.VARCHAR
     * Types.NUMERIC 等
     */
    private int sqlType;
    /**
     * 传入的值
     */
    private Object value;

    public SqlParam(int sqlType, Object value) {
        this.typeName = "in";
        this.sqlType = sqlType;
        this.value = value;
    }
    public SqlParam() {
        this.typeName = "out";
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
