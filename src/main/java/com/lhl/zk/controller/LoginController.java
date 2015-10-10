package com.lhl.zk.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lhl.zk.util.AuthUtils;

/**
 * 
 * @author liuhailin
 *
 */
@RestController
@RequestMapping("")
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, String username, String password) {
		AuthUtils.login(username, password);
		String referer = request.getHeader("referer");
		String cp = request.getContextPath();
		return new ModelAndView("redirect:" + StringUtils.substringAfter(referer, cp));
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		AuthUtils.logout();
		String referer = request.getHeader("referer");
		String cp = request.getContextPath();
		return new ModelAndView("redirect:" + StringUtils.substringAfter(referer, cp));
	}

}
