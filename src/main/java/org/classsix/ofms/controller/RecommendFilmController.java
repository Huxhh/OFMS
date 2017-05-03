package org.classsix.ofms.controller;

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

/**
 * Created by huxh on 2017/5/3.
 */
@RestController
public class RecommendFilmController {

    @Autowired
    RecommendFilmService recommendFilmService;

    @RequestMapping(value = "/recommend/buycount/{page}/{size}", method = RequestMethod.GET)
    public ResponseMessage recommendByBuyCount(@PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "buyCount");
        Page<MovieItem> movieItems = recommendFilmService.recommendByBuyCount(pageable);
        return new ResponseMessage(RecommendFilmStatus.SUCCESS, movieItems);
    }
}
