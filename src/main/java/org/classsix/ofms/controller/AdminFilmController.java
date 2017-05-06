package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.service.AdminFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by clxy on 2017/5/1.
 */
@RestController
public class AdminFilmController {
    @Autowired
    AdminFilmService adminFilmService;

    @RequestMapping(value = "/admin/",method = RequestMethod.GET)
    @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "Integer")
    @ApiOperation(value = "获取电影信息",notes = "分页抓取所有电影信息")
    Map<String,Object> getAllFilm( @PageableDefault(value = 15,sort = "id",direction = Sort.Direction.ASC)Pageable pageable){
        return adminFilmService.getAllFilm(pageable);
    }

    @RequestMapping(value = "/admin/",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除电影",notes = "电影的Model里至少要有ID字段")
    Map<String,Object> deleteFilm(List<MovieItem> movieItems){
        return adminFilmService.deleteAll(movieItems);
    }


    @ApiImplicitParam(name = "movieItems", value = "要修改的电影", required = true,dataType = "MovieItem")
    @ApiOperation(value = "删除电影",notes = "电影的Model里至少要有ID字段")
    @RequestMapping(value = "/admin/",method = RequestMethod.PUT)
    Map<String,Object> editFilm(@ModelAttribute List<MovieItem> movieItems){
        return adminFilmService.saveAll(movieItems);
    }

    @ApiImplicitParam(name = "movieItems", value = "要增加的电影", required = true,dataType = "Array[MovieItem]")
    @ApiOperation(value = "增加电影",notes = "电影的Model里要有完整的电影信息")
    @RequestMapping(value = "/admin/",method = RequestMethod.POST)
    Map<String,Object> addFilm(@ModelAttribute("movieItems") List<MovieItem> movieItems){
        return adminFilmService.saveAll(movieItems);
    }
}
