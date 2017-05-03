package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.service.RecommendFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxh on 2017/5/3.
 */
@Service
public class RecommendFilmServiceImpl implements RecommendFilmService {

    @Autowired
    MovieRepository movieRepository;

    public Page<MovieItem> recommendByBuyCount(Pageable page) {
        Page<MovieItem> movieItems;
        movieItems = movieRepository.orderByBuyCount(page);
        return movieItems;
    }
}
