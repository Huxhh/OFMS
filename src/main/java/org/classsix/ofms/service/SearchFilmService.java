package org.classsix.ofms.service;

import org.classsix.ofms.domin.MovieItem;

import java.util.List;

/**
 * Created by clxy on 2017/4/11.
 */
public interface SearchFilmService {
    List<MovieItem> searchFilm(String key);

}
