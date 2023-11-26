package com.mangosteen.app.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.mangosteen.app.converter.dtb.UserInfoDTBConverter;
import com.mangosteen.app.dao.UserDao;
import com.mangosteen.app.model.dao.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserInfoManagerTest {
    @Mock
    private UserDao userDao;

    private UserManager userManager;

    @BeforeEach
    void setup() {
        // MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
        val converter = new UserInfoDTBConverter();
        userManager = new UserManagerImpl(userDao, converter);
    }

    @Test
    void testGetUserInfoByUserId() {
        // Arrange
        Long userId = 10L;
        val userInfoDO = UserInfo.builder()
                                 .id(userId)
                                 .username("xxxx")
                                 .build();
        doReturn(userInfoDO).when(userDao).getUserInfoByUserId(userId);
        // Act
        val result = userManager.getUserInfoByUserId(userId);

        // Assert
        assertEquals(userId, result.getId());
        assertEquals("xxxx", result.getUsername());
        verify(userDao).getUserInfoByUserId(eq(userId));
    }
}
