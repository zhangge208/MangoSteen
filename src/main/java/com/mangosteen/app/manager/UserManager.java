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

    /**
     * Login with specific login request
     * @param request specific login request
     * @return JWT token
     */
    String login(com.mangosteen.app.model.vo.UserInfo userInfo);

    /**
     * Check whether username is existed.
     * @param username username
     * @return whether exist
     */
    boolean checkUserExisted(String username);

    /**
     * Register with new user
     * @param request new user info
     * @return the registed user info.
     */
    UserInfo register(com.mangosteen.app.model.vo.UserInfo userInfo);

}
