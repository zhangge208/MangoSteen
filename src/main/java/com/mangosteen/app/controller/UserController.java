package com.mangosteen.app.controller;

import com.mangosteen.app.mapper.UserInfoMapper;
import com.mangosteen.app.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserController(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @GetMapping("v1.0/users/{id}")
    UserInfo getUserInfoById(@PathVariable("id") Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }
}
