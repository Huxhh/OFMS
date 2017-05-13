package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.BuyFilm;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.domin.FilmScore;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.BuyFilmRepository;
import org.classsix.ofms.repository.FilmRepository;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.repository.ScoreFilmRepository;
import org.classsix.ofms.service.FilmService;
import org.classsix.ofms.status.BuyFilmStatus;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FilmServiceImpl implements FilmService {

    @Autowired
    BuyFilmRepository buyFilmRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScoreFilmRepository scoreFilmRepository;

    public BuyFilmStatus buyFilm(int uid, long fid) throws Exception {
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
            throw new Exception(e.getMessage(), e);
        }
        return buyFilmStatus;
    }

    public List<Film> showAllPaidFilms(int uid) throws Exception {
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
            throw new Exception(e.getMessage(), e);
        }
        return films;
    }

    public BuyFilmStatus scoreFilm(int uid, long fid, float score) throws Exception {
        BuyFilmStatus buyFilmStatus = BuyFilmStatus.SUCCESS;
        try {
            MovieItem movieItem = movieRepository.findById(fid);
            FilmScore filmScore = new FilmScore();
            filmScore.setUid(uid);
            filmScore.setFid(fid);
            filmScore.setScore(score);
            scoreFilmRepository.save(filmScore);
            long votecount = movieItem.getVoteCount();
            votecount++;
            movieItem.setVoteCount(votecount);
            movieRepository.save(movieItem);
        } catch (Exception e) {
            buyFilmStatus = BuyFilmStatus.SCOREFILM_ERROR;
            throw new Exception(e.getMessage(),e);
        }
        return buyFilmStatus;
    }

    public MovieItem getFilmById(long fid) throws Exception {
        MovieItem movieItem;
        try {
            movieItem = movieRepository.findById(fid);
        } catch (Exception e) {
            throw new Exception(e.getMessage(),e);
        }
        return movieItem;
    }

    public Page<MovieItem> getFilmByTime(Pageable pageable) {
        Page<MovieItem> movieItems = null;
        movieItems = movieRepository.orderByTime(pageable);
        return movieItems;
    }

    public void addData() {
        for(int i = 0;i < 1000;i++) {
            int uid = (int) (Math.random() * 10) + 1;
            long fid = (long) (Math.random() * 10000) + 1;
            float score = (float)((Math.random() * 5) % 5 + 1);
            FilmScore filmScore = new FilmScore();
            filmScore.setUid(uid);
            filmScore.setFid(fid);
            filmScore.setScore(score);
            scoreFilmRepository.save(filmScore);
        }
    }

}
