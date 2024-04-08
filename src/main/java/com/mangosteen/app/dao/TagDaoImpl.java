package com.mangosteen.app.dao;

import java.util.List;

import com.mangosteen.app.dao.mapper.TagMapper;
import com.mangosteen.app.model.dao.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl implements TagDao{
    private final TagMapper tagMapper;

    public TagDaoImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public int createTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public int countTagByUserIdAndTagName(String tagName, Long userId) {
        return tagMapper.countTagByUserIdAndTagName(tagName, userId);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTagByTagId(id);
    }

    @Override
    public int updateTag(Tag tagToUpdate) {
        return tagMapper.updateTag(tagToUpdate);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTagByTagId(id);
    }

    @Override
    public List<Tag> getTagListByIds(List<Long> ids) {
        return null;
    }
}
