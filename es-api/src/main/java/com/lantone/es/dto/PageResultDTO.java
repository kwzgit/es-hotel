package com.lantone.es.dto;

import com.lantone.es.entity.HotelDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: com.lantone.es.dto-> PageResultDTO
 * @description: 黑马旅游搜索结果返回类
 * @author: kongwz
 * @createDate: 2021-09-23 10:32
 * @version: 1.0
 * @todo:
 */
@Data
/*全部参数构造函数*/
@AllArgsConstructor
/*无参数构造函数*/
@NoArgsConstructor
public class PageResultDTO {
    /**
     * 返回总条数
     */
    private Long total;
    /**
     * 当前页酒店结果
     */
    private List<HotelDoc> hotels;
}
