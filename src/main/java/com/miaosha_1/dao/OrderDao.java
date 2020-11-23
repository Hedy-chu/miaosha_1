package com.miaosha_1.dao;

import com.miaosha_1.domain.MiaoshaOrder;
import com.miaosha_1.domain.OrderInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author qqtang
 * @Date 2020/11/23 16:31
 * @Desc
 */
@Component
@Mapper
public interface OrderDao {
    @Select("select * from miaosha_order where user_id=#{uid} and goods_id=#{gid}")
    public MiaoshaOrder getMiaoShaOrderByUidGid(@Param("uid") Long uid, @Param("gid")Long gid);

    @Insert("insert into order_info (user_id,goods_id,goods_name,goods_count,goods_price,order_channel,order_status,create_date) values "
            + "(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{orderStatus},#{createDate})")
    @SelectKey(keyColumn="id",keyProperty="id",resultType=long.class,before=false,statement="select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order (user_id,goods_id,order_id) values (#{userId},#{goodsId},#{orderId})")
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
