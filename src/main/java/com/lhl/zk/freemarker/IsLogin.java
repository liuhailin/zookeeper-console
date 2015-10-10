package com.lhl.zk.freemarker;

import java.util.List;

import com.lhl.zk.util.AuthUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class IsLogin implements TemplateMethodModelEx {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arg0) throws TemplateModelException {
		return AuthUtils.isLogin();
	}

}
