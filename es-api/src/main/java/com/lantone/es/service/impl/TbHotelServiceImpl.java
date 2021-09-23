package com.lantone.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.lantone.es.dto.PageResultDTO;
import com.lantone.es.entity.HotelDoc;
import com.lantone.es.entity.TbHotel;
import com.lantone.es.mapper.TbHotelMapper;
import com.lantone.es.service.TbHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantone.es.vo.RequestVO;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kongwz
 * @since 2021-09-22
 */
@Service
public class TbHotelServiceImpl extends ServiceImpl<TbHotelMapper, TbHotel> implements TbHotelService {
    @Autowired
    private RestHighLevelClient client;

    @Override
    public PageResultDTO search(RequestVO requestVO) {
        PageResultDTO pageResultDTO = new PageResultDTO();
        try {
            // 1.准备request
            SearchRequest request = new SearchRequest("hotel");
            // 2.准备DSL
            // 2.1 query(全文检索)
            getBoolQueryBuilder(request,requestVO);

            // 2.2 分页
            Integer page = requestVO.getPage();
            Integer size = requestVO.getSize();
            request.source().from((page - 1) * size).size(size);
            // 2.3 排序
            String location = requestVO.getLocation();
//            location = "31.034661,121.612282"; //测试坐标
            if (StringUtils.isNotBlank(location)) {
                request.source().sort(SortBuilders.geoDistanceSort("location", new GeoPoint(location))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS));
            }

            // 3.发送请求得到响应
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4.封装响应结果
            pageResultDTO = getPageResultDTO(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pageResultDTO;
    }

    public void getBoolQueryBuilder(SearchRequest request,RequestVO requestVO) {
        //构建BooleanQuery
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //关键字搜索
        String key = requestVO.getKey();
        if (StringUtils.isBlank(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
//                request.source().query(QueryBuilders.matchQuery("all", key));
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        }
        //条件过滤
        // 1 城市过滤
        if (StringUtils.isNotBlank(requestVO.getCity())) {
            boolQuery.filter(QueryBuilders.termQuery("city", requestVO.getCity()));
        }
        // 2 品牌过滤
        if (StringUtils.isNotBlank(requestVO.getBrand())) {
            boolQuery.filter(QueryBuilders.termQuery("brand", requestVO.getBrand()));
        }
        // 3 星级过滤
        if (StringUtils.isNotBlank(requestVO.getStarName())) {
            boolQuery.filter(QueryBuilders.termQuery("starName", requestVO.getStarName()));
        }
        // 4 价格过滤
        if (requestVO.getMinPrice() != null && requestVO.getMaxPrice() != null) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(requestVO.getMinPrice()).lte(requestVO.getMaxPrice()));
        }

        //算分控制
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(boolQuery,
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                //有广告标志的添加权重
                                QueryBuilders.termQuery("isAD",true),
                                ScoreFunctionBuilders.weightFactorFunction(10)
                        )
                });

        request.source().query(functionScoreQuery);
    }

    private PageResultDTO getPageResultDTO(SearchResponse response) {
        // 4.解析响应
        SearchHits searchHits = response.getHits();
        // 4.1 获取总条数
        long total = searchHits.getTotalHits().value;
        // 4.2 文档数组
        SearchHit[] hits = searchHits.getHits();
        List<HotelDoc> hotelDocList = new ArrayList<>();
        // 4.3 遍历文档
        for (SearchHit hit : hits) {
            //获取文档source
            String json = hit.getSourceAsString();
            // 反序列化为对象
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            //获取排序值
            Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                Object sortValue = sortValues[0];
                hotelDoc.setDistance(sortValue);
            }
            hotelDocList.add(hotelDoc);
        }
        return new PageResultDTO(total, hotelDocList);
    }
}
