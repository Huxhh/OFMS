package org.classsix.ofms.repository;


import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNameAndPassword(String userName,String password);
    User findByUserNameAndMail(String userName,String mail);
    User findById(int uid);



    @Modifying
    @Query("update User u set u.password=:password where u.id=:id")
    int update(@Param("id") Integer id,@Param("password") String password);


    @Modifying
    @Query("update User u set u.balance=:balance where u.id=:id")
    int updateBalance(@Param("id")Integer id,@Param("balance") Integer balance);





    @Query("select m from MovieItem m where m.id in (select bf.fid from BuyFilm bf where bf.uid=:id)")
    List<MovieItem> findUserFilm( @Param("id") Integer userId);

    @Query("select m from MovieItem m where m.id in (select fs.fid from FilmScore fs where fs.uid=:id)")
    List<MovieItem> findUserFilmJudged(@Param("id") Integer userId);

    @Query("select fs.score from FilmScore fs where fs.uid=:id")
    List<Float> findUserFilmScore(@Param("id") Integer userId);

}
