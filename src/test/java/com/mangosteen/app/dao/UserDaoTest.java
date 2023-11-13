package com.mangosteen.app.dao;

import com.mangosteen.app.dao.mapper.UserInfoMapper;
import com.mangosteen.app.model.dao.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserDaoTest {

    @Mock
    UserInfoMapper userInfoMapper;

    @InjectMocks
    private UserDaoImpl userDao; // SUT


    @Test
    void testGetUserInfoByUserId() {
        // Arrange
        Long userId = 100L;
        val userInfo = UserInfo.builder()
                               .id(userId)
                               .username("xxxx")
                               .build();
        when(userInfoMapper.getUserInfoByUserId(userId)).thenReturn(userInfo);
        // Act
        val result = userDao.getUserInfoByUserId(userId);

        // Assert
        assertEquals(userInfo, result);
        verify(userInfoMapper).getUserInfoByUserId(eq(userId));
    }
}
