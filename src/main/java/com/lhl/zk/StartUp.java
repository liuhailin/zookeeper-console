package com.lhl.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lhl.zk.interceptor.AuthInterceptor;

/**
 * 
 * @author liuhailin
 *
 */
@SpringBootApplication
public class StartUp extends WebMvcConfigurerAdapter{
	public static void main(String[] args) {
		 SpringApplication.run(StartUp.class);  
	}
	
	/**
     * 配置拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }

}
