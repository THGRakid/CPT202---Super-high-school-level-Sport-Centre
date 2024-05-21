package com.shsl.controller.user;

import com.shsl.entity.Stadium;
import com.shsl.result.Result;
import com.shsl.service.StadiumService;
import com.shsl.vo.StadiumListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
@Api(tags = "Book function Interface")
public class BookController {

    @Autowired
    private StadiumService stadiumService;

    /**
     * 1. Enter to stadium book page
     */

    @GetMapping("/stadium/show/{id}")
    @ApiOperation(value = "Stadium Book Show Page")
    public Result<StadiumListVO> showAllPlaces(@PathVariable Integer id) {
        log.info("Show stadium information id：{}", id);
        Stadium stadium = stadiumService.getStadiumById(id);
        StadiumListVO stadiumListVO = new StadiumListVO(
                stadium.getStaName(), stadium.getPlaceNumber(),
                stadium.getStaInfo(), stadium.getStaPicture(),
                stadium.getLimit(), stadium.getPrice(), stadium.getLocation());
        return Result.success(stadiumListVO);
    }
}
