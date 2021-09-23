package com.lantone.es.facade;

import com.lantone.es.dto.PageResultDTO;
import com.lantone.es.service.TbHotelService;
import com.lantone.es.vo.RequestVO;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className: com.lantone.es.facade-> HotelSearchFacade
 * @description: 酒店查找facade
 * @author: kongwz
 * @createDate: 2021-09-23 10:38
 * @version: 1.0
 * @todo:
 */
@Component
public class HotelSearchFacade {
    @Autowired
    private TbHotelService tbHotelService;

    public PageResultDTO search(RequestVO requestVO){
        PageResultDTO pageResultDTO = null;
        try {
            pageResultDTO = tbHotelService.search(requestVO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageResultDTO;
    }
}
