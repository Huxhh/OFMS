package org.classsix.ofms.repository;

import org.classsix.ofms.domin.FilmScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by huxh on 2017/5/3.
 */
public interface ScoreFilmRepository extends JpaRepository<FilmScore, Integer> {

    List<FilmScore> findByUid(int uid);
}
