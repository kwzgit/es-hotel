package com.lantone.es.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @className: com.lantone.es.vo-> IndexVO
 * @description: 索引入参
 * @author: kongwz
 * @createDate: 2021-09-22 13:55
 * @version: 1.0
 * @todo:
 */
@Data
public class IndexVO {
    @NotBlank(message = "创建索引的名称不能为空")
    private String name;

    //文档id
    private String docId;
}
