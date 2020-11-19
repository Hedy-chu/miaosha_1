package com.miaosha_1.Controller;

import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.domain.User;
import com.miaosha_1.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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
        return "goods_list";
    }
}
