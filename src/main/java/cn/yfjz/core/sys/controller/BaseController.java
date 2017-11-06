package cn.yfjz.core.sys.controller;

import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.domain.Log;
import cn.yfjz.core.sys.domain.Msg;
import cn.yfjz.core.sys.domain.User;
import cn.yfjz.core.sys.service.LogService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.RequestUtils;
import cn.yfjz.core.security.utils.UserAuthUtils;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liwj on 2015-7-11.
 */
public class BaseController {
    public final static String redirect = "redirect:";

    protected Msg msg = new Msg();
    @Autowired
    private LogService logService;

	protected String ERROR(ModelMap modelMap){
		modelMap.put("msg", msg);
		return "/common/error";
	}

	protected String ERROR(HttpServletRequest request){
		request.setAttribute("msg", msg);
		return "/common/error";
	}

    protected int getInt(HttpServletRequest request, String param, int defaultValue) {
		String value = request.getParameter(param);
		return NumberUtils.isNumber(value) ? Integer.parseInt(value) : defaultValue;
	}
	
	protected Integer getInteger(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		return NumberUtils.isNumber(value) ? Integer.parseInt(value) : null;
	}
	
	protected Integer getInteger(HttpServletRequest request, String param,Integer defaultValue) {
		String value = request.getParameter(param);
		return NumberUtils.isNumber(value) ? Integer.parseInt(value) : defaultValue;
	}
	
	protected String get(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		return value != null ? value.trim() : null;
	}
	
	protected String get(HttpServletRequest request, String param, String defaultValue) {
		String value = request.getParameter(param);
		return StringUtils.isNotBlank(value)? value.trim() : defaultValue;
	}
	
	protected String[] getValues(HttpServletRequest request, String param) {
		String[] values = request.getParameterValues(param);
		return values == null ? new String[0] : values;
	}

    protected void saveLog(String category, String title, String content) {
        ShiroUser user = UserAuthUtils.getCurrentUser();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Log log = new Log();
        log.setOperUser(new User(user.getId()));
        log.setLogLevel(CodeConstant.LEVEL_INFO);
        log.setCategory(category);
        log.setTitle(title);
		log.setClient(RequestUtils.getClientExplorer(request));
        log.setContent(content);
        log.setIp(RequestUtils.getIpAddr(request));
        log.setAction(request.getRequestURI());
        logService.save(log);
    }
	protected void saveLog(User user, String category, String title, String content) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Log log = new Log();
		log.setOperUser(user);
		log.setLogLevel(CodeConstant.LEVEL_INFO);
		log.setCategory(category);
		log.setTitle(title);
		log.setClient(RequestUtils.getClientExplorer(request));
		log.setContent(content);
		log.setIp(RequestUtils.getIpAddr(request));
		log.setAction(request.getRequestURI());
		logService.save(log);
	}
    protected int getPageSize(HttpServletRequest request){
        String rows = request.getParameter("limit");
        if (StringUtils.isEmpty(rows))
            rows = "10";
        return Integer.parseInt(rows);
    }
    protected int getPageNum(HttpServletRequest request){
        String page = request.getParameter("page");
        if (StringUtils.isEmpty(page))
            page = "1";
        return Integer.parseInt(page);
    }
    
    /**
	 * 将某个对象转换成json格式并发送到客户端
	 * @param response
	 * @param obj
	 * @throws Exception
	 */
	protected static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception{
		response.setContentType("application/json; charset=utf-8");

		PrintWriter writer = response.getWriter();
		writer.print(toJsonString(obj));
		writer.close();
		response.flushBuffer();
	}
	
	protected static String toJsonString(Object o) {
		return JSONSerializer.toJSON(o).toString();
	}
}
