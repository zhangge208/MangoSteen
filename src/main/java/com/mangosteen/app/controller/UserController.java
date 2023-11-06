package com.mangosteen.app.controller;

import com.mangosteen.app.exception.BizErrorCode;
import com.mangosteen.app.exception.ErrorResponse;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.exception.ResourceNotFoundException;
import com.mangosteen.app.exception.ServiceException;
import com.mangosteen.app.mapper.UserInfoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    ResponseEntity<?> getUserInfoById(@Parameter(description = "The user id to fetch")
                                      @PathVariable("id") Long id) {
        if (id < 0L) {
            throw new InvalidParameterException("User Id must be greater than 0");
        }
        val userInfo = Optional.ofNullable(userInfoMapper.getUserInfoByUserId(id))
                               .orElseThrow(() -> new ResourceNotFoundException(
                                       String.format("There is no user with id: %s", id)));
        return ResponseEntity.ok(userInfo);


    }
}
