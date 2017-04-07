package org.classsix.ofms.service;

import org.springframework.stereotype.Service;

/**
 * Created by huxh on 2017/4/7.
 */
@Service
public interface BuyFilmService {


    void buyFilm(int uid, int fid);
}
