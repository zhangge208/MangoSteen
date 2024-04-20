package com.mangosteen.app.converter;

import com.google.common.base.Converter;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemType;
import com.mangosteen.app.model.vo.ItemVO;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter extends Converter<ItemVO, Item> {
    @Override
    protected Item doForward(ItemVO itemVO) {
        val item = Item.builder()
                       .id(itemVO.getId())
                       .amount(itemVO.getAmount())
                       .userId(itemVO.getUserId())
                       .description(itemVO.getDescription())
                       .happenAt(itemVO.getHappenAt())
                       .tagIds(itemVO.getTagIds())
                       .build();

        if (itemVO.getItemType() != null) {
            item.setType(itemVO.getItemType()
                               .getType());
        }

        if (itemVO.getStatus() != null) {
            item.setStatus("ENABLE".equals(itemVO.getStatus()) ? 1 : 0);
        }

        return item;
    }

    @Override
    protected ItemVO doBackward(Item item) {
        return ItemVO.builder()
                     .id(item.getId())
                     .amount(item.getAmount())
                     .itemType(item.getType() == 0
                                   ? ItemType.OUTCOME
                                   : ItemType.INCOME)
                     .status(item.getStatus() == 1 ? "ENABLE" : "DISABLE")
                     .userId(item.getUserId())
                     .description(item.getDescription())
                     .happenAt(item.getHappenAt())
                     .tagIds(item.getTagIds())
                     .createTime(item.getCreateTime())
                     .updateTime(item.getUpdateTime())
                     .build();
    }
}
