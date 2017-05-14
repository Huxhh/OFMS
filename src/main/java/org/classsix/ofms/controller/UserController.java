package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.MovieItem;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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


    @ApiOperation(value = "获取用户所有已购买电影", notes = "获取已购买电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "null", value = "null", dataType = "null"),
    })
    @RequestMapping("/usr/film")
    public ResponseMessage userFilm(@PageableDefault(value = 15,sort = "id",direction = Sort.Direction.ASC)Pageable pageable, HttpServletRequest request){
        UserStatus userStatus = UserStatus.ERROR;
        Page<MovieItem> list = null;
        try{
            User user = (User)request.getSession().getAttribute("user");
            list = userService.findUserFilm(user.getId(),pageable);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(userStatus,list);
    }

    @ApiOperation(value = "获取用户已评价电影", notes = "获取已评价电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "null", value = "null", dataType = "null"),
    })
    @RequestMapping("/usr/filmjudged")
    public ResponseMessage userFilmJudged(@PageableDefault(value = 15,sort = "id",direction = Sort.Direction.ASC)Pageable pageable, HttpServletRequest request){
        UserStatus userStatus = UserStatus.ERROR;
        Page<MovieItem> list = null;
        try{
            User user = (User)request.getSession().getAttribute("user");
            list = userService.findUserFilmJudged(user.getId(),pageable);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(userStatus,list);
    }

    @ApiOperation(value = "获取用户未评价电影", notes = "获取未评价电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "null", value = "null", dataType = "null"),
    })
    @RequestMapping("/usr/filmunjudged")
    public ResponseMessage userFilmunJudged(@PageableDefault(value = 15,sort = "id",direction = Sort.Direction.ASC)Pageable pageable, HttpServletRequest request){
        UserStatus userStatus = UserStatus.ERROR;
        List<MovieItem> fullist;
        try{
            User user = (User)request.getSession().getAttribute("user");
            Page<MovieItem> sub = userService.findUserFilmJudged(user.getId(),pageable);
            List<MovieItem> sublist = sub.getContent();
            Page<MovieItem> full = userService.findUserFilm(user.getId(),pageable);
            fullist = full.getContent();
            for (MovieItem nful:fullist)
                for (MovieItem nsub : sublist){
                    if (Objects.equals(nsub.getName(), nful.getName())){
                        fullist.remove(nful);
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
            fullist = null;
        }
        return new ResponseMessage(userStatus,fullist);
    }


    @ApiOperation(value = "修改密码", notes = "用户修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "map", value = "{'password' : 'abc'}", required = true, dataType = "Json")
    })
    @RequestMapping("/usr/updateProfile")
    public ResponseMessage userFile(@RequestBody Map map,HttpServletRequest request){
        ResponseMessage responseMessage = new ResponseMessage(UserStatus.ERROR);
        try {
            User user = (User) request.getSession().getAttribute("user");
            responseMessage = userService.updateUser(user.getId(),(String) map.get("password"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }


    @ApiOperation(value = "充值", notes = "用户充值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "balance", value = "充值金额", dataType = "Integer"),
            @ApiImplicitParam(name = "map", value = "{'balance' : 123}", required = true, dataType = "Json")
    })
    @RequestMapping("/usr/updateBalance")
    public ResponseMessage userBalance(@RequestBody Map map,HttpServletRequest request){
        ResponseMessage responseMessage = new ResponseMessage(UserStatus.ERROR);
        try {
            User user = (User) request.getSession().getAttribute("user");
            responseMessage = userService.updateUserBalance(user.getId(),(Integer) map.get("balance"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }




}
