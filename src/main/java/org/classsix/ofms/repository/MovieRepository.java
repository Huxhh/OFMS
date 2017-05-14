package org.classsix.ofms.repository;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by clxy on 2017/4/11.
 */
public interface MovieRepository extends JpaRepository<MovieItem,Long> {

    Page<MovieItem> findByNameLike(String name, Pageable pageable);
    Page<MovieItem> findAll(Pageable pageable);
    Page<MovieItem> findByKindLike(String kind,Pageable pageable);



    MovieItem findById(long id);

    @Query("select m from MovieItem m order by m.buyCount DESC")
    Page<MovieItem> orderByBuyCount(Pageable pageable);

    @Query("select m from MovieItem m order by m.year DESC")
    Page<MovieItem> orderByTime(Pageable pageable);



}
