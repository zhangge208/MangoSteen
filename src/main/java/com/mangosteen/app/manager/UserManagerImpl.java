package com.mangosteen.app.manager;

import java.time.LocalDateTime;

import com.mangosteen.app.converter.dtb.UserInfoDTBConverter;
import com.mangosteen.app.dao.UserDao;
import com.mangosteen.app.model.bo.UserInfo;
import com.mangosteen.app.utils.JWTUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager {
    private final UserDao userDao;
    private final UserInfoDTBConverter converter;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JWTUtil jwtUtil;


    @Autowired
    public UserManagerImpl(UserDao userDao,
                           UserInfoDTBConverter converter,
                           UserDetailsService userDetailsService,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder encoder, JWTUtil jwtUtil) {
        this.userDao = userDao;
        this.converter = converter;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfoDO = userDao.getUserInfoByUserId(userId);
        return converter.convert(userInfoDO);
    }

    @Override
    public String login(com.mangosteen.app.model.vo.UserInfo userInfo) {
        //利用Spring security 认证
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userInfo.getUsername());

        // 生成JWT
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @Override
    public boolean checkUserExisted(String username) {
        return (userDao.getUserInfoByUsername(username) != null);
    }

    @Override
    public UserInfo register(com.mangosteen.app.model.vo.UserInfo request) {
        UserInfo userInfo = UserInfo.builder()
                                    .username(request.getUsername())
                                    .password(encoder.encode(request.getPassword()))
                                    .email(request.getEmail())
                                    .createTime(LocalDateTime.now())
                                    .updateTime(LocalDateTime.now())
                                    .build();
        userDao.createUser(userInfo);
        return userInfo;
    }
}
