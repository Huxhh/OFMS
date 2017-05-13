package org.classsix.ofms.service;

import org.apache.mahout.cf.taste.model.DataModel;
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

    List<MovieItem> recommendByFavor(int uid) throws Exception;

    List<MovieItem> recommendResult(int uid, DataModel dataModel) throws Exception;
}
