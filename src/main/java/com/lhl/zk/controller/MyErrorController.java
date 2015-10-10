//package com.lhl.zk.controller;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
///**
// * 
// * @author liuhailin
// *
// */
//@RestController
//@RequestMapping("/error")
//public class MyErrorController implements ErrorController{
//   private static final String[] error_code = new String[] { "404", "500" };
//
//   @RequestMapping("/{code}")
//   public String error404(String code) {
//      code = StringUtils.trimToNull(code);
//      if (!ArrayUtils.contains(error_code, code)) {
//         code = "404";
//      }
//      return "error" + code;
//   }
//
//@Override
//public String getErrorPath() {
//	return "error";
//}
//}
