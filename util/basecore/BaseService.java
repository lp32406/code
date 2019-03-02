package com.eb.dianlianbao_server.util.basecore;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eb.dianlianbao_server.util.Dtos;
import com.eb.dianlianbao_server.util.JSONResult;

public interface BaseService<T> {
	public int save(T entity);// 保存

    public int delete(T entity);// 删除用户

    public int update(T entity);// 更新用户

    public int deleteById(int id);// 删除用户

    public T findById(int id);// 根据主键查找用户

    public T findByIdByEntity(T entity);

    public List<T> getAll();// 查看所有

    public List<T> getSelectItme(T entity);// 查看所有

	T selectDestinationColumn(String column, Object value);//根据目标字段获取对应实体，"column对应数据库字段，仅限于唯一字段"

	int updateDestinationColumn(String column, Object value, int id);//根据目标字段更新对应实体中的数据，"column对应数据库字段"

	public Map<Object,Object> findMapById(int id);// 根据主键查找用户

	public Map<Object,Object> findMapByIdContain(int id,int userId);// 根据主键查找

	public Map<Object,Object> findMapByIdContainRes(T entity);

	public List<Map<Object,Object>> getSelectMapItme(T entity); //查看所有

	public List<Map<Object,Object>> getSelectMapItmeByIds(String ids); //查看所有

	Map<Object,Object> selectDestinationMap(String column, Object value);//根据目标字段获取对应实体，"column对应数据库字段，仅限于唯一字段"

	int updateDestinationColumnArray(Map<Object, Object> updateColunm,int id);

	int updateDestinationColumnNum(String tableName,String column,Object value,int id);//根据目标字段更新对应实体中的数据，"column对应数据库字段",num=num+1

	int updateDestinationColumnValue(String column,Object value,int id);//根据目标字段更新对应实体中的数据，"column对应数据库字段"

	int updateDestinationByColumn(String desColumn, Object desColumnValue,String updateColumn, Object updateColumnValue);//根据特定字段修改

}
