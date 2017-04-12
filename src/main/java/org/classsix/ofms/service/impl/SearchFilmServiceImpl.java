package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.service.SearchFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clxy on 2017/4/11.
 */
@Service
public class SearchFilmServiceImpl implements SearchFilmService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<MovieItem> searchFilm(String name) {
        List<MovieItem> movieList = new ArrayList<MovieItem>();

        //name = "%"+name+"%";
        movieList = movieRepository.findByName(name);
        return movieList;
    }

}
