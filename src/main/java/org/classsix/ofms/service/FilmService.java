package org.classsix.ofms.service;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.status.BuyFilmStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
@Service
@Transactional
public interface FilmService {


    BuyFilmStatus buyFilm(int uid, long fid) throws Exception;

    List<Film> showAllPaidFilms(int uid) throws Exception;

    BuyFilmStatus scoreFilm(int uid, long fid, float score) throws Exception;

    MovieItem getFilmById(long fid) throws Exception;

    void addData();


}
