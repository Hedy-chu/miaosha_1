package com.miaosha_1.dao;

import com.miaosha_1.domain.MiaoshaGoods;
import com.miaosha_1.vo.GoodsVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qqtang
 * @Date 2020/11/19 14:50
 * @Desc
 */
@Component
@Mapper
public interface GoodsDao {

    @Select("select g.* ,mg.stock_count ,mg.start_date ,mg.end_date ,miaosha_price from " +
            "miaosha_goods mg left join goods g on mg.goods_id =g.id ")
    public List<GoodsVo> listGoodsVo() ;

    @Select("select g.* ,mg.stock_count ,mg.start_date ,mg.end_date ,miaosha_price from " +
            "miaosha_goods mg left join goods g on mg.goods_id =g.id where g.id = #{goods_id}")
    public GoodsVo goodsDetail(@Param("goods_id")Long goods_id);

    @Update("update miaosha_goods set stock_count=stock_count-1 where goods_id=#{goodsId} and stock_count>0")
    public int reduceStock(MiaoshaGoods g);


}
