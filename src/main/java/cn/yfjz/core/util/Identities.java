package cn.yfjz.core.util;

import java.util.UUID;

/**
 * Created by sibetech on 2015-1-12.
 */
public class Identities {
    /**
     * 封装JDK自带的UUID, 通过Random数字生成
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static long lastID = gen();

    /**
     * 时间毫秒16进制
     * @return
     */
    public static synchronized String id(){
        while(lastID ==gen()){
        }
        lastID = gen();
        return Long.toHexString(lastID);
    }

    private static long gen() {
        return System.currentTimeMillis();
    }

}
