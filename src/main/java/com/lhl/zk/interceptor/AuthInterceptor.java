package com.lhl.zk.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lhl.zk.util.AuthUtils;


public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log =LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		if (!AuthUtils.isLogin()) {
//			throw new RuntimeException("没有登录");
//			
//		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("host", request.getContextPath());
		modelAndView.addObject("isLogin", AuthUtils.isLogin());
		log.info("=========contextPath:{}",request.getContextPath());
		super.postHandle(request, response, handler, modelAndView);
	}
}
