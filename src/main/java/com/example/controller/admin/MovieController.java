package com.example.controller.user;

import com.example.entity.Movie;
import com.example.result.Result;
import com.example.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 电影
 */
@RestController
@RequestMapping("/user/movie")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 查询电影列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<Movie>> list(){
        log.info("全部电影列表");
        return Result.success(movieService.list());
    }

    /**
     * 查询正在放映的电影列表
     * @return
     */
    @GetMapping("/onplaying")
    public Result<List<Movie>> onplaying(){
        log.info("正在放映的电影列表");
        return Result.success(movieService.onplaying());
    }

}
