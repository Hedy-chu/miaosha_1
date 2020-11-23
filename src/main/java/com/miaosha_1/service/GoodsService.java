package com.miaosha_1.service;

import com.miaosha_1.dao.GoodsDao;
import com.miaosha_1.domain.MiaoshaGoods;
import com.miaosha_1.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qqtang
 * @Date 2020/11/19 14:50
 * @Desc
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        List<GoodsVo> goodsVos = goodsDao.listGoodsVo();
        return goodsVos;
    }

    public GoodsVo goodsDetail(Long goodsId){
        GoodsVo goodsVo = goodsDao.goodsDetail(goodsId);
        return goodsVo;
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
