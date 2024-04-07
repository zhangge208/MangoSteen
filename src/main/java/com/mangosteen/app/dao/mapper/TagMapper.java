package com.mangosteen.app.dao.mapper;

import com.mangosteen.app.dao.provider.TagSQLProvider;
import com.mangosteen.app.model.dao.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface TagMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO ms_tag(name, description, icon, status, user_id)"
        + " VALUES (#{name}, #{description}, #{icon}, #{status}, #{userId})")
    int insertTag(Tag tag);

    @Select("SELECT count(1) FROM ms_tag WHERE name = #{name} and user_id = #{userId}")
    int countTagByUserIdAndTagName(@Param("name") String name,
                                   @Param("userId") Long userId);

    @Select("SELECT id, name, description, icon, user_id, status, create_time, update_time FROM ms_tag WHERE id = #{id}")
    @Results(value = {
        @Result(column = "id", property = "id"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "name", property = "name"),
        @Result(column = "description", property = "description"),
        @Result(column = "icon", property = "icon"),
        @Result(column = "status", property = "status"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime")
    })
    Tag getTagByTagId(@Param("id") Long tagId);

    @UpdateProvider(type = TagSQLProvider.class, method = "updateTag")
    int updateTag(Tag tagToUpdate);

    @Delete("DELETE FROM ms_tag WHERE id = #{id}")
    int deleteTagByTagId(@Param("id") Long tagId);
}
