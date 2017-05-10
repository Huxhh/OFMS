package org.classsix.ofms.service.impl;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.repository.UserRepository;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserStatus confirmLogin(String userName, String password){
        UserStatus userStatus = UserStatus.ERROR;
        try {
            User user = userRepository.findByUserName(userName);
            if (user == null || !Objects.equals(user.getPassword(), password)){
                userStatus = UserStatus.ERROR;
            }else {
                userStatus = UserStatus.SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userStatus;

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
}
