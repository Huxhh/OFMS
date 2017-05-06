package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.service.AdminFilmService;
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

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> getAllFilm(Pageable pageable) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Page<MovieItem> resultList;
        int status;

        try{
            resultList = movieRepository.findAll(pageable);
            resultMap.put("movie",resultList);
            resultMap.put("status",1);
        }
        catch (Exception e){
            status = 0;
            resultMap.put("status",0);
        }
        return resultMap;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveAll(List<MovieItem> movieItems) {
        Map<String,Object> resultMap = new HashMap<String,Object>();

        try{
            movieRepository.save(movieItems);
            resultMap.put("status",1);

        }
        catch (Exception e){
            resultMap.put("status",0);
        }
        return resultMap;

    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object> deleteAll(List<MovieItem> movieItems) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
           movieRepository.delete(movieItems);
           resultMap.put("status",1);
       }
       catch (Exception e){
            resultMap.put("status",0);
       }
       return resultMap;
    }
}
