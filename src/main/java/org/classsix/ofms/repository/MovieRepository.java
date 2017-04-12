package org.classsix.ofms.repository;

import org.classsix.ofms.domin.MovieItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by clxy on 2017/4/11.
 */
public interface MovieRepository extends JpaRepository<MovieItem,Long> {


//    @Query("SELECT m FROM MovieItem m WHERE m.name like ?1")
    List<MovieItem> findByName(String name);







}
