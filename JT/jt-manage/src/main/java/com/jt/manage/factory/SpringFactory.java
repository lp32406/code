package com.jt.manage.factory;

import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;

public class SpringFactory implements FactoryBean<Calendar>{

	@Override
	public Calendar getObject() throws Exception {
		
		System.out.println("我是spring工厂模式");
		return Calendar.getInstance();
	}

	@Override
	public Class<?> getObjectType() {
		
		return Calendar.class;
	}

	@Override
	public boolean isSingleton() {
		
		return true;
	}
}
