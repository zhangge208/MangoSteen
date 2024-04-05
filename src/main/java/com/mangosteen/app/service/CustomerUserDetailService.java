package com.mangosteen.app.service;

import java.util.Optional;

import com.mangosteen.app.dao.UserDao;
import com.mangosteen.app.exception.ResourceNotFoundException;
import com.mangosteen.app.model.LoginUser;
import com.mangosteen.app.model.dao.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public CustomerUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = Optional.ofNullable(userDao.getUserInfoByUsername(username))
                                    .orElseThrow(() -> new ResourceNotFoundException(
                                        String.format("There is no related user with username: %s", username)));
        return LoginUser.builder()
                        .userInfo(userInfo)
                        .build();
    }
}
