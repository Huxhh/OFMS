package org.classsix.ofms.repository;


import org.classsix.ofms.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByUserNameAndTel(String userName,String tel);
}
