package com.lantone.es.web;

import com.lantone.es.dto.RespDTO;
import com.lantone.es.facade.DocFacade;
import com.lantone.es.vo.IndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @className: com.lantone.es.web-> ESDocController
 * @description: ES文档操作
 * @author: kongwz
 * @createDate: 2021-09-22 13:51
 * @version: 1.0
 * @todo:
 */
@RestController
@RequestMapping("/es")
@Api(tags = {"ES文档操作API"})
public class ESDocController {
    @Autowired
    private DocFacade docFacade;

    @ApiOperation(value = "批量插入文档[kongwz]", notes = "name 索引的名称")
    @PostMapping("/bulkInsertDoc")
    public RespDTO bulkInsertDoc(@Valid @RequestBody IndexVO indexVO) throws IOException {
        docFacade.bulkInsertDoc(indexVO);
        return RespDTO.onSuc(true);
    }

    @ApiOperation(value = "根据id删除文档[kongwz]", notes = "docId id")
    @PostMapping("/deleteDoc")
    public RespDTO deleteDoc(@Valid @RequestBody IndexVO indexVO) throws IOException {
        docFacade.deleteDoc(indexVO);
        return RespDTO.onSuc(true);
    }

}
