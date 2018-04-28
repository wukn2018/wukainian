package com.example.eeeeessssss.pojo;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class SpuPojo {

    private Long spuId;

    private Date createDate;            //创建时间

    private String createBy;            //创建人id

    private String createByName;        //创建人姓名

    private Date updateDate;            //更新时间

    private String updateBy;            //更新人id

    private String updateByName;        //更新人姓名

    private Boolean delFlag;            //删除标记位 0:有效 1:删除

    private Integer platformId;         //平台id

    private Long shopId;                //店铺id

    private String shopName;            //店铺名称

    private String name;                //商品名称

    private String details;             //详情描述

    private Long categoryId;            //类目id

    private String categoryName;        //类目显示

    private Long showPrice;             //最低售价

    private Long showUnitPrice;         //最低单价

    private Long brandId;               //品牌id

    private String spuNo;               //商品编号

    private String saleStatus;          //上架状态 0:上架 1:下架

    private Long carriageId;            //定价模版id

    private Date saleDateBegin;         //可售时间 开始

    private Date saleDateEnd;           //可售时间 结束

    private String imageUrl;            //缩略图

    private String belongType;          //所属类型 设计师、买手等

    private Integer minBuyNum;          //最低起买: 单位为lot_size,  实际最低起买sku数量为lot_size*min_buy_num
    private Integer lotSize;            //每手增加数量，大于等于1

    private Integer weight;             //重量 单位:克

    private String presellType;         //预售类型 0:非预售 1:统一发货

    private Boolean standardFlag;       //标品标记位 0:非标品 1:标品
    private String unit;                //单位

    private String pkgUnit;             //包装单位

    private String subHead;             //商品副标题

    private  Boolean deliveryStatus;    //是否支持预约配送

    public SpuPojo() {
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Long showPrice) {
        this.showPrice = showPrice;
    }

    public Long getShowUnitPrice() {
        return showUnitPrice;
    }

    public void setShowUnitPrice(Long showUnitPrice) {
        this.showUnitPrice = showUnitPrice;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getSpuNo() {
        return spuNo;
    }

    public void setSpuNo(String spuNo) {
        this.spuNo = spuNo;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Long getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(Long carriageId) {
        this.carriageId = carriageId;
    }

    public Date getSaleDateBegin() {
        return saleDateBegin;
    }

    public void setSaleDateBegin(Date saleDateBegin) {
        this.saleDateBegin = saleDateBegin;
    }

    public Date getSaleDateEnd() {
        return saleDateEnd;
    }

    public void setSaleDateEnd(Date saleDateEnd) {
        this.saleDateEnd = saleDateEnd;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    public Integer getMinBuyNum() {
        return minBuyNum;
    }

    public void setMinBuyNum(Integer minBuyNum) {
        this.minBuyNum = minBuyNum;
    }

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPresellType() {
        return presellType;
    }

    public void setPresellType(String presellType) {
        this.presellType = presellType;
    }

    public Boolean getStandardFlag() {
        return standardFlag;
    }

    public void setStandardFlag(Boolean standardFlag) {
        this.standardFlag = standardFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPkgUnit() {
        return pkgUnit;
    }

    public void setPkgUnit(String pkgUnit) {
        this.pkgUnit = pkgUnit;
    }

    public String getSubHead() {
        return subHead;
    }

    public void setSubHead(String subHead) {
        this.subHead = subHead;
    }

    public Boolean getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Boolean deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this);
    }
}
