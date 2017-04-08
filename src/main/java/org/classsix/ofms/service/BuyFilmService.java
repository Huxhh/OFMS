package org.classsix.ofms.service;

import org.classsix.ofms.domin.Film;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
@Service
public interface BuyFilmService {


    void buyFilm(int uid, int fid);

    List<Film> showAllPaidFilms(int uid);
}
