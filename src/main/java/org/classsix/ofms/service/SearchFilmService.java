package org.classsix.ofms.service;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by clxy on 2017/4/11.
 */
public interface SearchFilmService {
    Page<MovieItem> searchFilm(String name, Pageable pageable);

    Page<MovieItem> searchFilmByKind(String kind, Pageable pageable);





}
