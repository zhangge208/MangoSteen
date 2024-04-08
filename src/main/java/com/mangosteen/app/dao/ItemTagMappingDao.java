package com.mangosteen.app.dao;

import java.util.List;

public interface ItemTagMappingDao {
    int batchInsertItemTagMapping(List<Long> tagIds, Long itemId);
}
