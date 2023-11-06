package com.mangosteen.app.controller;

import com.mangosteen.app.mapper.UserInfoMapper;
import com.mangosteen.app.model.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User APIs", description = "Related APIs for user management")
public class UserController {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserController(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @GetMapping("v1.0/users/{id}")
    @Operation(summary = "Get user information", description = "Return the specific user information",
    responses = {
            @ApiResponse(responseCode = "200", description = "User information found"),
            @ApiResponse(responseCode = "404", description = "User information not found")
    })
    UserInfo getUserInfoById(@Parameter(description = "The user id to fetch")
                             @PathVariable("id") Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }
}
