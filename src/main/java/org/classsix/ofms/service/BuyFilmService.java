package org.classsix.ofms.service;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.status.BuyFilmStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
@Service
@Transactional
public interface BuyFilmService {


    BuyFilmStatus buyFilm(int uid, long fid);

    List<Film> showAllPaidFilms(int uid);


}
