package com.example.eeeeessssss.service;
import com.example.eeeeessssss.entity.UserEntity;
import com.example.eeeeessssss.repository.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {



    @Autowired
    private UserJpa userJpa;


    /**
     * 根据性别查询
     * @return
     */
    public List<UserEntity> findBySex(int id) {
        List<UserEntity> userEntities = userJpa.findBySex( id );
        return userEntities;
    }



    /**
     *根据主键查询
     * @param id
     * @return
     */
    public UserEntity findUserBudISD(Long id) {
       UserEntity userEntity = null;
        if(null != id) {
            userEntity = userJpa.updateUserByName(id);
        }
        return userEntity;
    }









}
