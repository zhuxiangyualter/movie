package com.example.controller.user;

import com.example.dto.SeatDTO;
import com.example.result.Result;
import com.example.service.SeatService;
import com.example.vo.SeatVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 座位
 */
@RestController
@RequestMapping("/user/seat")
@Slf4j
public class SeatController {

    @Autowired
    private SeatService seatService;

    /**
     * 查询座位信息
     * @param seatDTO
     * @return
     */
    @PostMapping("/query")
    public Result<SeatVO> querySeat(@RequestBody SeatDTO seatDTO){
        return Result.success(seatService.querySeat(seatDTO));
    }


    /**
     * 买票
     * @param seatDTO
     * @return
     */
    @PostMapping("/buy")
    public Result<String> buy(@RequestBody SeatDTO seatDTO){

        seatService.buy(seatDTO);
        return Result.success();
    }


}
