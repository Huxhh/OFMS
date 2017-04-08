package org.classsix.ofms.repository;

import org.classsix.ofms.domin.BuyFilm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by huxh on 2017/4/7.
 */
public interface BuyFilmRepository extends JpaRepository<BuyFilm, Integer> {

    List<BuyFilm> findByUid(int uid);
}
