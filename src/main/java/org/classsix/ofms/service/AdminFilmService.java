package org.classsix.ofms.service;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * Created by clxy on 2017/5/1.
 */
public interface AdminFilmService {
    ResponseMessage getAllFilm(Pageable pageable);

    ResponseMessage saveAll(List<MovieItem> movieItems);

    ResponseMessage deleteAll(List<MovieItem> movieItems);

    ResponseMessage searchFilm(String name, Pageable pageable);

    ResponseMessage searchFilmByKind(String kind, Pageable pageable);


}
