package com.lantone.es.vo;

import lombok.Data;

/**
 * @className: com.lantone.es.vo-> RequestVO
 * @description: 黑马旅游查询入参
 * @author: kongwz
 * @createDate: 2021-09-23 10:27
 * @version: 1.0
 * @todo:
 */
@Data
public class RequestVO {
    /**
     * 搜索关键字
     */
    private String key;
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页多少条
     */
    private Integer size;
    /**
     * 排序字段
     */
    private String sortBy;
    /**
     * 酒店品牌
     */
    private String brand;
    /**
     * 酒店所在城市
     */
    private String city;
    /**
     * 酒店星级
     */
    private String starName;
    /**
     * 最低价格
     */
    private Integer minPrice;
    /**
     * 最高价格
     */
    private Integer maxPrice;
    /**
     * 地理坐标
     */
    private String location;
}
