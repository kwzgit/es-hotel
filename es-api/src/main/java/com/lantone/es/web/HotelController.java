package com.lantone.es.web;

import com.lantone.es.dto.PageResultDTO;
import com.lantone.es.dto.RespDTO;
import com.lantone.es.facade.HotelSearchFacade;
import com.lantone.es.vo.IndexVO;
import com.lantone.es.vo.RequestVO;
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
 * @className: com.lantone.es.web-> HotelController
 * @description: 黑马旅游控制类
 * @author: kongwz
 * @createDate: 2021-09-23 10:35
 * @version: 1.0
 * @todo:
 */
@RestController
@RequestMapping("/hotel")
@Api(tags = {"酒店搜索API"})
public class HotelController {
    @Autowired
    private HotelSearchFacade hotelSearchFacade;

    @ApiOperation(value = "酒店查询[kongwz]",notes = "name: 索引的名称 这里为hotel")
    @PostMapping("/list")
    public PageResultDTO search(@RequestBody RequestVO requestVO) throws IOException {
        PageResultDTO searchResponse = hotelSearchFacade.search(requestVO);
        return searchResponse;
    }
}
