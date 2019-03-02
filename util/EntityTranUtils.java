package com.eb.dianlianbao_server.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;



/**
 * 实体转换工具
 *
 */
public class EntityTranUtils {
	
	private static final Logger log = Logger.getLogger(EntityTranUtils.class);
	
	/**
	 * Dtos对象转换为实体
	 * @param entity
	 * @param newDto
	 * @return
	 */
	public static <T>T getEntity(T entity,Dtos newDto){
		try {
			BeanUtils.populate(entity, newDto);
			return entity;
		} catch (IllegalAccessException e) {
			log.error("bean copy err", e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error("bean copy err", e);
			e.printStackTrace();
		}
		return null;
	}
}
