package org.classsix.ofms.service;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huxh on 2017/5/3.
 */
@Service
public interface RecommendFilmService {

    Page<MovieItem> recommendByBuyCount(Pageable page);
}
