package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.BuyFilm;
import org.classsix.ofms.repository.BuyFilmRepository;
import org.classsix.ofms.service.BuyFilmService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huxh on 2017/4/7.
 */
public class BuyFilmServiceImpl implements BuyFilmService {

    @Autowired
    BuyFilmRepository buyFilmRepository;

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

}
