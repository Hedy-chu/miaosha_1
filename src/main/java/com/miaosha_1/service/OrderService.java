package com.miaosha_1.service;

import com.miaosha_1.dao.OrderDao;
import com.miaosha_1.domain.MiaoshaOrder;
import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.domain.OrderInfo;
import com.miaosha_1.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author qqtang
 * @Date 2020/11/23 16:28
 * @Desc
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public MiaoshaOrder getMiaoShaOrderByUidGid(long Uid, long Gid) {
        return orderDao.getMiaoShaOrderByUidGid(Uid,Gid);
    }

    @Transactional//原子操作
    public OrderInfo creatOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setOrderStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(user.getId());
        miaoshaOrder.setOrderId(orderId);
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
