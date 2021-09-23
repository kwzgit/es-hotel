package com.lantone.es.web;


import com.lantone.es.dto.HotelDTO;
import com.lantone.es.dto.RespDTO;
import com.lantone.es.facade.HotelFacade;
import com.lantone.es.vo.HotelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kongwz
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/tb-hotel")
@Api(value = "查询mysqlAPI", tags = { "查询mysqlAPI" })
public class TbHotelController {
    @Autowired
    private HotelFacade hotelFacade;

    @ApiOperation(value = "查询hotel API[kongwz]", notes = "id hotel的id")
    @PostMapping("/searchById")
    public RespDTO<HotelDTO> serchHotelById(@Valid @RequestBody HotelVO hotelVO) {
        HotelDTO hotelDTO = hotelFacade.selectById(hotelVO);
        return RespDTO.onSuc(hotelDTO);
    }



}
