package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.service.SearchFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clxy on 2017/4/11.
 */

@RestController
public class SearchFilmController {
    @Autowired
    SearchFilmService searchFilmService;


    @ApiOperation(value = "搜索电影",notes = "通过关键字搜索电影")
    @ApiImplicitParams(
            {@ApiImplicitParam(name="name",value = "搜索关键字",required = true,dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int")}
    )


    @RequestMapping(value = "/search/name",method = RequestMethod.GET)


    Map<String,Object> searchFilmByName(String name, @PageableDefault(value = 15,sort = "id",direction = Sort.Direction.ASC)Pageable pageable){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Page<MovieItem> resultList;
        int status;
        try{

            resultList = searchFilmService.searchFilm(name,pageable);
            status = 1;
            resultMap.put("status",status);
            resultMap.put("movie",resultList);
        }
        catch (Exception e){
            status = 0;
            resultMap.put("status",status);
        }

        return resultMap;

     }

    @ApiOperation(value = "搜索电影",notes = "通过类别搜索电影")
    @ApiImplicitParams(
            {@ApiImplicitParam(name="kind",value = "电影类别",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int")}
    )

    @RequestMapping(value = "/search/kind",method = RequestMethod.GET)
     Map<String,Object> searchFilmByKind(String kind, @PageableDefault(value = 15,sort = "id",direction = Sort.Direction.ASC)Pageable pageable){
         Map<String,Object> resultMap = new HashMap<String, Object>();
         Page<MovieItem> resultList;
         int status;
         try{

             resultList = searchFilmService.searchFilmByKind(kind,pageable);
             status = 1;
             resultMap.put("status",status);
             resultMap.put("movie",resultList);
         }
         catch (Exception e){
             status = 0;
             resultMap.put("status",status);
         }

         return resultMap;

     }
}
