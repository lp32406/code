package com.eb.dianlianbao_server.util.basecore.serviceImpl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T> {
	public int save(T entity);// 保存

	public int delete(T entity);// 删除用户

	public int deleteById(int id);// 删除用户

	public int update(T entity);// 更新用户

	public T findById(int id);// 根据主键查找用户

	public T findByIdByEntity(T entity);

	public List<T> getAll();// 查看所有

	T selectDestinationColumn(@Param("column") String column, @Param("value") Object value);// 根据目标字段获取对应实体，"column对应数据库字段，仅限于唯一字段"

	public List<T> getSelectItme(T entity);// 查看所有

	public Map<Object, Object> findMapById(int id);// 根据主键查找用户

	public Map<Object, Object> findMapByIdContain(@Param("id") int id, @Param("userId") int userId);// 根据主键查找用户

	public Map<Object, Object> findMapByIdContainRes(T entity);

	public List<Map<Object, Object>> getSelectMapItme(T entity); // 查看所有

	Map<Object, Object> selectDestinationMap(@Param("column") String column, @Param("value") Object value);// 根据目标字段获取对应实体，"column对应数据库字段，仅限于唯一字段"

	/**
	 * 动态修改多个字段
	 * @param updateCoulnm 更新字段集合
	 * @return
	 */
	int updateDestinationColumnArray(@Param("updateColunm") Map<Object, Object> updateColunm, @Param("id") int id);

	int updateDestinationColumnValue(@Param("column") String column, @Param("value") Object value, @Param("id") int id);// 根据目标字段更新对应实体中的数据，"column对应数据库字段"

	public List<Map<Object, Object>> getSelectMapItmeByIds(@Param("ids") String ids);

	int updateDestinationColumn(@Param("column") String column, @Param("value") Object value, @Param("id") int id);// 根据目标字段更新对应实体中的数据，"column对应数据库字段"

	int updateDestinationColumnNum(@Param("tableName") String tableName, @Param("column") String column, @Param("value") Object value, @Param("id") int id);// 根据目标字段更新对应实体中的数据，"column对应数据库字段",num=num+1

	int updateDestinationByColumn(@Param("desColumn") String desColumn, @Param("desColumnValue") Object desColumnValue, @Param("updateColumn") String updateColumn,
			@Param("updateColumnValue") Object updateColumnValue);// 根据特定字段修改

}
