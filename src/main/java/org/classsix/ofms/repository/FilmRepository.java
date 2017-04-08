package org.classsix.ofms.repository;

import org.classsix.ofms.domin.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huxh on 2017/4/8.
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

    Film findById(int id);

}
