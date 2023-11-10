package com.mangosteen.app.dao;

import com.mangosteen.app.model.dao.UserInfo;

public interface UserDao {
    /**
     * Get user info by user id.
     * @param id user id
     * @return the specific user information
     */
    UserInfo getUserInfoByUserId(Long id);
}
