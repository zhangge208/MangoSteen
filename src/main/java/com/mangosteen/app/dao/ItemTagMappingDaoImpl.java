package com.mangosteen.app.dao;

import com.mangosteen.app.dao.mapper.ItemTagMappingMapper;
import com.mangosteen.app.model.dao.ItemTagMapping;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemTagMappingDaoImpl implements ItemTagMappingDao {
    private final ItemTagMappingMapper itemTagMappingMapper;
    public static final int ENABLED = 1;

    public ItemTagMappingDaoImpl(ItemTagMappingMapper itemTagMappingMapper) {
        this.itemTagMappingMapper = itemTagMappingMapper;
    }

    @Override
    public int batchInsertItemTagMapping(List<Long> tagIds, Long itemId) {
        val itemTagMappingList = tagIds.stream()
                                       .map(tagId -> ItemTagMapping.builder()
                                                                   .tagId(tagId)
                                                                   .itemId(itemId)
                                                                   .status(ENABLED)
                                                                   .build())
                                       .toList();

        return itemTagMappingMapper.batchInsertItemTagMapping(itemTagMappingList);
    }

    @Override
    public void deleteItemTagMappingByItemId(Long itemId) {
        itemTagMappingMapper.deleteItemTagMappingByItemId(itemId);
    }
}
