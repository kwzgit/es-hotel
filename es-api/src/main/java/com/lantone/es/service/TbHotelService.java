package com.lantone.es.service;

import com.lantone.es.dto.PageResultDTO;
import com.lantone.es.entity.TbHotel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lantone.es.vo.RequestVO;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kongwz
 * @since 2021-09-22
 */
public interface TbHotelService extends IService<TbHotel> {

    PageResultDTO search(RequestVO requestVO) throws IOException;
}
