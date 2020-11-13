package com.miaosha_1.dao;

import com.miaosha_1.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author qqtang
 * @Date 2020/11/13 17:09
 * @Desc
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id")Integer id);
}
