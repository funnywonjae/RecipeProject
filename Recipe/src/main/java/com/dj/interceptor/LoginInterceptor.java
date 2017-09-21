package com.dj.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("zzz");
		HttpSession session = request.getSession();
		System.out.println("fff");
		ModelMap modelMap = modelAndView.getModelMap();
		System.out.println("kkk");

		Object userDTO = modelMap.get("user");
		System.out.println("여긴 인터셉터 POST");
		if (userDTO != null) {
			logger.info("new login data ");
			session.setAttribute(LOGIN, userDTO);
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		System.out.println("여긴 인터셉터 PRE");
		if (session.getAttribute(LOGIN) == null) {
			System.out.println("222");

		}
		System.out.println(333);
		return true;
	}

}
