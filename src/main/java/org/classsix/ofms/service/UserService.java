package org.classsix.ofms.service;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.status.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
public interface UserService {
    User confirmLogin(String userName, String password) throws Exception;
    UserStatus addUser(User user);
    User findUser(String userName, String mail) throws Exception;
    List<MovieItem> findUserFilm(Integer userId) throws Exception;
    ResponseMessage updateUser(Integer id, String password);
    ResponseMessage updateUserBalance(Integer id,Integer balance);
    List<MovieItem> findUserFilmJudged(Integer userId) throws Exception;
    List<Float> findUserFilmScore(Integer userId,Pageable pageable) throws Exception;
}
