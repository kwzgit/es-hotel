package com.lantone.es.facade;

import com.alibaba.fastjson.JSON;
import com.lantone.es.entity.HotelDoc;
import com.lantone.es.entity.TbHotel;
import com.lantone.es.service.TbHotelService;
import com.lantone.es.util.ListUtil;
import com.lantone.es.vo.IndexVO;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

/**
 * @className: com.lantone.es.facade-> DocFacade
 * @description: 文档操作facade
 * @author: kongwz
 * @createDate: 2021-09-22 14:22
 * @version: 1.0
 * @todo:
 */
@Component
public class DocFacade {
    @Autowired
    private TbHotelService tbHotelService;
    @Autowired
    private RestHighLevelClient client;

    public void bulkInsertDoc(IndexVO indexVO) throws IOException {
        //索引名称
        String name = indexVO.getName();
        // 查询所有的酒店数据
        List<TbHotel> list = tbHotelService.list();
        // 1.准备Request
        BulkRequest request = new BulkRequest();
        if (ListUtil.isNotEmpty(list)) {
            for (TbHotel hotel : list) {
                // 2.1.转为HotelDoc
                HotelDoc hotelDoc = new HotelDoc(hotel);
                // 2.2.转json
                String json = JSON.toJSONString(hotelDoc);
                // 2.3.添加请求
                IndexRequest doc = new IndexRequest(name).id(hotel.getId().toString()).source(json, XContentType.JSON);
                request.add(doc);
            }
        }
        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }

    public void deleteDoc(IndexVO indexVO) throws IOException {
        //hotel
        String name = indexVO.getName();
        //"61083"
        String docId = indexVO.getDocId();
        // 1.准备Request      // DELETE /hotel/_doc/{id}
        DeleteRequest request = new DeleteRequest(name,docId);
        // 2.发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }
}
