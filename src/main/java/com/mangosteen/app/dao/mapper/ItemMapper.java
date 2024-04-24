package com.mangosteen.app.dao.mapper;

import java.util.List;

import com.mangosteen.app.dao.provider.ItemSQLProvider;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface ItemMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO ms_item(user_id, amount, description, type, status, happen_at) "
        + "VALUES (#{userId}, #{amount}, #{description}, #{type}, #{status}, #{happenAt})")
    int insertItem(Item item);

    @Select("SELECT id, user_id, amount, description, type, status, happen_at FROM ms_item WHERE id = #{id}")
    @Results(
        {
            @Result(column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "happen_at", property = "happenAt"),
            @Result(column = "type", property = "type"),
            @Result(column = "description", property = "description"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", javaType = List.class, property = "tagIds",
                many = @Many(select = "com.mangosteen.app.dao.mapper.ItemTagMappingMapper.getTagIdsByItemId")
            )
        }
    )
    Item getItemById(@Param("id") Long id);

    @UpdateProvider(type = ItemSQLProvider.class, method = "updateItem")
    int updateItem(Item itemToUpdate);

    @SelectProvider(type = ItemSQLProvider.class, method = "selectItemsByFilters")
    @Results(
        {
            @Result(column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "happen_at", property = "happenAt"),
            @Result(column = "type", property = "type"),
            @Result(column = "description", property = "description"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", javaType = List.class, property = "tagIds",
                many = @Many(select = "com.mangosteen.app.dao.mapper.ItemTagMappingMapper.getTagIdsByItemId")
            )
        }
    )
    List<Item> selectItemsByFilters(ItemQueryParam param);
}
