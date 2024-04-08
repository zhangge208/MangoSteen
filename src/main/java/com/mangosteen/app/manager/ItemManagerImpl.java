package com.mangosteen.app.manager;

import java.math.BigDecimal;

import com.mangosteen.app.converter.ItemConverter;
import com.mangosteen.app.dao.ItemDao;
import com.mangosteen.app.dao.ItemTagMappingDao;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.model.dao.Tag;
import com.mangosteen.app.model.vo.ItemVO;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemManagerImpl implements ItemManager {

    private final TagManager tagManager;
    private final ItemDao itemDao;
    private final ItemTagMappingDao itemTagMappingDao;

    private final ItemConverter itemConverter;

    @Autowired
    public ItemManagerImpl(TagManager tagManager, ItemDao itemDao, ItemTagMappingDao itemTagMappingDao,
                           ItemConverter itemConverter) {
        this.tagManager = tagManager;
        this.itemDao = itemDao;
        this.itemTagMappingDao = itemTagMappingDao;
        this.itemConverter = itemConverter;
    }

    @Override
    public void validateItem(ItemVO item, Long userId) {
        // 1. input basic validation
        if (item.getTagIds() == null) {
            throw new InvalidParameterException("The tag list is required.");
        }

        if (item.getAmount() == null) {
            throw new InvalidParameterException("The amount is required.");
        }

        if (item.getItemType() == null) {
            throw new InvalidParameterException("The type is required.");
        }

        if (item.getHappenAt() == null) {
            throw new InvalidParameterException("The happened time is required.");
        }

        if (item.getAmount().compareTo(new BigDecimal(0)) < 0) {
            throw new InvalidParameterException("The amount must be positive.");
        }

        // 2. tag list existing validation -> batch query
        val tagList = tagManager.getTagListByIds(item.getTagIds());

        if (tagList.isEmpty() || tagList.size() != item.getTagIds().size()) {
            throw new InvalidParameterException("The tag id list is invalid. Tag ids: " + item.getTagIds());
        }

        // 3. tag list and user id match validation
        boolean isMatched = tagList.stream()
                                   .map(Tag::getUserId)
                                   .allMatch(uid -> uid.equals(userId));

        if (!isMatched) {
            throw new InvalidParameterException("The tag list is not matched for user.");
        }


    }

    @Override
    public ItemVO createNewItem(ItemVO item, Long userId) {
        item.setUserId(userId);
        itemDao.createItem(itemConverter.convert(item));

        // how to fetch new item id?
        itemTagMappingDao.batchInsertItemTagMapping(item.getTagIds(), item.getId());

        return getItemByItemId(item.getId());
    }

    @Override
    public ItemVO getItemByItemId(Long itemId) {
        val item = itemDao.getItemById(itemId);
        // converter
        return itemConverter.reverse().convert(item);
    }
}
