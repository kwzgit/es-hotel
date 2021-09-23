package com.lantone.es.facade;

import com.lantone.es.vo.IndexVO;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @className: com.lantone.es.facade-> IndexFacade
 * @description: 索引facade
 * @author: kongwz
 * @createDate: 2021-09-22 14:05
 * @version: 1.0
 * @todo:
 */
@Component
public class IndexFacade {
    @Autowired
    private RestHighLevelClient client;

    public CreateIndexResponse createIndex(IndexVO indexVO) throws IOException {
        String name = indexVO.getName();
        CreateIndexRequest request = new CreateIndexRequest(name);
        //这里是通过客户端，然后传递请求， RequestOptions.DEFAULT（这个是默认的，是es里面的，可以查看源码）
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response;
    }

    public Boolean existIndex(IndexVO indexVO) throws IOException {
        String name = indexVO.getName();
        GetIndexRequest re = new GetIndexRequest(name);
        boolean exists = client.indices().exists(re, RequestOptions.DEFAULT);
        return exists;
    }

    public Boolean deleteIndex(IndexVO indexVO) throws IOException {
        String name = indexVO.getName();
        if(!existIndex(indexVO)){
            return null;
        }
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(name);
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        boolean b = delete.isAcknowledged();
        return b;
    }
}
