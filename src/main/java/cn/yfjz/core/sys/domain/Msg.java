package cn.yfjz.core.sys.domain;

/**
 * Created by chenlh on 2015-1-15.
 */
public class Msg {

    private String errcode;
    private String errmsg;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setSuccessMsg(String text){
        this.errcode = "0";
        this.errmsg = text;
    }
    public void setErrorMsg(String errcode, String text){
        this.errcode = errcode;
        this.errmsg = text;
    }
}
