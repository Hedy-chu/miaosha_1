package com.miaosha_1.Controller;

import com.miaosha_1.result.CodeMsg;
import com.miaosha_1.result.Result;
import com.miaosha_1.service.MiaoshaUserService;
import com.miaosha_1.util.ValidatorUtil;
import com.miaosha_1.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


/**
 * @author qqtang
 * @Date 2020/11/17 11:10
 * @Desc
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result doLogin(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
//        //参数校验
//        String password = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if (StringUtils.isEmpty(password)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if (StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if (!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        //登录
        CodeMsg msg = miaoshaUserService.login(loginVo);
        if (msg.getCode() == 0){
            return Result.success(true);
        }else{
            return Result.error(msg);
        }
    }
}
