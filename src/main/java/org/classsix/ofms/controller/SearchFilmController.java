package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.service.SearchFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by clxy on 2017/4/11.
 */

@RestController
public class SearchFilmController {
    @Autowired
    SearchFilmService searchFilmService;
    @ApiOperation(value = "搜索电影",notes = "通过关键字搜索电影")
    @ApiImplicitParam(name="key",value = "搜索关键字",required = true,dataType = "String")
    @RequestMapping(value = "/search/",method = RequestMethod.GET)


    @ResponseBody
    List<MovieItem> searchFilm(String name){
         return searchFilmService.searchFilm(name);
     }
}
