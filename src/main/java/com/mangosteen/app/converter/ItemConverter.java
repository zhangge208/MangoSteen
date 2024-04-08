package com.mangosteen.app.converter;

import com.google.common.base.Converter;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemType;
import com.mangosteen.app.model.vo.ItemVO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter extends Converter<ItemVO, Item> {
    @Override
    protected Item doForward(ItemVO itemVO) {
        return Item.builder()
                   .id(itemVO.getId())
                   .amount(itemVO.getAmount())
                   .type(itemVO.getItemType()
                               .getType())
                   .userId(itemVO.getUserId())
                   .description(itemVO.getDescription())
                   .happenAt(itemVO.getHappenAt())
                   .tagIds(itemVO.getTagIds())
                   .build();
    }

    @Override
    protected ItemVO doBackward(Item item) {
        return ItemVO.builder()
                     .id(item.getId())
                     .amount(item.getAmount())
                     .itemType(item.getType() == 0
                                   ? ItemType.OUTCOME
                                   : ItemType.INCOME)
                     .userId(item.getUserId())
                     .description(item.getDescription())
                     .happenAt(item.getHappenAt())
                     .tagIds(item.getTagIds())
                     .createTime(item.getCreateTime())
                     .updateTime(item.getUpdateTime())
                     .build();
    }
}
