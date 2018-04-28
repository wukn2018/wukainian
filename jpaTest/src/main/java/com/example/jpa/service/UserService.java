package com.example.jpa.service;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */

import com.example.jpa.entity.UserEntity;
import com.example.jpa.pojo.User;
import com.example.jpa.repotory.UserJpa;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

    @Autowired
    private UserJpa userJpa;


    /**
     * 查询所有
     */
    public List<User> queryList() {
        List<UserEntity> listUser =  userJpa.findAll();
        List<User> list = new ArrayList<>(  );
        Iterator<UserEntity> iterator = listUser.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            User user = new User(  );
            BeanUtils.copyProperties( userEntity,user );
            list.add( user );
        }
        return list;
    }


    /**
     * 添加数据
     * @return
     */
    public String adduser() {
        UserEntity userEntity  = null;
        Random random = new Random(  );
        for(int i = 0;i<100;++i) {
            int aa = random.nextInt(100)+1;
            userEntity = new UserEntity(  );
            userEntity.setName( "sd"+i );
            userEntity.setAddress( "dgh"+i );
            userEntity.setSex( aa );
            UserEntity userEntity1 = userJpa.save( userEntity );
        }
        String s = "添加成功";
        return s;
    }

    /**
     *
     * @return
     */
    public String uupter() {
       Integer id =  userJpa.updateUser( 666,"666",2L );
        return "修改成功"+id;
    }


    /**
     * 删除
     * @return
     */
    public String deleteUser() {
       userJpa.deleteUser( 2 );
        return "删除成功";
    }




    /**
     * 更新
     * @return
     * @throws Exception
     */
    public String updateUser() throws Exception{
        UserEntity userEntity = userJpa.findOne( 2L );
        userEntity.setAddress( "gghddddd" );
        userJpa.save( userEntity );
        return "成功";
    }


    public List<UserEntity> findBySex() {
        List<UserEntity> userEntities = userJpa.findBySex( 1 );
        return userEntities;
    }


    /**
     * 分页查询
     * @return
     */
    public List<User> queryPage(int pageNo, int pageSize) {
        //排序
        List<Sort.Order> olist = new ArrayList(  );
        olist.add( new Sort.Order( Sort.Direction.ASC,"id" ) );
        Sort sort = new Sort( olist );
        //分页
        PageRequest request = new PageRequest(pageNo-1,pageSize , sort);
        List<UserEntity> list = userJpa.findAll(request).getContent();
        List<User> list1 = new ArrayList(  );
        Iterator<UserEntity> iterator = list.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            User user = new User(  );
            BeanUtils.copyProperties( userEntity,user );
            list1.add( user );
        }
        return list1;
    }


    /**
     * 按多条件查询数据
     * @param entity
     * @return
     */
    private Specification<UserEntity> where(final UserEntity entity) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != entity.getId()){
                    list.add(cb.equal(root.get("id").as(Long.class), entity.getId()));
                }
                if (null != entity.getName()) {
                    list.add(cb.equal(root.get("name").as(String.class), entity.getName()));
                }
                return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
            }
        };
    }


    /**
     *
     * @return
     */
    public User findUserByName() {
        UserEntity userEntity = userJpa.findUserEntityByName( "sd7" );
        User user = new User(  );
        BeanUtils.copyProperties( userEntity,user );
        return user;
    }


    /**
     * 多条件分页查询
     * @return
     */
    public Page<UserEntity> findByAllUser(int pageNo, int pageSize) {
        //排序
        List<Sort.Order> olist = new ArrayList(  );
        olist.add( new Sort.Order( Sort.Direction.ASC,"id" ) );
        Sort sort = new Sort( olist );
        //分页
        PageRequest pageRequest = new PageRequest( pageNo-1,pageSize,sort );
        //多条件查询
        UserEntity userEntity = new UserEntity(  );
        userEntity.setId( 3L );
        userEntity.setName( "sd" );
        Page<UserEntity> u = userJpa.findAll(where(userEntity),pageRequest);
        return u;
    }


    /**
     * 查询年龄在list中的人数
     * @param sex
     * @return
     */
    public List<UserEntity> findUserBySex(List<Integer> sex) {
        return userJpa.findAll( new Specification<UserEntity>( ) {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return root.in( sex );
            }
        } );

    }





    /**
     * 查询年龄大于5
     * @param
     * @return
     */
    public List<UserEntity> findUser1(Integer sex) {
        return userJpa.findAll( new Specification<UserEntity>( ) {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.gt(root.get("sex").as(Integer.class), sex);
            }
        } );

    }


    /**
     * 查询年龄小于sex的人员
     * @param sex
     * @return
     */
    public List<UserEntity> finUser2(Integer sex) {
        return userJpa.findAll( new Specification<UserEntity>( ) {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.lt( root.get( "sex" ).as( Integer.class ) , sex );
            }
        } );

    }



    /**
     * 查询sex在某个区间的人员
     * @param
     * @return
     */
    public List<UserEntity> finUser3(Integer start, Integer end) {
        return userJpa.findAll( new Specification<UserEntity>( ) {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.between( root.get( "sex" ).as( Integer.class ) , start,end );
            }
        } );
    }




    /**
     * 排序
     * @param
     * @return
     */
    public List<UserEntity> finUser4() {
        return userJpa.findAll( new Specification<UserEntity>( ) {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               query.orderBy( cb.asc( root.get( "sex" ).as( Integer.class ) ) );
               return query.getRestriction();
            }
        } );
    }
















}
