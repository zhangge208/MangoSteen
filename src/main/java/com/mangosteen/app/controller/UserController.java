package com.mangosteen.app.controller;

import com.mangosteen.app.converter.btv.UserInfoBTVConverter;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.exception.ResourceNotFoundException;
import com.mangosteen.app.manager.UserManager;
import com.mangosteen.app.model.vo.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "User APIs", description = "Related APIs for user management")
public class UserController {

    private final UserManager userManager;
    private final UserInfoBTVConverter converter;

    @Autowired
    public UserController(UserManager userManager,
                          UserInfoBTVConverter converter) {
        this.userManager = userManager;
        this.converter = converter;
    }

    @GetMapping("v1.0/users/{id}")
    @Operation(summary = "Get user information", description = "Return the specific user information",
        responses = {
            @ApiResponse(responseCode = "200", description = "User information found"),
            @ApiResponse(responseCode = "404", description = "User information not found")
        })
    ResponseEntity<UserInfo> getUserInfoById(@Parameter(description = "The user id to fetch")
                                             @PathVariable("id") Long id) {
        if (id < 0L) {
            throw new InvalidParameterException("User Id must be greater than 0");
        }
        val userInfoBO = Optional.ofNullable(userManager.getUserInfoByUserId(id))
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("There is no user with id: %s", id)));
        return ResponseEntity.ok(converter.convert(userInfoBO));


    }
}
