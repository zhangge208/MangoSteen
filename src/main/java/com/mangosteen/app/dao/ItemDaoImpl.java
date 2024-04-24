package com.mangosteen.app.dao;

import java.util.List;

import com.mangosteen.app.dao.mapper.ItemMapper;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemQueryParam;
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

    @Override
    public int updateItem(Item itemToUpdate) {
        return itemMapper.updateItem(itemToUpdate);
    }

    @Override
    public List<Item> queryItems(ItemQueryParam param) {
        return itemMapper.selectItemsByFilters(param);
    }


}
