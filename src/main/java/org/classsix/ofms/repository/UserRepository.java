package org.classsix.ofms.repository;


import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByUserNameAndMail(String userName,String mail);


    @Modifying
    @Query("update User u set u.password=:password where u.id=:id")
    int update(@Param("id") Integer id,@Param("password") String password);

    @Modifying
    @Query("update User u set u.balance=:balance where u.id=:id")
    int updateBalance(@Param("id")Integer id,@Param("balance") Integer balance);



}
