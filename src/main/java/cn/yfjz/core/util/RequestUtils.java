package cn.yfjz.core.util;


import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	/**
	 * 获取真实IP
	 * @param request
	 * @return
     */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 判断是否手机浏览器
	 * @param request
	 * @return
     */
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

	public static String getClientExplorer(HttpServletRequest request) {

		String explorer = "";
		String info = request.getHeader("user-agent");
		if(StringUtils.isEmpty(info)) {
			return null;
		}
		UserAgent userAgent = UserAgent.parseUserAgentString(info);

		OperatingSystem operatingSystem = userAgent.getOperatingSystem();
		Browser browser = userAgent.getBrowser();
		if(operatingSystem!=null){
			explorer += operatingSystem.getName()+ " ";
		}
		if(browser!=null){
			explorer += browser.getName();
		}
		if(StringUtils.isEmpty(explorer)){
			explorer = "未知";
		}
		return explorer;
	}
}
