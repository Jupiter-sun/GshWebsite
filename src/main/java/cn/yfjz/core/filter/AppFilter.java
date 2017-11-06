package cn.yfjz.core.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppFilter extends OncePerRequestFilter {
	private Log logger = LogFactory.getLog(getClass());

	private static final String[] IGNORE_URI_SUFFIX=new String[]{".jpg",".png",".js",".css",
			".gif",".JPG",".PNG",".JS",".CSS",".GIF",
			"showImg"};

	private static String APP_ENCODING = "_app_encoding_tag_";
	private static final int COOKIE_MAXAGE_ONE_YEAR = 365 * 24 * 60 * 60;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String url=request.getRequestURI();
		if(StringUtils.endsWithAny(url, IGNORE_URI_SUFFIX)) filterChain.doFilter(request, response);
		else{
			//当前服务器时间，页面可以直接获取${CURRENT_DATE}
			request.setAttribute("CURRENT_DATE", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			request.setAttribute("CURRENT_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			//处理get方式自动转码
			String tag = request.getParameter(APP_ENCODING);
			try{
				if (StringUtils.isBlank(tag)
						|| "POST".equalsIgnoreCase(request.getMethod())) {
					filterChain.doFilter(request, response);
				} else {
					HttpServletRequest req = new AppRequestWrapper(request, null);
					filterChain.doFilter(req, response);
				}
			}finally {
			}
		}

	}

	/**
	 * 根据jwt认证头进行登录
	 * @param request
	 * @param response
	 */
	/*private UserProfile processUserprofileByJwtToken(HttpServletRequest request, HttpServletResponse response){
		String authorizationHeader = request.getHeader("Authorization");
		if(StringUtils.isEmpty(authorizationHeader)) {
			return null;
		}
		String token = StringUtils.substringAfter(authorizationHeader, " ");
		if(StringUtils.isEmpty(token)) {
			logger.error("认证头【"+ authorizationHeader +"】不合法");
			return null;
		}
		String userId = JWTAuthService.verifyAndReturnUserId(token);
		if(StringUtils.isEmpty(userId)) {
			return null;
		}
		UserProfile up = null;
		try {
			up =  AppContextHolder.getContext().getBean(SecurityService.class).getUserProfile(userId, WebUtil.getClientIP(request));
		} catch (Exception e) {
			logger.error("认证用户【"+ userId +"】失败", e);
		}
		return up;
	}
	
	private RoleDTO processCurrentRole(UserProfile userProfile){
		RoleDTO currentRole = new RoleDTO();
		if(userProfile!=null && !userProfile.getRoles().isEmpty()){
			Map role = (Map) userProfile.getRoles().get(0);
			currentRole.setId((String) role.get("ROLEID"));
			currentRole.setName((String) role.get("NAME"));
			currentRole.setScope((String)role.get("SCOPE"));
		}
		return currentRole;
	}*/
}
