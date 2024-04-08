package com.mangosteen.app.dao.mapper;

import com.mangosteen.app.model.dao.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO ms_item(user_id, amount, description, type, status, happen_at) "
        + "VALUES (#{userId}, #{amount}, #{description}, #{type}, #{status}, #{happenAt})")
    int insertItem(Item item);

    Item getItemById(@Param("id") Long id);
}
