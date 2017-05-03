package org.classsix.ofms.service.impl;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.BuyFilm;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.BuyFilmRepository;
import org.classsix.ofms.repository.FilmRepository;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.service.BuyFilmService;
import org.classsix.ofms.status.BuyFilmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BuyFilmServiceImpl implements BuyFilmService {

    @Autowired
    BuyFilmRepository buyFilmRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    MovieRepository movieRepository;

    public BuyFilmStatus buyFilm(int uid, long fid) {
        BuyFilmStatus buyFilmStatus = BuyFilmStatus.ERROR;
        BuyFilm buyFilm = new BuyFilm();
        buyFilm.setUid(uid);
        buyFilm.setFid(fid);
        try {
            MovieItem movieItem = movieRepository.findById(fid);
            buyFilmRepository.save(buyFilm);
            long buyCount = movieItem.getBuyCount();
            buyCount++;
            movieItem.setBuyCount(buyCount);
            movieRepository.save(movieItem);
            buyFilmStatus = BuyFilmStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buyFilmStatus;
    }

    public List<Film> showAllPaidFilms(int uid) {
        List<Film> films = new ArrayList<>();
        List<BuyFilm> buyFilms;
        try {
            buyFilms = buyFilmRepository.findByUid(uid);
            for(BuyFilm b : buyFilms) {
                Film film;
                film = filmRepository.findById(b.getFid().intValue());
                films.add(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

}
