package com.imooc.user.service.impl;

import com.imooc.enums.Sex;
import com.imooc.user.mapper.UsersMapper;
import com.imooc.user.pojo.Users;
import com.imooc.user.pojo.bo.UserBO;
import com.imooc.user.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExist(String userName) {
        //使用Example查询
        Example example = new Example(Users.class);
        Example.Criteria userCriteria =example.createCriteria();
        userCriteria.andEqualTo("username",userName);
        Users users=usersMapper.selectOneByExample(example);
        return users==null ? true:false;
    }

    @Autowired
    private Sid sid;
    private static final String USER_FACE = "http://www.pacee1.com//img/avatar.png";
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBo) {
        Users users = new Users();
        String id= sid.nextShort();
        users.setId(id);
        users.setUsername(userBo.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        // 默认用户昵称同用户名
        users.setNickname(userBo.getUsername());
        // 默认头像
        users.setFace(USER_FACE);
        // 默认生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        users.setSex(Sex.secret.type);
        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users queryUserForLogin(String username, String password) throws Exception {
        //使用Example查询
        Example example = new Example(Users.class);
        Example.Criteria userCriteria =example.createCriteria();
        userCriteria.andEqualTo("username",username);
        userCriteria.andEqualTo("password",password);
        Users result=usersMapper.selectOneByExample(example);
        return result;
    }


}
