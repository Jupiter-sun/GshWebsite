package cn.yfjz.xg.domain;

/**
 * Created by liwj on 16/11/28.
 */
public class CheckDate {
    private boolean isOk=false;
    private String message;

    public boolean isOk() {
        return isOk;
    }

    public boolean getOk(){
        return isOk;
    }
    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
