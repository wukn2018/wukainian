package com.example.jpa.controller;
import com.example.jpa.entity.UserEntity;
import com.example.jpa.pojo.User;
import com.example.jpa.response.ResultClient;
import com.example.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@Transactional(rollbackOn = Exception.class)
@RequestMapping(value = "jpa")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 全查询
     * @return
     */
    @PostMapping(value = "/user/list")
    public ResultClient queryList() {
        List<User> list = userService.queryList();
        return new ResultClient( list );
    }


    /**
     * 添加
     * @return
     */
    @PostMapping(value = "/user/add")
    public ResultClient addUs() {
        String s = userService.adduser();
        return new ResultClient( s );
    }


    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/user/delete")
    public ResultClient deleteUser() {
        return new ResultClient( userService.deleteUser() );

    }


    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/user/update")
    public ResultClient updateUser() throws Exception{
        String s = userService.uupter();
        return new ResultClient( s );
    }

    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/user/findsex")
    public ResultClient findbysex() throws Exception{
        List<UserEntity> userEntities = userService.findBySex();
        return new ResultClient( userEntities );
    }



    /**
     * 更新
     * @return
     */
    @PostMapping(value = "/user/up")
    public ResultClient updatep() throws Exception{
       String s = userService.uupter();
        return new ResultClient( s );
    }

    /**
     * 分页查询
     * @return
     */
    @PostMapping(value = "/user/queryPage")
    public ResultClient queryPage() throws Exception{
        return new ResultClient( userService.queryPage( 1,10 ) );
    }



    /**
     * 自定义查询
     * @return
     */
    @PostMapping(value = "/user/findUserByName")
    public ResultClient findUserByName() throws Exception{
        return new ResultClient( userService.findUserByName());
    }

    /**
     * 多条件分页查询
     * @return
     */
    @PostMapping(value = "/user/findAllPage")
    public ResultClient findUserByNamePage() throws Exception{
        return new ResultClient( userService.findByAllUser(1,5));
    }

    /**
     * 查询年龄在list中的人数
     * @param
     * @return
     */
    @PostMapping(value = "/user/findAllPages")
    public ResultClient findUserBySex() throws Exception{
        List<Integer> list = new ArrayList<>(  );
        list.add( 1 );
        list.add( 41 );
        list.add( 25 );
        return new ResultClient( userService.findUserBySex(list));
    }


    /**
     * 查询年龄大于5
     * @param
     * @return
     */
    @PostMapping(value = "/user/findAllPages0")
    public ResultClient findUser1() throws Exception{
        return new ResultClient( userService.findUser1(57));
    }

    /**
     * 查询年龄小于5
     * @param
     * @return
     */
    @PostMapping(value = "/user/findAllPages1")
    public ResultClient findUser2() throws Exception{
        return new ResultClient( userService.finUser2(57));
    }


    /**
     * 查询年龄在某个区间
     * @param
     * @return
     */
    @PostMapping(value = "/user/findAllPages2")
    public ResultClient findUser3() throws Exception{
        return new ResultClient( userService.finUser3(1,25));
    }
    /**
     *排序
     * @param
     * @return
     */
    @PostMapping(value = "/user/findAllPages3")
    public ResultClient findUser4() throws Exception{
        return new ResultClient( userService.finUser4());
    }





    


}
