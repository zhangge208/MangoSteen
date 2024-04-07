package com.mangosteen.app.dao.mapper;

import com.mangosteen.app.model.dao.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * ORM: data model UserInfo -> Table ms_userinfo.
 */
@Mapper
public interface UserInfoMapper {

    // SELECT * from mangosteen_test.ms_userinfo WHERE id = {id}
    @Select("SELECT id, username, password, email, create_time, update_time FROM ms_userinfo WHERE id = #{id}")
    UserInfo getUserInfoByUserId(Long id);

    @Select("SELECT id, username, password, email, create_time, update_time FROM ms_userinfo WHERE username = #{username}")
    UserInfo getUserInfoByUsername(String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT into ms_userinfo(username, password, email, create_time, update_time)"
        + " VALUES (#{username}, #{password}, #{email}, #{createTime}, #{updateTime})")
    int createNewUser(com.mangosteen.app.model.bo.UserInfo userInfo);
}
