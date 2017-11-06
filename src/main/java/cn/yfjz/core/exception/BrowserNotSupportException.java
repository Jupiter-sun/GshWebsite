package cn.yfjz.core.exception;

/**
 * Created by liwj on 16/9/28.
 */
public class BrowserNotSupportException extends RuntimeException  {
    public BrowserNotSupportException() {
        super();
    }

    public BrowserNotSupportException(String message) {
        super(message);
    }
}
