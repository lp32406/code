package com.jt.manage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.jt.manage.pojo.User;
public interface UserMapper {
	
	@Select("select * from user")
	List<User> findAll();

}
