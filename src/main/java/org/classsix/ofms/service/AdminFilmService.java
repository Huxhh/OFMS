package org.classsix.ofms.service;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Pageable;

import java.util.*;
/**
 * Created by clxy on 2017/5/1.
 */
public interface AdminFilmService {
    Map<String,Object> getAllFilm(Pageable pageable);

    Map<String,Object> saveAll(List<MovieItem> movieItems);

    Map<String,Object> deleteAll(List<MovieItem> movieItems);


}
