package com.mangosteen.app.manager;

import com.github.pagehelper.PageInfo;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemQueryParam;
import com.mangosteen.app.model.vo.ItemVO;

public interface ItemManager {

    void validateItem(ItemVO item, Long userId);

    void validateItemToUpdate(ItemVO itemToUpdate);

    ItemVO createNewItem(ItemVO item, Long userId);

    ItemVO getItemByItemId(Long itemId);

    ItemVO updateItem(ItemVO itemToUpdate);

    PageInfo<Item> queryItems(ItemQueryParam queryParam);


}
