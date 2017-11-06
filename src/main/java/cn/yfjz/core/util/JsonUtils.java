package cn.yfjz.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwj on 16/7/29.
 */
public class JsonUtils {
    public final static String ERR_CODE="errcode";
    public final static String ERR_MSG="errmsg";
    public final static String ERR_MSG_OK="ok";
    public final static String ERR_CODE_OK="0";

    public static Map success(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ERR_CODE, ERR_CODE_OK);
        map.put(ERR_MSG, ERR_MSG_OK);
        return map;
    }
    public static Map error(String errorCode, String errorMessage){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ERR_CODE, errorCode);
        map.put(ERR_MSG, errorMessage);
        return map;
    }
}
