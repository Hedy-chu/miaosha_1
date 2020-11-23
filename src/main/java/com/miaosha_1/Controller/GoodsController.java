package com.miaosha_1.Controller;

import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.service.GoodsService;
import com.miaosha_1.service.MiaoshaUserService;
import com.miaosha_1.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qqtang
 * @Date 2020/11/18 15:17
 * @Desc
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toLogin(Model model,MiaoshaUser user){
//                             HttpServletResponse response
//                          @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String cookieToken,
//                          @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String paramToken){
//        if (StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
//            return "login";
//        }
//
//        String token = StringUtils.isEmpty(cookieToken)?paramToken:cookieToken;
//        MiaoshaUser user = userService.getByToken(response,token);
        model.addAttribute("user",user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser user, @PathVariable("goodsId")long goodsId){
        GoodsVo goods = goodsService.goodsDetail(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int  miaoshaStatus = 0;
        //倒计时
        int remainSecond = 0;
        if (now < startAt){//秒杀没开始，倒计时
            miaoshaStatus = 0;
            remainSecond = (int) ((startAt-now)/1000);
        }else if (now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSecond = -1;
        }else {//正在进行
            miaoshaStatus = 1;
            remainSecond = 0;
        }
        model.addAttribute("user",user);
        model.addAttribute("goods",goods);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSecond",remainSecond);
        System.out.println(remainSecond);
        return "goods_detail";

    }
}
