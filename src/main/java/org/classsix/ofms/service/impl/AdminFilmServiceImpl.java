package org.classsix.ofms.service.impl;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.service.AdminFilmService;
import org.classsix.ofms.service.SearchFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clxy on 2017/5/1.
 */
@Service
public class AdminFilmServiceImpl implements AdminFilmService{
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SearchFilmService searchFilmService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResponseMessage getAllFilm(Pageable pageable) {
        Page<MovieItem> resultList;
        int status;

        try{
            resultList = movieRepository.findAll(pageable);
            return new ResponseMessage(0,null,resultList);
        }
        catch (Exception e){
            return new ResponseMessage(1,e.getMessage(),null);
        }

    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResponseMessage saveAll(List<MovieItem> movieItems) {


        try{
            movieRepository.save(movieItems);
            return new ResponseMessage(0,null,null);

        }
        catch (Exception e){
            return new ResponseMessage(1,e.getMessage(),null);
        }


    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResponseMessage deleteAll(List<MovieItem> movieItems) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
           movieRepository.delete(movieItems);
            return new ResponseMessage(0,null,null);
       }
       catch (Exception e){
           return new ResponseMessage(1,e.getMessage(),null);
       }

    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResponseMessage searchFilm(String name, Pageable pageable) {
        Page<MovieItem> resultList;

        try{

            resultList = searchFilmService.searchFilm(name,pageable);
            return new ResponseMessage(0,null,resultList);
        }
        catch (Exception e){
            return new ResponseMessage(1,e.getMessage(),null);
        }
    }

    @Override
    public ResponseMessage searchFilmByKind(String kind, Pageable pageable) {
        Page<MovieItem> resultList;
        try{

            resultList = searchFilmService.searchFilmByKind(kind,pageable);
            return new ResponseMessage(0,null,resultList);
        }
        catch (Exception e){
            return new ResponseMessage(1,e.getMessage(),null);
        }
    }
}
