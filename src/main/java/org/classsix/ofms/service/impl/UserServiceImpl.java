package org.classsix.ofms.service.impl;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.repository.MovieRepository;
import org.classsix.ofms.repository.UserRepository;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Objects;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public User confirmLogin(String userName, String password) throws Exception{
        User user = userRepository.findByUserNameAndPassword(userName,password);
        return user;
    }

    @Override
    public UserStatus addUser(User user){
        UserStatus status = UserStatus.ERROR;
        try {
            user.setBalance(0);
            userRepository.save(user);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }


    @Override
    public User findUser(String mail) throws Exception{
        User user1 = userRepository.findByMail(mail).get(0);
        if (user1 == null)
            throw new Exception();
        return user1;
    }


    @Override
    public List<MovieItem> findUserFilm(Integer userId) throws Exception{
        return userRepository.findUserFilm(userId);
    }

    @Override
    public List<MovieItem> findUserFilmJudged(Integer userId) throws Exception{
        return userRepository.findUserFilmJudged(userId);
    }

    @Override
    public List<Float> findUserFilmScore(Integer userId,Pageable pageable) throws Exception{
        return userRepository.findUserFilmScore(userId);
    }

    @Override
    public ResponseMessage updateUser(Integer id,String password){
        UserStatus status = UserStatus.ERROR;
        try {
            userRepository.update(id,password);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }
    @Override
    public ResponseMessage updateUserBalance(Integer id,Integer balance){
        UserStatus status = UserStatus.ERROR;
        try {
            userRepository.updateBalance(id,balance);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }



}
