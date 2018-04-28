package com.example.studyspring.Controller;

import com.example.studyspring.Configration.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {




    @Autowired
    private User user;


    @GetMapping(value = "/show")
    @ResponseBody
    public String sd() {
        user.say();
        return "ok";
    }















}
