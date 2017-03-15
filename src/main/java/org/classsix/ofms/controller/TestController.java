package org.classsix.ofms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huxh on 2017/3/10.
 */
@RestController
public class TestController {
    @ApiOperation(value = "测试",notes = "use for test")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    String home() {
        return "Hello World!";
    }

    @ApiOperation(value = "测试2",notes = "use for test2")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String home2(String id) {
        id = "1";
        return "Hello World!"+id;
    }

}
