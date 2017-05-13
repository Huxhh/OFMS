package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.service.RecommendFilmService;
import org.classsix.ofms.status.RecommendFilmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxh on 2017/5/3.
 */
@RestController
public class RecommendFilmController {

    @Autowired
    RecommendFilmService recommendFilmService;

    @ApiOperation(value = "根据购买量推荐电影", notes = "根据电影购买量推荐电影，按购买量从大到小排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前要展示的页数（从0开始）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页展示数目", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/recommend/buycount/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage recommendByBuyCount(@PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "buyCount");
        Page<MovieItem> movieItems = recommendFilmService.recommendByBuyCount(pageable);
        return new ResponseMessage(RecommendFilmStatus.SUCCESS, movieItems);
    }

    @RequestMapping(value = "/recommend/favor/{uid}", method = RequestMethod.GET)
    public ResponseMessage recommendByFavor(@PathVariable("uid") int uid) {
        RecommendFilmStatus filmStatus = RecommendFilmStatus.SUCCESS;
        List<MovieItem> movieItems = new ArrayList<>();
        try {
            movieItems = recommendFilmService.recommendByFavor(uid);
        } catch (Exception e) {
            filmStatus = RecommendFilmStatus.ERROR;
            e.printStackTrace();
        }
        return new ResponseMessage(filmStatus, movieItems);
    }
}
