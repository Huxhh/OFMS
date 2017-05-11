package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.BuyFilm;
import org.classsix.ofms.domin.Film;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.service.FilmService;
import org.classsix.ofms.status.BuyFilmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by huxh on 2017/5/1.
 */
@RestController
public class BuyFilmController {

    @Autowired
    FilmService filmService;


    @ApiOperation(value = "购买电影", notes = "用户购买电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "购买电影的用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "fid", value = "用户所要购买的电影的id", dataType = "Long"),
            @ApiImplicitParam(name = "map", value = "uid与fid构成的json:{'uid' : '1', 'fid' : '1'}", required = true, dataType = "Json")
    })
    @RequestMapping(value = "/user/buyfilm", method = RequestMethod.POST)
    public ResponseMessage BuyFilm(@RequestBody Map map) throws Exception{
        BuyFilmStatus buyFilmStatus;
        int uid = Integer.parseInt((String)map.get("uid"));
        long fid = Integer.parseInt((String)map.get("fid"));
        if(0 == uid || 0 == fid) {
            return new ResponseMessage(BuyFilmStatus.ARGUMENTS_ERROR, "buy film argument error");
        } else {
            try {
                buyFilmStatus = filmService.buyFilm(uid, fid);
                return new ResponseMessage(buyFilmStatus);
            } catch (Exception e) {
                return new ResponseMessage(BuyFilmStatus.ERROR);
            }
        }

    }

    @RequestMapping(value = "/user/showallpaidfilm" , method = RequestMethod.POST)
    public ResponseMessage showAllPaidMessage(@RequestBody Map map) throws Exception {
        BuyFilmStatus buyFilmStatus;
        List<Film> films;
        int uid = Integer.parseInt((String) map.get("uid"));
        if(0 == uid) {
            return new ResponseMessage(BuyFilmStatus.ARGUMENTS_ERROR, "show all paid films arguments error");
        }else {
            try {
                films = filmService.showAllPaidFilms(uid);
                buyFilmStatus = BuyFilmStatus.SUCCESS;
                return new ResponseMessage(buyFilmStatus, films);
            } catch (Exception e) {
                return new ResponseMessage(BuyFilmStatus.SHOWALLPAIDFILM_ERROR);
            }
        }

    }

    @RequestMapping(value = "/user/scorefilm", method = RequestMethod.POST)
    public ResponseMessage scoreFilm(@RequestBody Map map) throws Exception {
        BuyFilmStatus buyFilmStatus;
        int uid = Integer.parseInt((String)map.get("uid"));
        long fid = Integer.parseInt((String)map.get("fid"));
        float score = Float.parseFloat((String)map.get("score"));
        if(0 == uid || 0 == fid) {
            return new ResponseMessage(BuyFilmStatus.ARGUMENTS_ERROR, "score film arguments error");
        } else {
            try {
                buyFilmStatus = filmService.scoreFilm(uid, fid, score);
                return new ResponseMessage(buyFilmStatus);
            } catch (Exception e) {
                return new ResponseMessage(BuyFilmStatus.SCOREFILM_ERROR);
            }
        }

    }

    @RequestMapping(value = "/film/{fid}", method = RequestMethod.GET)
    public ResponseMessage getFilmById(@PathVariable("fid") long fid) {
        MovieItem movieItem;
        if(0 == fid) {
            return new ResponseMessage(BuyFilmStatus.ARGUMENTS_ERROR, "get film by id arguments error");
        } else {
            try {
                movieItem = filmService.getFilmById(fid);
                return new ResponseMessage(BuyFilmStatus.SUCCESS, movieItem);
            } catch (Exception e) {
                return new ResponseMessage(BuyFilmStatus.GETFILMBYID_ERROR);
            }
        }
    }

}
