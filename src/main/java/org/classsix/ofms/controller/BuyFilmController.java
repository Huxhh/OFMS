package org.classsix.ofms.controller;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.BuyFilm;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.service.BuyFilmService;
import org.classsix.ofms.status.BuyFilmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by huxh on 2017/5/1.
 */
@RestController
public class BuyFilmController {

    @Autowired
    BuyFilmService buyFilmService;


    @RequestMapping(value = "/user/buyfilm", method = RequestMethod.POST)
    public ResponseMessage BuyFilm(@RequestBody Map map) throws Exception{
        BuyFilmStatus buyFilmStatus = BuyFilmStatus.ARGUMENTS_ERROR;
        int uid = Integer.parseInt((String)map.get("uid"));
        int fid = Integer.parseInt((String)map.get("fid"));
        if(0 == uid || 0 == fid) {
            throw new Exception("buyfilm arguments error");
        } else {
            buyFilmStatus = buyFilmService.buyFilm(uid, fid);
        }
        return new ResponseMessage(buyFilmStatus);
    }

    @RequestMapping(value = "/user/showallpaodfilm" , method = RequestMethod.POST)
    public ResponseMessage showAllPaidMessage(@RequestBody Map map) throws Exception {
        BuyFilmStatus buyFilmStatus = BuyFilmStatus.SHOWALLPAIDFILM_ERROR;
        List<Film> films;
        int uid = Integer.parseInt((String) map.get("uid"));
        if(0 == uid) {
            throw new Exception("show all paid films error");
        }else {
            films = buyFilmService.showAllPaidFilms(uid);
            buyFilmStatus = BuyFilmStatus.SUCCESS;
        }
        return new ResponseMessage(buyFilmStatus, films);
    }

}
