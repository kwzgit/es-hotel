package com.lantone.es.web;

import com.lantone.es.dto.RespDTO;
import com.lantone.es.facade.ESMatchFacade;
import com.lantone.es.vo.IndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @className: com.lantone.es.web-> ESMatchController
 * @description: es match查询
 * @author: kongwz
 * @createDate: 2021-09-22 16:44
 * @version: 1.0
 * @todo:
 */
@RestController
@RequestMapping("/es")
@Api(tags = {"ES查询操作API（match）"})
public class ESMatchController {
    @Autowired
    private ESMatchFacade esMatchFacade;

    @ApiOperation(value = "mach_all 查询[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/matchAll")
    public RespDTO bulkInsertDoc(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.matchAll(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "全文检索查询(match 单字段分词查询)[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/match")
    public RespDTO match(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.match(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "全文检索查询(multi match 多字段分词查询)[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/multiMatch")
    public RespDTO multi_match(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.multi_match(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "精确查询(term查询)[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/termMatch")
    public RespDTO term_match(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.term_match(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "精确查询(range查询)[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/rangeMatch")
    public RespDTO range_match(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.range_match(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "复合查询[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/boolMatch")
    public RespDTO boolMatch(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.boolMatch(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "搜索结果处理-->分页排序[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/sortMatch")
    public RespDTO sortMatch(@Valid @RequestBody IndexVO indexVO) throws IOException {
        SearchResponse searchResponse = esMatchFacade.sortMatch(indexVO);
        return RespDTO.onSuc(searchResponse);
    }

    @ApiOperation(value = "搜索结果处理-->高亮[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/highLighter")
    public RespDTO highLighter(@Valid @RequestBody IndexVO indexVO) throws IOException {
        return RespDTO.onSuc(esMatchFacade.highLighter(indexVO));
    }

}
