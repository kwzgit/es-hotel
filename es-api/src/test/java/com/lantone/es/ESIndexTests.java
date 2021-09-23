//package com.lantone.es;
//
//import com.lantone.es.constants.HotelIndexConstants;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.client.IndicesClient;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//
///**
// * @className: com.lantone.es-> ESApplicationTests
// * @description: es测试
// * @author: kongwz
// * @createDate: 2021-09-18 15:29
// * @version: 1.0
// * @todo:com.lantone.es.ESApplicationTests
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ESIndexTests {
//    @Autowired
//    private RestHighLevelClient client;
//
//    /**
//     * 删除索引库
//     */
//    @Test
//    public void testDelIndex() throws IOException {
//        // 删除索引的请求
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("哈哈1");
//        //删除索引
//        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//        // 得到响应
//        boolean b = delete.isAcknowledged();
//        System.out.println(b);
//    }
//
//    /**
//     * 创建索引库
//     */
//    @Test
//    public void testCreateIndex() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("哈哈1");
//        //这里是通过客户端，然后传递请求， RequestOptions.DEFAULT（这个是默认的，是es里面的，可以查看源码）
//        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//        System.out.println(response);
//    }
//
//    /**
//     * 创建酒店索引库
//     */
//    @Test
//    public void testCreateHotelIndex() throws IOException {
//        // 1.准备Request      PUT /hotel
//        CreateIndexRequest request = new CreateIndexRequest("hotel");
//        // 2.准备请求参数
//        request.source(HotelIndexConstants.MAPPING_TEMPLATE, XContentType.JSON);
//        // 3.发送请求
//        client.indices().create(request, RequestOptions.DEFAULT);
//    }
//
//    /**
//     * 查看索引是否存在
//     *
//     * @throws IOException
//     */
//    @Test
//    public void existRequest() throws IOException {
//        //创建请求
//        GetIndexRequest re = new GetIndexRequest("heima");
//        boolean exists = client.indices().exists(re, RequestOptions.DEFAULT);
//        System.out.println(exists);
//    }
//
//
//
//}
