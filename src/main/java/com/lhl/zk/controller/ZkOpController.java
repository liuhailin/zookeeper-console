package com.lhl.zk.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.lhl.zk.constants.Constants;
import com.lhl.zk.repository.Zk;

/**
 * 
 * @author liuhailin
 *
 */
@RestController
@RequestMapping("/op")
public class ZkOpController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkOpController.class);
	private static final String SEPARATOR = "/";

	@RequestMapping("/create")
	public ModelAndView create(Model model, String parent, String name, String data) throws Exception {
		String cxnstr = getCxnstr();
		if (StringUtils.isBlank(cxnstr)) {
			return new ModelAndView("redirect:/");
		}
		parent = StringUtils.isBlank(parent) ? SEPARATOR : StringUtils.trimToEmpty(parent);
		parent = StringUtils.endsWith(parent, SEPARATOR) ? parent : parent + SEPARATOR;
		name = StringUtils.startsWith(name, SEPARATOR) ? StringUtils.substring(name, 1) : name;
		Zk zk = new Zk(cxnstr);
		String path = parent + name;
		zk.create(path, data.getBytes(Charset.forName("UTF-8")));
		return  new ModelAndView("redirect:/read/node?path=" + path);
	}

	@RequestMapping("/edit")
	public ModelAndView edit(Model model, String path, String data) throws Exception {
		String cxnstr = getCxnstr();
		if (StringUtils.isBlank(cxnstr)) {
			return  new ModelAndView("redirect:/");
		}
		path = StringUtils.isBlank(path) ? SEPARATOR : StringUtils.trimToEmpty(path);
		path = StringUtils.endsWith(path, "/") ? StringUtils.substring(path, 0, path.length()-1) : path;
		Zk zk = new Zk(cxnstr);
		zk.edit(path, data.getBytes(Charset.forName("UTF-8")));
		return  new ModelAndView("redirect:/read/node?path=" + path);
	}

	@RequestMapping("/delete")
	public ModelAndView delete(Model model, String path, String data) throws Exception {
		String cxnstr = getCxnstr();
		if (StringUtils.isBlank(cxnstr)) {
			return  new ModelAndView("redirect:/");
		}
		path = StringUtils.isBlank(path) ? SEPARATOR : StringUtils.trimToEmpty(path);
		path = StringUtils.endsWith(path, "/") ? StringUtils.substring(path, 0, path.length()-1) : path;
		Zk zk = new Zk(cxnstr);
		zk.delete(path);
		return  new ModelAndView("redirect:/read/node?path=" + StringUtils.substring(path, 0, StringUtils.lastIndexOf(path, "/")));
	}

	@RequestMapping("/rmrdel")
	public ModelAndView rmrdel(Model model, String path, String data) throws Exception {
		String cxnstr = getCxnstr();
		if (StringUtils.isBlank(cxnstr)) {
			return  new ModelAndView("redirect:/");
		}
		path = StringUtils.isBlank(path) ? SEPARATOR : StringUtils.trimToEmpty(path);
		path = StringUtils.endsWith(path, "/") ? StringUtils.substring(path, 0, path.length()-1) : path;
		Zk zk = new Zk(cxnstr);
		zk.deleteRecursive(path);
		LOGGER.info("deleteRecursive, cxnstr:{}, path:{}", cxnstr, path);
		return  new ModelAndView("redirect:/read/node?path=" + StringUtils.substring(path, 0, StringUtils.lastIndexOf(path, "/")));
	}

	private String getCxnstr() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return (String) req.getSession().getAttribute(Constants.CX_STR);
	}

}
