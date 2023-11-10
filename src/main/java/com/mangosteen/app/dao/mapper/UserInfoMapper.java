package com.mangosteen.app.dao.mapper;

import com.mangosteen.app.model.dao.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ORM: data model UserInfo -> Table ms_userinfo
 */
@Mapper
public interface UserInfoMapper {

    // SELECT * from mangosteen_test.ms_userinfo WHERE id = {id}
    @Select("SELECT id, username, password, create_time, update_time FROM ms_userinfo WHERE id = #{id}")
    UserInfo getUserInfoByUserId(Long id);
}
