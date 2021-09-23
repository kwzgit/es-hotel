package com.lantone.es.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @className: com.lantone.es.dto-> HotelDTO
 * @description: hotel操作出参
 * @author: kongwz
 * @createDate: 2021-09-22 13:20
 * @version: 1.0
 * @todo:
 */
@Data
public class HotelDTO {
    /**
     * 酒店id
     */
    private Long id;

    /**
     * 酒店名称
     */
    private String name;

    /**
     * 酒店地址
     */
    private String address;

    /**
     * 酒店价格
     */
    private Integer price;

    /**
     * 酒店评分
     */
    private Integer score;

    /**
     * 酒店品牌
     */
    private String brand;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 酒店星级，1星到5星，1钻到5钻
     */
    @TableField("star_name")
    private String starName;

    /**
     * 商圈
     */
    private String business;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 酒店图片
     */
    private String pic;

}
