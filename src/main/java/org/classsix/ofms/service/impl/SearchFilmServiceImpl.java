package org.classsix.ofms.service.impl;

import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.service.SearchFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by clxy on 2017/4/11.
 */
@Service
public class SearchFilmServiceImpl implements SearchFilmService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Page<MovieItem> searchFilm(String name, Pageable pageable) {
        if(name != null){
        name = "%"+name+"%";
        return movieRepository.findByNameLike(name,pageable);
        }

        return movieRepository.findAll(pageable);

    }

    public Page<MovieItem> searchFilmByKind(String kind, Pageable pageable) {
        if(kind != null){
            kind = "%"+kind+"%";
            return movieRepository.findByKindLike(kind,pageable);
        }

        return movieRepository.findAll(pageable);

    }


}
