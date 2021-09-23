package com.lantone.es;

import com.alibaba.fastjson.JSON;
import com.lantone.es.entity.HotelDoc;
import com.lantone.es.entity.TbHotel;
import com.lantone.es.service.TbHotelService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @className: com.lantone.es-> ESDocTests
 * @description: es文档测试（添加，查询，删除，批量添加）
 * @author: kongwz
 * @createDate: 2021-09-22 10:25
 * @version: 1.0
 * @todo:
 */
@SpringBootTest
public class ESDocTests {
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private TbHotelService tbHotelService;

    @Test
    public void testAddDocument() throws IOException {
        // 1.查询数据库hotel数据
        TbHotel hotel = tbHotelService.getById(61083L);
        // 2.转换为HotelDoc
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 3.转JSON
        String json = JSON.toJSONString(hotelDoc);

        // 1.准备Request
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        // 2.准备请求参数DSL，其实就是文档的JSON字符串
        request.source(json, XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

}
