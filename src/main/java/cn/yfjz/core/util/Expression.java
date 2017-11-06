package cn.yfjz.core.util;

/**
 * Created by liwj on 2016/5/23.
 */
public class Expression {
    private String key;
    private Object value;
    private String expr;

    public Expression(String key, String expr, Object value) {
        this.key = key;
        this.value = value;
        this.expr = expr;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }
}
