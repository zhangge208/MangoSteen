package com.mangosteen.app.dao;

import com.mangosteen.app.dao.mapper.ItemMapper;
import com.mangosteen.app.model.dao.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {

    public static final int ENABLED = 1;
    private final ItemMapper itemMapper;

    public ItemDaoImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public int createItem(Item item) {
        item.setStatus(ENABLED);
        return itemMapper.insertItem(item);
    }

    @Override
    public Item getItemById(Long id) {
        return itemMapper.getItemById(id);
    }
}