package com.mangosteen.app.dao.mapper;

import java.util.List;

import com.mangosteen.app.model.dao.ItemTagMapping;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemTagMappingMapper {
    @Insert({
        "<script>",
        "INSERT INTO ms_item_tag_mapping(item_id, tag_id, status) VALUES ",
        "<foreach item = 'item' index = 'index' collection= 'itemTagMappings'",
        "open ='(' separator = '),(', close=')'>",
        "#{item.itemId}, #{item.tagId}, #{item.status}",
        "</foreach>",
    })
    int batchItemTagMapping(@Param("itemTagMappings") List<ItemTagMapping> itemTagMappingList);
}
