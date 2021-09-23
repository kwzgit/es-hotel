package com.lantone.es.facade;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lantone.es.entity.HotelDoc;
import com.lantone.es.vo.IndexVO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @className: com.lantone.es.facade-> ESMatchFacade
 * @description:
 * @author: kongwz
 * @createDate: 2021-09-22 16:46
 * @version: 1.0
 * @todo:
 */
@Component
public class ESMatchFacade {
    @Autowired
    private RestHighLevelClient client;

    public SearchResponse matchAll(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        searchRequest.source()
                .query(QueryBuilders.matchAllQuery());
        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }

        return response;
    }

    public SearchResponse match(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("all", "如家");
        searchRequest.source()
                .query(matchQueryBuilder);

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }

        return response;
    }

    public SearchResponse multi_match(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("如家", "name", "business");
        searchRequest.source()
                .query(multiMatchQueryBuilder);

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }

        return response;
    }

    public SearchResponse term_match(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("city", "杭州");
        searchRequest.source()
                .query(termQueryBuilder);

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }

        return response;
    }

    public SearchResponse range_match(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gte(100).lte(200);
        searchRequest.source().query(rangeQueryBuilder);

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }

        return response;
    }

    public SearchResponse boolMatch(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //添加must条件
        boolQueryBuilder.must(QueryBuilders.termQuery("city","上海"));
        //添加filter条件
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gt(100));

        searchRequest.source().query(boolQueryBuilder);

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }

        return response;
    }

    public SearchResponse sortMatch(IndexVO indexVO) throws IOException {
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        searchRequest.source().query(QueryBuilders.matchAllQuery());
        //分页
        searchRequest.source().from(0).size(5);
        //价格排序
        searchRequest.source().sort("price", SortOrder.ASC);

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
        }
        return response;

    }

    public List<HotelDoc> highLighter(IndexVO indexVO) throws IOException {
        List<HotelDoc> hotelDocs = Lists.newArrayList();
        //索引名称 hotel
        String name = indexVO.getName();
        //准备请求
        SearchRequest searchRequest = new SearchRequest(name);
        //组织DSL参数
        searchRequest.source().query(QueryBuilders.matchQuery("all","如家"));
        //高亮
        searchRequest.source().highlighter(new HighlightBuilder()
        .field("name")
                //是否需要与查询字段匹配
        .requireFieldMatch(false));

        //发送请求，得到响应结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        SearchHits hits = response.getHits();
        //查询的总条数
        long total = hits.getTotalHits().value;
        //查询的结果数组
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit :hitsHits) {
            //具体的json
            String sourceAsString = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(sourceAsString, HotelDoc.class);
            //处理高亮
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(!CollectionUtils.isEmpty(highlightFields)){
                //获取高亮字段
                HighlightField highlightField = highlightFields.get("name");
                if(highlightField != null){
                    //取出高亮结果数组中的第一个，就是酒店名称
                    String hotel_name = highlightField.getFragments()[0].string();
                    hotelDoc.setName(hotel_name);
                    hotelDocs.add(hotelDoc);
                }

            }

        }
        return hotelDocs;
    }
}
