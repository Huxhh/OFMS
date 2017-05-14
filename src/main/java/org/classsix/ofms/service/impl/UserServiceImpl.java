package org.classsix.ofms.service.impl;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.repository.UserRepository;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Objects;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User confirmLogin(String userName, String password) throws Exception{
        User user = userRepository.findByUserName(userName);
        return user;
    }

    @Override
    public UserStatus addUser(User user){
        UserStatus status = UserStatus.ERROR;
        try {
            userRepository.save(user);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }


    @Override
    public String findUser(String userName, String tel) throws Exception{
        User user1 = userRepository.findByUserNameAndTel(userName,tel);
        if (user1 == null)
            return "";
        return user1.getPassword();
    }


    @Override
    public Page<MovieItem> findUserFilm(Integer userId,Pageable pageable) throws Exception{
        return userRepository.findUserFilm(userId,pageable);
    }

    @Override
    public Page<MovieItem> findUserFilmJudged(Integer userId,Pageable pageable) throws Exception{
        return userRepository.findUserFilmJudged(userId,pageable);
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
