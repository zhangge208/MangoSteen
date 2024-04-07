package com.mangosteen.app.dao.mapper;

import com.mangosteen.app.model.dao.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TagMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO ms_tag(name, description, icon, status, user_id)"
        + " VALUES (#{name}, #{description}, #{icon}, #{status}, #{userId})")
    int insertTag(Tag tag);

    @Select("SELECT count(1) FROM ms_tag WHERE name = #{name} and user_id = #{userId}")
    int countTagByUserIdAndTagName(@Param("name") String name,
                                   @Param("userId") Long userId);
}
