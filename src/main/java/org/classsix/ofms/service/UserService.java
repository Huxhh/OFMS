package org.classsix.ofms.service;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.status.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
public interface UserService {
    User confirmLogin(String userName, String password) throws Exception;
    UserStatus addUser(User user);
    String findUser(String userName, String tel) throws Exception;
    Page<MovieItem> findUserFilm(Integer userId, Pageable pageable) throws Exception;
    ResponseMessage updateUser(Integer id, String password);
    ResponseMessage updateUserBalance(Integer id,Integer balance);
    Page<MovieItem> findUserFilmJudged(Integer userId,Pageable pageable) throws Exception;
    Page<MovieItem> findUserFilmScore(Integer userId,Pageable pageable) throws Exception;
}
