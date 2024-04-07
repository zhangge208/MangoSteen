package com.mangosteen.app.converter.btv;

import com.google.common.base.Converter;
import com.mangosteen.app.model.bo.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoBTVConverter extends Converter<UserInfo, com.mangosteen.app.model.vo.UserInfo> {
    @Override
    protected com.mangosteen.app.model.vo.UserInfo doForward(UserInfo userInfo) {
        return com.mangosteen.app.model.vo.UserInfo.builder()
                                                   .id(userInfo.getId())
                                                   .username(userInfo.getUsername())
                                                   .email(userInfo.getEmail())
                                                   .build();
    }

    @Override
    protected UserInfo doBackward(com.mangosteen.app.model.vo.UserInfo userInfo) {
        return UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .email(userInfo.getEmail())
                       .build();
    }
}
