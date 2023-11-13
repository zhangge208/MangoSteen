package com.mangosteen.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangosteen.app.converter.btv.UserInfoBTVConverter;
import com.mangosteen.app.exception.GlobalExceptionHandler;
import com.mangosteen.app.manager.UserManager;
import com.mangosteen.app.model.bo.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    UserManager userManager;

    @Mock
    UserInfoBTVConverter converter;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                                 .setControllerAdvice(new GlobalExceptionHandler())
                                 .build();
    }

    @AfterEach
    void tearDown() {
        reset(userManager);
        reset(converter);
    }

    @Test
    void testGetUserInfoById() throws Exception {
        // Arrange
        Long id = 20L;
        val userInfoBO = UserInfo.builder()
                                 .id(id)
                                 .username("zhangsan")
                                 .build();

        val userInfoVO = com.mangosteen.app.model.vo.UserInfo.builder()
                                                             .id(id)
                                                             .username("zhangsan")
                                                             .build();
        when(userManager.getUserInfoByUserId(id)).thenReturn(userInfoBO);
        when(converter.convert(userInfoBO)).thenReturn(userInfoVO);

        // Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/" + id)
                                              .contentType("application/json")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content()
                                               .contentType("application/json"))
               .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper()
                                                                         .writeValueAsString(userInfoVO)));
        verify(userManager).getUserInfoByUserId(eq(id));
        verify(converter).convert(eq(userInfoBO));
    }

    @Test
    void testGetUserInfoWithInvalidUserId() throws Exception {
        // Arrange
        Long id = -100L;
        // Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/" + id)
                                              .contentType("application/json")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().is4xxClientError())
               .andExpect(MockMvcResultMatchers.content()
                                               .contentType("application/json"))
               .andExpect(MockMvcResultMatchers.content()
                                               .string("{\"statusCode\":400,\"message\":\"User Id must be greater than 0\",\"errorType\":\"Client\",\"errorCode\":\"INVALID_PARAMETER\"}"));
        verify(userManager, never()).getUserInfoByUserId(eq(id));
        verify(converter, never()).convert(any());
    }

    @Test
    void testGetUserInfoWithNoUserFound() throws Exception {
        // Arrange
        Long id = 100L;
        when(userManager.getUserInfoByUserId(id))
                .thenReturn(null);

        // Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/users/" + id)
                                              .contentType("application/json")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().is4xxClientError())
               .andExpect(MockMvcResultMatchers.content()
                                               .contentType("application/json"))
               .andExpect(MockMvcResultMatchers.content()
                                               .string("{\"statusCode\":404,\"message\":\"There is no user with id: 100\",\"errorType\":\"Client\",\"errorCode\":\"NOT_FOUND\"}"));
        verify(userManager).getUserInfoByUserId(eq(id));
        verify(converter, never()).convert(any());

    }
}
