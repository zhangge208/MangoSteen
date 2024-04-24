package com.mangosteen.app.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mangosteen.app.converter.ItemConverter;
import com.mangosteen.app.dao.ItemDao;
import com.mangosteen.app.dao.ItemTagMappingDao;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.exception.ResourceNotFoundException;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.dao.Tag;
import com.mangosteen.app.model.vo.ItemQueryParam;
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

        checkTagRelatedRules(item, userId);
    }

    private void checkTagRelatedRules(ItemVO item, Long userId) {
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
    public void validateItemToUpdate(ItemVO itemToUpdate) {
        // item id related item existed
        val itemResult = getItemByItemId(itemToUpdate.getId());

        if (!Objects.equals(itemToUpdate.getUserId(), itemResult.getUserId())) {
            throw new InvalidParameterException("The user id and item id are mismatched.");
        }

        if (itemToUpdate.getAmount() != null && itemToUpdate.getAmount().compareTo(new BigDecimal(0)) <= 0) {

            throw new InvalidParameterException("The amount for updating must be positive.");
        }

        if (itemToUpdate.getTagIds() != null) {
            checkTagRelatedRules(itemToUpdate, itemToUpdate.getUserId());
        }
    }

    @Override
    public ItemVO createNewItem(ItemVO itemVO, Long userId) {
        itemVO.setUserId(userId);
        val item = itemConverter.convert(itemVO);
        itemDao.createItem(item);

        // how to fetch new item id?
        itemTagMappingDao.batchInsertItemTagMapping(item.getTagIds(), item.getId());

        return getItemByItemId(item.getId());
    }

    @Override
    public ItemVO getItemByItemId(Long itemId) {
        val item = Optional.ofNullable(itemDao.getItemById(itemId))
                           .orElseThrow(() -> new ResourceNotFoundException(
                               String.format("There is no related item found, item id: %d", itemId)));
        // converter

        System.out.println(item);
        return itemConverter.reverse().convert(item);
    }

    @Override
    public ItemVO updateItem(ItemVO itemToUpdate) {
        if (itemToUpdate.getTagIds() != null) {
            val existingItem = getItemByItemId(itemToUpdate.getId());
            if (!itemToUpdate.getTagIds().equals(existingItem.getTagIds())) {
                // delete all of tag/item mapping
                itemTagMappingDao.deleteItemTagMappingByItemId(itemToUpdate.getId());
                // create new mapping for updating tag and item
                itemTagMappingDao.batchInsertItemTagMapping(itemToUpdate.getTagIds(),
                                                            itemToUpdate.getId());
            }
        }

        if (itemToUpdate.getItemType() != null
            || itemToUpdate.getAmount() != null
            || itemToUpdate.getDescription() != null
            || itemToUpdate.getHappenAt() != null
            || itemToUpdate.getStatus() != null) {
            itemDao.updateItem(itemConverter.convert(itemToUpdate));
        }


        return getItemByItemId(itemToUpdate.getId());
    }

    @Override
    public PageInfo<Item> queryItems(ItemQueryParam queryParam) {
        PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
        List<Item> item = itemDao.queryItems(queryParam);
        return new PageInfo<>(item);
    }
}
