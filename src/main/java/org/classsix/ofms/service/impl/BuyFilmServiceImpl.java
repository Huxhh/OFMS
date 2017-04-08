package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.BuyFilm;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.repository.BuyFilmRepository;
import org.classsix.ofms.repository.FilmRepository;
import org.classsix.ofms.service.BuyFilmService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
public class BuyFilmServiceImpl implements BuyFilmService {

    @Autowired
    BuyFilmRepository buyFilmRepository;

    @Autowired
    FilmRepository filmRepository;

    public void buyFilm(int uid, int fid) {
        BuyFilm buyFilm = new BuyFilm();
        buyFilm.setUid(uid);
        buyFilm.setFid(fid);
        try {
            buyFilmRepository.save(buyFilm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Film> showAllPaidFilms(int uid) {
        List<Film> films = new ArrayList<>();
        List<BuyFilm> buyFilms;
        try {
            buyFilms = buyFilmRepository.findByUid(uid);
            for(BuyFilm b : buyFilms) {
                Film film;
                film = filmRepository.findById(b.getFid());
                films.add(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

}
