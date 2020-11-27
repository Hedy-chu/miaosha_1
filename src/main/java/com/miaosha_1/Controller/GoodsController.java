package com.miaosha_1.Controller;

import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.result.Result;
import com.miaosha_1.service.GoodsService;
import com.miaosha_1.service.MiaoshaUserService;
import com.miaosha_1.service.RedisService;
import com.miaosha_1.vo.GoodsDetailVo;
import com.miaosha_1.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    RedisService redisService;

    //注入渲染
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

/*    //页面缓存的例子
    @RequestMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public String toLogin(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser user){
        model.addAttribute("user",user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if (!StringUtils.isEmpty(html)){
            return html;
        }

        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
//        return "goods_list"

        //手动渲染
        WebContext context = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);
        if (!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList,"",html);
        }
        return html;

    }

    //页面缓存的例子
    @RequestMapping(value = "/to_detail2/{goodsId}",produces = "text/html")
    @ResponseBody
    public String toDetail2(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser user,
                            @PathVariable("goodsId")long goodsId){
        String html = redisService.get(GoodsKey.getGoodsDeDetail, ""+goodsId, String.class);
        if (!StringUtils.isEmpty(html)){
            return html;
        }
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
//        return "goods_detail";

        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", context);
        if (!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsDeDetail,""+goodsId, html);
        }
        return html;
    }*/

    //页面静态化例子
    @RequestMapping(value = "/to_list")
    public String toLogin(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser user){
        model.addAttribute("user",user);

        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        System.out.println(goodsList);
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }
    //页面静态化例子
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> toDetail(HttpServletRequest request, HttpServletResponse response, Model model,
                                          MiaoshaUser user,
                                          @PathVariable("goodsId")long goodsId){
        GoodsVo goods = goodsService.goodsDetail(goodsId);
        System.out.println(goods);
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
//        return "goods_detail";
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setMiaoshaStatus(miaoshaStatus);
        vo.setRemainSecond(remainSecond);
        vo.setUser(user);
        return Result.success(vo);
    }


}
