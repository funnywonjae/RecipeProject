package com.dj.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dj.domain.UserInfoDTO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if (request.getParameter("page") != null) {
			if (((UserInfoDTO) session.getAttribute("userInfo"))== null) {
				switch (request.getParameter("page")) {
				case ("write"):
				case("myRef"):
				case("bookmark_page"):
					System.out.println("인터셉터 " + request.getParameter("page"));
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('로그인이 필요합니다.'); location.href='/recipe/?page=signup' </script>");
					out.flush();
				}
			}
		}
		return true;
	}

}