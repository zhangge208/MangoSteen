package com.mangosteen.app.converter.dtb;

import com.google.common.base.Converter;
import com.mangosteen.app.model.dao.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDTBConverter extends Converter<UserInfo, com.mangosteen.app.model.bo.UserInfo> {
    @Override
    protected com.mangosteen.app.model.bo.UserInfo doForward(UserInfo userInfo) {
        return com.mangosteen.app.model.bo.UserInfo.builder()
                                                   .id(userInfo.getId())
                                                   .username(userInfo.getUsername())
                                                   .password(userInfo.getPassword())
                                                   .email(userInfo.getEmail())
                                                   .build();
    }

    @Override
    protected UserInfo doBackward(com.mangosteen.app.model.bo.UserInfo userInfo) {
        return UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .password(userInfo.getPassword())
                       .email(userInfo.getEmail())
                       .build();
    }
}
