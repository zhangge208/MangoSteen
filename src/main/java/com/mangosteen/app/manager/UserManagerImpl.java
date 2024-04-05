package com.mangosteen.app.manager;

import com.mangosteen.app.converter.dtb.UserInfoDTBConverter;
import com.mangosteen.app.dao.UserDao;
import com.mangosteen.app.model.bo.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  UserManagerImpl implements UserManager {
    private final UserDao userDao;
    private final UserInfoDTBConverter converter;

    @Autowired
    public UserManagerImpl(UserDao userDao, UserInfoDTBConverter converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfoDO = userDao.getUserInfoByUserId(userId);
        return converter.convert(userInfoDO);
    }
}
