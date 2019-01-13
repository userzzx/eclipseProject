package com.accp.action;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.github.pagehelper.PageHelper;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.accp")
@MapperScan(basePackages="com.accp.mapper")
public class InitAction {
	public static void main(String[] args) {
		SpringApplication.run(InitAction.class, args);
	}
	
	@Bean
	public PageHelper getPageHelper() {
		PageHelper helper=new PageHelper();
		Properties pro=new Properties();
		pro.setProperty("dialect", "mysql");
		helper.setProperties(pro);
		return helper;
	}
}
