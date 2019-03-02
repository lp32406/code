package com.eb.dianlianbao_server.util.basecore.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.dianlianbao_server.util.Dtos;
import com.eb.dianlianbao_server.util.JSONResult;
import com.eb.dianlianbao_server.util.basecore.BaseService;
import com.eb.dianlianbao_server.util.basecore.serviceImpl.dao.BaseMapper;

@Service
@Transactional("transactionManager")
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected BaseMapper<T> baseMapper;

	@Override
	public int save(T entity) {
		return baseMapper.save(entity);
	}

	@Override
	public int delete(T entity) {
		return baseMapper.delete(entity);
	}

	@Override
	public int update(T entity) {
		return baseMapper.update(entity);
	}

	@Override
	public T findById(int id) {
		return baseMapper.findById(id);
	}

	@Override
	public List<T> getAll() {
		return baseMapper.getAll();
	}

	@Override
	public T selectDestinationColumn(String column, Object value) {
		return baseMapper.selectDestinationColumn(column, value);
	}

	@Override
	public int updateDestinationColumn(String column, Object value, int id) {
		return baseMapper.updateDestinationColumn(column, value, id);
	}

	@Override
	public List<T> getSelectItme(T entity) {
		return baseMapper.getSelectItme(entity);
	}

	@Override
	public Map<Object, Object> findMapById(int id) {
		return baseMapper.findMapById(id);
	}

	@Override
	public List<Map<Object, Object>> getSelectMapItme(T entity) {
		return baseMapper.getSelectMapItme(entity);
	}

	@Override
	public Map<Object, Object> selectDestinationMap(String column, Object value) {
		return baseMapper.selectDestinationMap(column, value);
	}

	@Override
	public Map<Object, Object> findMapByIdContain(int id, int userId) {
		return baseMapper.findMapByIdContain(id, userId);
	}

	@Override
	public int updateDestinationColumnArray(Map<Object, Object> updateColunm, int id) {
		return baseMapper.updateDestinationColumnArray(updateColunm, id);
	}

	@Override
	public int updateDestinationColumnNum(String tableName, String column, Object value, int id) {
		return baseMapper.updateDestinationColumnNum(tableName, column, value, id);
	}

	@Override
	public int updateDestinationColumnValue(String column, Object value, int id) {
		return baseMapper.updateDestinationColumnValue(column, value, id);
	}

	@Override
	public Map<Object, Object> findMapByIdContainRes(T entity) {
		return baseMapper.findMapByIdContainRes(entity);
	}

	@Override
	public List<Map<Object, Object>> getSelectMapItmeByIds(String ids) {
		return baseMapper.getSelectMapItmeByIds(ids);
	}

	@Override
	public int deleteById(int id) {
		return baseMapper.deleteById(id);
	}

	@Override
	public T findByIdByEntity(T entity) {
		return baseMapper.findByIdByEntity(entity);
	}

	@Override
	public int updateDestinationByColumn(String desColumn, Object desColumnValue, String updateColumn, Object updateColumnValue) {

		return baseMapper.updateDestinationByColumn(desColumn, desColumnValue, updateColumn, updateColumnValue);
	}

}
