package com.mangosteen.app.manager;

import java.util.List;

import com.mangosteen.app.dao.TagDao;
import com.mangosteen.app.exception.InternalServiceFailureException;
import com.mangosteen.app.model.dao.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagManagerImpl implements TagManager {

    private final TagDao tagDao;

    public TagManagerImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag createTag(Tag tag, Long userId) {
        try {
            tag.setUserId(userId);
            tag.setStatus(1);
            tagDao.createTag(tag);
            return tag;
        } catch (Exception exception) {
            throw new InternalServiceFailureException("There is unexpected internal service error for creating tag: "
                                                          + exception.getMessage());
        }

    }

    @Override
    public Tag updateTag(Tag tagToUpdate) {
        // 1. (大多数适用与NoSQL)从数据库中拿到资源，然后跟要更新的字段合并，然后整体更新
        // 2. (MySQL) 利用SQL部分更新 UPDATE ... SET ... WHERE

        try {
            tagDao.updateTag(tagToUpdate); // read after write
            return tagDao.getTag(tagToUpdate.getId());
        } catch (Exception ex) {
            throw new InternalServiceFailureException("There is unexpected internal service error for updating tag: "
                                                          + ex.getMessage());
        }

        //return tagDao.getTag(tagToUpdate.getId());
    }

    @Override
    public boolean checkTagExisted(String name, Long userId) {
        return tagDao.countTagByUserIdAndTagName(name, userId) != 0;
    }

    @Override
    public Tag getTagByTagId(Long tagId) {
        return tagDao.getTag(tagId);
    }

    @Override
    public void deleteTagByTagId(Long tagId) {
        tagDao.deleteTag(tagId);
    }

    @Override
    public List<Tag> getTagListByIds(List<Long> tagIds) {
        return tagDao.getTagListByIds(tagIds);
    }
}
