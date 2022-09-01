package com.imooc.user.controller;


import com.imooc.pojo.IMOOCJSONResult;
import com.imooc.user.pojo.Users;
import com.imooc.user.pojo.bo.UserBO;
import com.imooc.user.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassPortController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){
        //判断用户名是否为空;
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        //判断用户名是否存在
        if(!userService.queryUserNameIsExist(username)){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO){
        String username =userBO.getUsername();
        String password =userBO.getPassword();
        String confirmPassword =userBO.getConfirmPassword();
        //1判断用户名是否为空;
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)
                ||StringUtils.isBlank(confirmPassword)){
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }
        //2 判断用户名是否存在
        if(!userService.queryUserNameIsExist(username)){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        //3 密码不能少于6位
        if(password.length()<6){
            return IMOOCJSONResult.errorMsg("密码长度不能低于6位");
        }
        //4 密码与确认密码是否一致
        if(!password.equals(confirmPassword)){
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }
        Users users = userService.createUser(userBO);
        return IMOOCJSONResult.ok(users);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username =userBO.getUsername();
        String password =userBO.getPassword();
        //1判断用户名是否为空;
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }

        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if (userResult == null) {
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        return IMOOCJSONResult.ok(userResult);
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        CookieUtils.deleteCookie(request, response, "user");
        return IMOOCJSONResult.ok();
    }
}
