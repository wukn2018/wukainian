package com.yuqiyu.redis.jpa;
import com.yuqiyu.redis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public interface UserJpa extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {





    /**
     * 删除
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "delete from t_user  where id  = :id",nativeQuery = true)
    public void deleteUser(@Param("id") int id);


    /**
     * 修改
     * @param sex
     * @param address
     * @param id
     */
    @Modifying
    @Query(value = "update #{#entityName} c set c.sex = :sex, c.address = :address where c.id = :id" )
    public Integer updateUser(@Param("sex") Integer sex, @Param("address") String address, @Param("id") Long id);


    /**
     *根据用户名来查询
     * @param name
     * @return
     */
     UserEntity findUserEntityByName(String name);

    /**
     * 根据用户名和性别查询
     * @param name
     * @param sex
     * @return
     */
     UserEntity findUserEntityByNameAndSex(String name, int sex);



    @Query(value = "select c from UserEntity c where c.sex = :sex")
    public List<UserEntity> findBySex(@Param("sex") int sex);



    /**
     * 通过id查询
     * @param id
     * @return
     */
     @Query(value = "select u from UserEntity u where u.id = :id")
     public UserEntity updateUserByName(@Param("id") Long id);
















}
