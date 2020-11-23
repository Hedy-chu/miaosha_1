package com.miaosha_1.Controller;

import com.miaosha_1.domain.MiaoshaOrder;
import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.domain.OrderInfo;
import com.miaosha_1.result.CodeMsg;
import com.miaosha_1.service.GoodsService;
import com.miaosha_1.service.MiaoshaService;
import com.miaosha_1.service.OrderService;
import com.miaosha_1.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qqtang
 * @Date 2020/11/23 16:13
 * @Desc
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoShaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String doMiaoSha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId){
        if (user == null){
            return "/login";
        }
        //判断库存
        GoodsVo goods = goodsService.goodsDetail(goodsId);
        Integer stock = goods.getStockCount();
        if (stock <=0){
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER_ERROR.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoShaOrderByUidGid(user.getId(), goodsId);
        if (order!=null){
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存、下订单、写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user,goods);
        model.addAttribute("orderinfo",orderInfo);
        model.addAttribute("goods",goods);
        return "order_detail";
    }
}
