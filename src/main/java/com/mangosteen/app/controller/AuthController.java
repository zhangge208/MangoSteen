package com.mangosteen.app.controller;

import com.mangosteen.app.manager.UserManager;
import com.mangosteen.app.model.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserManager userManager;

    @Autowired
    public AuthController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(path = "v1.0/token")
    public ResponseEntity<String> login(@RequestBody UserInfo userInfo) {
        // todo: request validation
        String token = userManager.login(userInfo);
        return ResponseEntity.ok(token);
    }
}
