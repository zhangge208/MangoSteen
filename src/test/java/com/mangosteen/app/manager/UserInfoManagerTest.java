package com.mangosteen.app.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.mangosteen.app.converter.dtb.UserInfoDTBConverter;
import com.mangosteen.app.dao.UserDao;
import com.mangosteen.app.model.dao.UserInfo;
import com.mangosteen.app.service.CustomerUserDetailService;
import com.mangosteen.app.utils.JWTUtil;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserInfoManagerTest {
    @Mock
    private UserDao userDao;

    private UserManager userManager;

    @BeforeEach
    void setup() {
        // MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
        val converter = new UserInfoDTBConverter();
        val userDetailsService = new CustomerUserDetailService(userDao);
        val authenticationManager = new ProviderManager();
        userManager = new UserManagerImpl(userDao, converter, userDetailsService, authenticationManager, new BCryptPasswordEncoder(),
                                          new JWTUtil());
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
