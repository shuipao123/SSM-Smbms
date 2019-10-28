package com.dzl.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dzl.smbms.pojo.User;
import com.dzl.smbms.service.UserService;
import com.dzl.smbms.tools.Constants;

/**
 * 首页
 * @author 微笑
 *
 */
@Controller
public class IndexController {
	
	private Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private UserService userService;
	
	//登录页面
	@RequestMapping("/login.html")
	public String login() {
		logger.debug("UserController welcome SMBMS===============");
		return "login";
	}

	//登录验证
	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam("userCode") String userCode, @RequestParam("userPassword") String userPassword,
			HttpSession session, HttpServletRequest request) {
		// 调用service方法，进行用户匹配
		User user = userService.getLoginUser(userCode, userPassword);
		if (null != user) {// 登录成功
			if (!user.getUserPassword().equals(userPassword)) {
//				throw new RuntimeException("密码输入错误！");
				request.setAttribute("error", "密码输入错误！");
				//return "redirect:/login.html";
			} else {
				// 放入session中
				session.setAttribute(Constants.USER_SESSION, user);
				// 跳转到（frame.jsp）
				return "redirect:/user/main.html";
			}
		} else {
//			throw new RuntimeException("用户名不存在！");
			request.setAttribute("error", "用户名不存在！");
			//return "redirect:/login.html";
		}
		return "login";
	}
	@RequestMapping(value = "/main.html")
	public String main(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "redirect:/user/login.html";// 跳转到登录页面
		}
		return "jsp/frame"; // 登录成功：跳转到首页
	}

	// 局部异常处理
	/*@RequestMapping(value = "exlogin.html", method = RequestMethod.GET)
	public String exLogin(@RequestParam("userCode") String userCode, @RequestParam("userPassword") String userPassword,
			HttpSession session, HttpServletRequest request) {
		logger.debug("exLogin----------------------------------------------"); //
		// 调用service方法，进行匹配
		User user = userService.login(userCode, userPassword);
		if (null == user) {// 登录失败
			throw new RuntimeException("用户名或密码不正确");
		}
		return "redirect:/user/main.html";
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public String handlerException(RuntimeException e, HttpServletRequest req) {
		req.setAttribute("error", e);
		return "login";
	}*/
}
	
