package com.miaosha_1.service;

import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.domain.OrderInfo;
import com.miaosha_1.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qqtang
 * @Date 2020/11/23 16:40
 * @Desc
 */
@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Transactional//原子操作
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存、下订单、写入秒杀订单
        goodsService.reduceStock(goods);
        return orderService.creatOrder(user,goods);
    }
}
