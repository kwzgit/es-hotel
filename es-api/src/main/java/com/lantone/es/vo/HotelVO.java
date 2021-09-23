package com.lantone.es.vo;

import lombok.Data;
import javax.validation.constraints.NotBlank;


/**
 * @className: com.lantone.es.vo-> HotelVO
 * @description: hotel操作入参
 * @author: kongwz
 * @createDate: 2021-09-22 13:19
 * @version: 1.0
 * @todo:
 */
@Data
public class HotelVO {
    @NotBlank(message = "请输id")
    private Long id;
}
