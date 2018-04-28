package com.example.eeeeessssss.repository;
import com.example.eeeeessssss.entity.Spu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ouyoung
 * @Date 2018/1/17
 */
public interface SpuRepository extends JpaRepository<Spu, Long>, JpaSpecificationExecutor<Spu> {

    Spu findBySpuId(Long spuId);

    Spu findBySpuIdAndShopId(Long spuId, Long shopId);

    long countByCategoryIdAndDelFlag(Long categoryId, boolean delFlag);

    long countByCarriageIdAndDelFlag(Long carriageId, boolean delFlag);

    @Query("select s from Spu s where s.spuId in :spuIdList and s.delFlag = :delFlag ")
    List<Spu> findBySpuIdList(@Param("spuIdList") List<Long> spuIdList, @Param("delFlag") boolean delFlag);

    @Modifying
    @Query("update Spu set categoryName = :categoryName where categoryId = :categoryId ")
    int updateCategoryName(@Param("categoryId") Long categoryId, @Param("categoryName") String categoryName);

    @Modifying
    @Query("update Spu set saleStatus = :saleStatus, updateBy = :updateBy, updateByName = :updateByName , updateDate = :updateDate " +
            "where spuId = :spuId and delFlag = :delFlag ")
    int updateSaleSatus(@Param("spuId") Long spuId, @Param("saleStatus") String saleStatus, @Param("updateBy") String updateBy,
                        @Param("updateByName") String updateByName, @Param("updateDate") Date updateDate, @Param("delFlag") boolean delFlag);

    @Modifying
    @Query("update Spu set delFlag = :delFlag, updateBy = :updateBy, updateByName = :updateByName , updateDate = :updateDate " +
            "where spuId = :spuId ")
    int updateToDel(@Param("spuId") Long spuId, @Param("delFlag") boolean delFlag,
                    @Param("updateBy") String updateBy, @Param("updateByName") String updateByName, @Param("updateDate") Date updateDate);

    @Modifying
    @Query("update Spu set showPrice = :showPrice, updateBy = :updateBy, updateByName = :updateByName , updateDate = :updateDate " +
            "where spuId = :spuId and delFlag = :delFlag ")
    int updateShowPrice(@Param("spuId") Long spuId, @Param("showPrice") Integer showPrice, @Param("updateBy") String updateBy,
                        @Param("updateByName") String updateByName, @Param("updateDate") Date updateDate, @Param("delFlag") boolean delFlag);

    @Modifying
    @Query("update Spu s set name = :name, subHead = :subHead, createDate = :createDate, updateDate = :updateDate, " +
            " unit = :unit where spuId = :spuId and platformId = :platformId")
    int updateSyncInfoBySpuIdAndPlatformId(@Param("spuId") Long spuId, @Param("platformId") Integer platformId,
                                           @Param("name") String name, @Param("subHead") String subHead,
                                           @Param("createDate") Date createDate, @Param("updateDate") Date updateDate,
                                           @Param("unit") String unit);

    @Query(value = "SELECT s.* FROM t_spu s WHERE s.spu_id in :spuIds AND s.del_flag = :delFlag AND s.platform_id = :platformId AND s.sale_status = :saleStatus", nativeQuery = true)
    List<Spu> findAllSpu(@Param("platformId") Integer platformId, @Param("saleStatus") String saleStatus, @Param("delFlag") boolean delFlag, @Param("spuIds") List<Long> spuIds);


    @Query(value = "SELECT s.* FROM t_spu s WHERE DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= s.update_date AND s.platform_id = :platformId AND s.sale_status = :saleStatus AND s.del_flag = :delFlag" , nativeQuery = true)
    List<Spu> findNewSpu(@Param("platformId") Integer platformId, @Param("saleStatus") String saleStatus, @Param("delFlag") boolean delFlag);


    /**
     * 查询所有
     * @return
     */
    @Query(value = "SELECT s.* FROM t_spu s WHERE s.del_flag = :delFlag" ,nativeQuery = true)
    List<Spu> findAllSpu(@Param( "delFlag" )boolean delFlag);











}
