package com.miaosha_1.dao;

import com.miaosha_1.domain.MiaoshaUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author qqtang
 * @Date 2020/11/17 16:15
 * @Desc
 */
@Component
@Mapper
public interface MiaoshaUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id")Long id);

    @Update("update miaosha_user set password = #{password} where id = #{id}")
    public void update(MiaoshaUser toBeUpdate);
}
