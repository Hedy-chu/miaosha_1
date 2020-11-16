package com.miaosha_1.Controller;

import com.miaosha_1.domain.User;
import com.miaosha_1.result.Result;
import com.miaosha_1.service.RedisService;
import com.miaosha_1.redis.UserKey;
import com.miaosha_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qqtang
 * @Date 2020/11/13 15:18
 * @Desc
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","储何");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public User dbGet(){
        User user = userService.getById(1);
        return user;
    }

//    @RequestMapping("/redis/get")
//    @ResponseBody
//    public Result<String> redisGet(){
//        Long v1 = redisService.get("key1", Long.class);
//        Boolean result = redisService.set("key2", 123);
//        String v2 = redisService.get("key2", String.class);
//        return Result.success(v2);
//    }

    @RequestMapping("/redis/prefix")
    @ResponseBody
    public Result<User> prefixTest(){
        User user1 = new User();
        user1.setId(1);
        user1.setName("111");
        Boolean b = redisService.set(UserKey.getById, "" + 1, user1);
        User user = redisService.get(UserKey.getById ,"" + 1, User.class);
        return Result.success(user);
    }
}
