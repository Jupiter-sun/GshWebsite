package cn.yfjz.core.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by liwj on 15/8/5.
 */
public class ArrayUtil {
    public static boolean contains(String[] strs, String str){
        if(StringUtils.isEmpty(str)){
            return true;
        }
        if(ArrayUtils.isEmpty(strs)){
            return true;
        }
        for(String s:strs){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }
}
