package com.example.eeeeessssss.controller;
import com.example.eeeeessssss.contans.EsContan;
import com.example.eeeeessssss.entity.UserEntity;
import com.example.eeeeessssss.response.ResultClient;
import com.example.eeeeessssss.service.UserEsService;
import com.example.eeeeessssss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "user")
public class UserController {


    @Autowired
    private UserService userService;


    @Autowired
    private UserEsService service;


    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    @PostMapping(value = "find")
    @ResponseBody
    public ResultClient findUserby( String id) {

        if(null != id) {
           UserEntity userEntity = userService.findUserBudISD(Long.parseLong( id ));
            return new ResultClient( userEntity );
        }
      return new ResultClient( "失败" );
    }


    /**
     * 根据性别查询
     * @param id
     * @return
     */
    @PostMapping(value = "findsex")
    @ResponseBody
    public ResultClient findBySex(int id) {
            List<UserEntity> list  = userService.findBySex(id);
            return new ResultClient( list );
    }




    /**
     * 根据性别查询
     * @param id
     * @return
     */
    @PostMapping(value = "es")
    @ResponseBody
    public ResultClient findSex(int id) {
        service.df( EsContan.INDEX_NAME01, EsContan.INDEX_TYPE01,id);
        return new ResultClient( "dd" );
    }




















}
