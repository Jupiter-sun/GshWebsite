package cn.yfjz.core.exception;

/**
 * Created by liwj on 15/7/13.
 */
public class WebException extends RuntimeException {
    public WebException() {
        super();
    }

    public WebException(String msg) {
        super(msg);
    }
}