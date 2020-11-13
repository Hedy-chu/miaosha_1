package com.miaosha_1.Controller;

import com.miaosha_1.domain.User;
import com.miaosha_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

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
}
