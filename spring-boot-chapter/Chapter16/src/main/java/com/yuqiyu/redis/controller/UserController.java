package com.yuqiyu.redis.controller;

import com.yuqiyu.redis.entity.UserEntity;
import com.yuqiyu.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping(value = "/all")
    @ResponseBody
    public List<UserEntity> list() {
        return userService.findAll("123");
    }
}
