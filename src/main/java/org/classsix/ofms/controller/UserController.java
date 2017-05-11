package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;


    @ApiOperation(value = "登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "map", value = "{'userName' : 'fengshenai', 'password' : 'xiaohua'}", required = true, dataType = "Json")
    })
    @RequestMapping("/usr/login")
    public ResponseMessage userLogin(@RequestBody Map map,HttpServletRequest request){
        UserStatus status = UserStatus.ERROR;
        try {
            User u = userService.confirmLogin((String) map.get("userName"),(String) map.get("password"));
            if (u == null)
                return new ResponseMessage(status);
            request.getSession().setAttribute("user",u);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user对象", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{'user' : {'userName':'a','tel':'1231','password':'123'}}", required = true, dataType = "Json")
    })
    @RequestMapping("/usr/regist")
    public ResponseMessage userRegist(@RequestBody Map map){
        UserStatus status = userService.addUser((User) map.get("user"));
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "找回密码", notes = "用户找回密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "tel", value = "电话号码", dataType = "String"),
            @ApiImplicitParam(name = "map", value = "{'userName' : 'a','tel':'1232'}", required = true, dataType = "Json")
    })
    @RequestMapping("/usr/findpassword")
    public ResponseMessage userFindPsw(@RequestBody Map map){
        UserStatus userStatus = UserStatus.ERROR;
        try {
            String s = userService.findUser((String) map.get("userName"),(String) map.get("tel"));
            userStatus = UserStatus.SUCCESS;
            return new ResponseMessage(userStatus,s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(userStatus);
    }








}
