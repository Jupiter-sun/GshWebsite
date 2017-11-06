package cn.yfjz.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liwj on 16/6/12.
 */
public class MobileUtils {
    public static boolean judgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"nokia", "sony", "ericsson", "mot", "samsung", "htc", "sgh", "lg", "sharp", "sie-"
                ,"philips", "panasonic", "alcatel", "lenovo", "iphone", "ipod", "blackberry", "meizu",
                "android", "netfront", "symbian", "ucweb", "windowsce", "palm", "operamini",
                "operamobi", "opera mobi", "openwave", "nexusone", "cldc", "midp", "wap", "mobile"};
        if (request.getHeader("User-Agent") != null) {
            for (String mobileAgent : mobileAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }
}
