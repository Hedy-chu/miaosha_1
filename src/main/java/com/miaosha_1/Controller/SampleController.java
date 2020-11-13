package com.miaosha_1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qqtang
 * @Date 2020/11/13 15:18
 * @Desc
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","储何");
        return "hello";
    }
}
