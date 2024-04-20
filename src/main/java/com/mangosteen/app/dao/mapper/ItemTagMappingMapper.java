package com.mangosteen.app.dao.mapper;

import java.util.List;

import com.mangosteen.app.model.dao.ItemTagMapping;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemTagMappingMapper {
    @Insert({
        "<script>",
        "INSERT INTO ms_item_tag_mapping(item_id, tag_id, status) VALUES ",
        "<foreach item = 'item' index = 'index' collection= 'itemTagMappings' separator = ','>",
        "(#{item.itemId}, #{item.tagId}, #{item.status})",
        "</foreach>",
        "</script>"
    })
    int batchInsertItemTagMapping(@Param("itemTagMappings") List<ItemTagMapping> itemTagMappingList);

    @Select("SELECT tag_id FROM ms_item_tag_mapping WHERE item_id = #{itemId}")
    List<Long> getTagIdsByItemId(@Param("itemId") Long itemId);

    @Delete("DELETE FROM ms_item_tag_mapping where item_id = #{itemId}")
    int deleteItemTagMappingByItemId(@Param("itemId") Long itemId);
}
