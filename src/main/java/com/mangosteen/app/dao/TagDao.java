package com.mangosteen.app.dao;

import java.util.List;

import com.mangosteen.app.model.dao.Tag;

public interface TagDao {
    int createTag(Tag tag);

    int countTagByUserIdAndTagName(String tagName, Long userId);

    Tag getTag(Long id);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> getTagListByIds(List<Long> ids);
}
