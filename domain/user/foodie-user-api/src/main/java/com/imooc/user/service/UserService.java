package com.imooc.user.service;


import com.imooc.user.pojo.Users;
import com.imooc.user.pojo.bo.UserBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user-api")
public interface UserService {
    /**
     * 查询用户名是否存在
     * @param userName
     * @return
     */
    @GetMapping("user/exists")
    public boolean  queryUserNameIsExist(@RequestParam("userName")String userName);

    /**
     * 创建用户
     * @param userBo
     * @return
     */
    @PostMapping("user")
    public Users createUser(@RequestBody UserBO userBo);

    /**
     * 验证用户名和密码
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @GetMapping("verify")
    Users queryUserForLogin(@RequestParam("userName")String username,
                            @RequestParam("password")String password) throws Exception;
}
