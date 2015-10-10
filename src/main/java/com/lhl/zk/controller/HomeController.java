package com.lhl.zk.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author liuhailin
 *
 */
@RestController
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("/home")
	public ModelAndView home(ModelMap map){
		ModelAndView view = new ModelAndView("home");
		map.put("hello", "nmd");
		return view;
	}
}
