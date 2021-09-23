package com.lantone.es.web;

import com.lantone.es.dto.HotelDTO;
import com.lantone.es.dto.RespDTO;
import com.lantone.es.facade.IndexFacade;
import com.lantone.es.vo.HotelVO;
import com.lantone.es.vo.IndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @className: com.lantone.es.web-> ESIndexController
 * @description: ES索引操作
 * @author: kongwz
 * @createDate: 2021-09-22 13:50
 * @version: 1.0
 * @todo:
 */
@RestController
@RequestMapping("/es")
@Api(tags = {"ES索引操作API"})
public class ESIndexController {
    @Autowired
    private IndexFacade indexFacade;

    @ApiOperation(value = "创建索引[kongwz]", notes = "name 创建索引的名称")
    @PostMapping("/createIndex")
    public RespDTO createIndex(@Valid @RequestBody IndexVO indexVO) throws IOException {
        CreateIndexResponse index = indexFacade.createIndex(indexVO);
        return RespDTO.onSuc(index);
    }

    @ApiOperation(value = "查询索引是否存在[kongwz]", notes = "name 索引的名称")
    @PostMapping("/existIndex")
    public RespDTO existRequest(@Valid @RequestBody IndexVO indexVO) throws IOException {
        Boolean aBoolean = indexFacade.existIndex(indexVO);
        return RespDTO.onSuc(aBoolean);
    }

    @ApiOperation(value = "删除索引[kongwz]", notes = "name 索引的名称")
    @PostMapping("/deleteIndex")
    public RespDTO deleteIndex(@Valid @RequestBody IndexVO indexVO) throws IOException {
        Boolean aBoolean = indexFacade.deleteIndex(indexVO);
        return RespDTO.onSuc(aBoolean);
    }
}
