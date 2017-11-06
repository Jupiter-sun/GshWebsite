package cn.yfjz.core.security.web;

import cn.yfjz.core.security.service.SecurityService;
import cn.yfjz.core.security.utils.UserAuthUtils;
import cn.yfjz.core.shiro.ShiroUser;
import cn.yfjz.core.sys.controller.BaseController;
import cn.yfjz.core.util.RequestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController extends BaseController {
	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return redirect + "/index";
		}
		//默认赋值message,避免freemarker尝试从session取值,造成异常
		model.addAttribute("errmsg", "");
		return "/login";
	}
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String loginPost(@RequestParam String username, @RequestParam String password, HttpServletRequest request, ModelMap model) throws Exception {
		try {
			securityService.login(username, password);
			//IP
			SecurityUtils.getSubject().getSession().setAttribute("ipAddress", RequestUtils.getIpAddr(request));
			String selectedRoleid = null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("selectedRole"+username)){
					selectedRoleid = cookie.getValue();
				}
			}
			securityService.setCurrentRole(selectedRoleid);
			this.saveLog("登录", "登录系统", "成功登录系统");
		} catch (UnknownAccountException uae) {
			model.addAttribute("errmsg", "账号不存在!");
			return "/login";
		} catch (IncorrectCredentialsException ice) {
			model.addAttribute("errmsg", "密码错误!");
			return "/login";
		} catch (LockedAccountException lae) {
			model.addAttribute("errmsg", "账号被锁定!");
			return "/login";
		} catch (Exception e) {
			model.addAttribute("errmsg", "未知错误,请联系管理员.");
			return "/login";
		}

		return redirect+"/index";
	}
	@RequestMapping("/switchRole")
	public String switchRole(@RequestParam String current_roleid, HttpServletResponse response){
		ShiroUser user = UserAuthUtils.getCurrentUser();
		securityService.setCurrentRole(current_roleid);
		if(user.getClass()!=null){
			Cookie c = new Cookie("selectedRole" + user.getLoginName(), user.getCurrentRole().getId().toString());
			response.addCookie(c);
		}
		return redirect+"/index";
	}
	/**
	 * 没有权限
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauth")
	public String unauth(Model model) throws Exception {
		if(!SecurityUtils.getSubject().isAuthenticated()){
			return redirect+"/login";
		}
		return "/common/unauth";

	}

	@RequestMapping(value="/logout")
	public String logout(RedirectAttributes redirectAttributes ){
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			this.saveLog("退出", "退出系统", "成功退出系统");
			subject.logout();
		}
		redirectAttributes.addFlashAttribute("errmsg", "您已安全退出");
		return "redirect:/login";
	}
}
