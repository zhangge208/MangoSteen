package com.mangosteen.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mangosteen.app.dao.mapper.UserInfoMapper;
import com.mangosteen.app.model.dao.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UserDaoLegacyTest {

    @Test
    @Disabled
    void testGetUserInfoByUserId() {
        // Arrange
        Long userId = 100L;
        val userInfo = UserInfo.builder()
                               .id(userId)
                               .username("xxxx")
                               .build();
        val userInfoMapper = new UserInfoMapper() {
            // 定义行为
            @Override
            public UserInfo getUserInfoByUserId(Long id) {
                return userInfo;
            }
        };
        // Act
        val userDao = new UserDaoImpl(userInfoMapper);
        val result = userDao.getUserInfoByUserId(userId);
        // Assert
        assertEquals(userInfo, result);
    }

    @Test
    @Disabled
    void testGetUserInfoByUserIdWithException() {
        // Arrange
        Long userId = 100L;
        val userInfo = UserInfo.builder()
                               .id(userId)
                               .username("xxxx")
                               .build();
        val userInfoMapper = new UserInfoMapper() {
            // 定义行为
            @Override
            public UserInfo getUserInfoByUserId(Long id) {
                throw new RuntimeException("Unknown exception");
            }
        };
        // Act
        val userDao = new UserDaoImpl(userInfoMapper);
        val result = userDao.getUserInfoByUserId(userId);
        // Assert
        assertEquals(userInfo, result);
    }


}
