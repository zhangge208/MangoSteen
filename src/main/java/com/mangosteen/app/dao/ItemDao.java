package com.mangosteen.app.dao;

import java.util.List;

import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemQueryParam;

public interface ItemDao {

    int createItem(Item item);

    Item getItemById(Long id);

    int updateItem(Item itemToUpdate);

    List<Item> queryItems(ItemQueryParam param);
}
