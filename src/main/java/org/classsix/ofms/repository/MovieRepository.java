package org.classsix.ofms.repository;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by clxy on 2017/4/11.
 */
public interface MovieRepository extends JpaRepository<MovieItem,Long> {


//    @Query("SELECT m FROM MovieItem m WHERE m.name like ?1")
    List<MovieItem> findByName(String name);

    MovieItem findById(long id);

    @Query("select m from MovieItem m order by m.buyCount DESC")
    Page<MovieItem> orderByBuyCount(Pageable pageable);







}
