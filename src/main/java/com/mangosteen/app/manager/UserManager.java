package com.mangosteen.app.manager;

import com.mangosteen.app.model.bo.UserInfo;

/**
 * User manager(Business Logic Layer)
 */
public interface UserManager {
    /**
     * Get user info by user id.
     * @param userId user id.
     * @return the specific user info
     */
    UserInfo getUserInfoByUserId(Long userId);

}
