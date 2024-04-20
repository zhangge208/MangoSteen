package com.mangosteen.app.manager;

import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemVO;

public interface ItemManager {

    void validateItem(ItemVO item, Long userId);

    void validateItemToUpdate(ItemVO itemToUpdate);

    ItemVO createNewItem(ItemVO item, Long userId);

    ItemVO getItemByItemId(Long itemId);

    ItemVO updateItem(ItemVO itemToUpdate);


}
