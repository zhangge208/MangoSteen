package com.mangosteen.app.dao;

import com.mangosteen.app.model.dao.Tag;

public interface TagDao {
    int createTag(Tag tag);

    int countTagByUserIdAndTagName(String tagName, Long userId);

    Tag getTag(Long id);

    int updateTag(Tag tag);
}
