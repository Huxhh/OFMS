package org.classsix.ofms.repository;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by clxy on 2017/4/11.
 */
public interface MovieRepository extends JpaRepository<MovieItem,Long> {

    Page<MovieItem> findByNameLike(String name, Pageable pageable);
    Page<MovieItem> findAll(Pageable pageable);
    Page<MovieItem> findByKindLike(String kind,Pageable pageable);









}
