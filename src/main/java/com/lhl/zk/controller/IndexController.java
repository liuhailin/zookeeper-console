package com.lhl.zk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lhl.zk.util.ConfUtils;

/**
 * 
 * @author liuhailin
 *
 */
@RestController
@RequestMapping("")
public class IndexController {

   @RequestMapping(value = { "", "/", "/index" })
   public ModelAndView index() {
      ModelAndView mav = new ModelAndView("index");
      mav.addObject("addrs", ConfUtils.getConxtions());
      return mav;
   }

}
