package com.mangosteen.app.manager;

import java.util.List;

import com.mangosteen.app.model.dao.Tag;

/**
 * Tag manager(Business Logic Layer)
 */
public interface TagManager {
    /**
     * Create new tag for the specific user.
     * @param tag tag info
     * @param userId user id
     * @return created tag info
     */
    Tag createTag(Tag tag, Long userId);

    /**
     * Update tag for the specific user.
     * @param tagToUpdate tag for partial updating
     */
    Tag updateTag(Tag tagToUpdate);

    /**
     * Check the tag to create whether exist for the specific user
     * @param name tag name
     * @param userId user id
     * @return existed or not
     */
    boolean checkTagExisted(String name, Long userId);

    /**
     * Get tag by tag id.
     * @param tagId the related tag id.
     * @return the specific tag info.
     */
    Tag getTagByTagId(Long tagId);

    /**
     * Delete the specific tag.
     * @param tagId the related tag id.
     */
    void deleteTagByTagId(Long tagId);

    List<Tag> getTagListByIds(List<Long> tagIds);
}
