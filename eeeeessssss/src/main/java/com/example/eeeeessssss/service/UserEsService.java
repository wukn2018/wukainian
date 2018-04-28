package com.example.eeeeessssss.service;
import com.alibaba.fastjson.JSON;
import com.example.eeeeessssss.entity.UserEntity;
import com.example.eeeeessssss.pojo.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Service
public class UserEsService {



    @Autowired
    private EsIndexService esIndexService;

    @Autowired
    private UserService userService;



    /**
     *根据性别查询
     * @param index
     * @param type
     * @param sex
     */
    public void df(String index, String type, int sex) {
      List<UserEntity> list =  userService.findBySex( sex );
      for(UserEntity entity:list) {
          String json = JSON.toJSONString( entity );
          esIndexService.addStrMesg( index,type );
      }
    }








}
