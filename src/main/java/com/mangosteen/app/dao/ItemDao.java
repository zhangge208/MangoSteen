package com.mangosteen.app.dao;

import com.mangosteen.app.model.dao.Item;

public interface ItemDao {

    int createItem(Item item);

    Item getItemById(Long id);
}
