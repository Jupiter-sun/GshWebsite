package cn.yfjz.core.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AppRequestWrapper extends HttpServletRequestWrapper implements HttpServletRequest {
	
	public AppRequestWrapper(HttpServletRequest arg0, String baseEnc) {
		super(arg0);
	}
	
	public String getString(String para) {
		String res = para;
		if (StringUtils.isBlank(para)) {
			return res;
		}
		if(para.indexOf("%u") != -1) {
			res = this.unescape(para);
		}
		return res;
	}

	public String getParameter(String arg0) {
		String ov = super.getParameter(arg0);
		ov = getString(ov);
		return ov;
	}

	public String[] getParameterValues(String arg0) {		
		String[] ov = super.getParameterValues(arg0);
		if (ov != null) {
			for (int i = 0; i < ov.length; i++) {
				ov[i] = getString(ov[i]);
			}
		}
		return ov;
	}
    /**
     * Java的unescape算法,用于对js中eccape了的字符串解码
     * @param src
     * @return
     */
    public String unescape(String src) {
        if(src == null) return null;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while(lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if(pos == lastPos) {
                if(src.charAt(pos + 1) == 'u') {
                    ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else {
                    ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else {
                if(pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                }
                else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }
}
