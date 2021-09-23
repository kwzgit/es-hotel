package com.lantone.es.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantone.es.dto.HotelDTO;
import com.lantone.es.entity.TbHotel;
import com.lantone.es.service.TbHotelService;
import com.lantone.es.vo.HotelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className: com.lantone.es.facade-> HotelFacade
 * @description: hotel facade
 * @author: kongwz
 * @createDate: 2021-09-22 11:18
 * @version: 1.0
 * @todo:
 */
@Component
public class HotelFacade {
    @Autowired
    private TbHotelService hotelService;

    public HotelDTO selectById(HotelVO hotelVO) {
        /*QueryWrapper<TbHotel> id1 = new QueryWrapper<TbHotel>()
                .eq("id", id);
        List<TbHotel> records = hotelService.list(id1);
        return records.get(0);*/
        HotelDTO hotelDTO = new HotelDTO();
        Long id = hotelVO.getId();
        TbHotel tbHotel = hotelService.getById(id);
        if(tbHotel != null){
            BeanUtils.copyProperties(tbHotel,hotelDTO);
        }
        return hotelDTO;
    }

}
