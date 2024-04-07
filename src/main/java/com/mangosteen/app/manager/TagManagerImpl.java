package com.mangosteen.app.manager;

import java.sql.SQLSyntaxErrorException;
import java.util.Optional;

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
    public boolean checkTagExisted(String name, Long userId) {
        return tagDao.countTagByUserIdAndTagName(name, userId) != 0;
    }
}
