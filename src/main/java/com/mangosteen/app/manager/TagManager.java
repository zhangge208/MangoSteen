package com.mangosteen.app.manager;

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
    public Tag createTag(Tag tag, Long userId);

    /**
     * Update tag for the specific user.
     * @param tagToUpdate tag for partial updating
     */
    public Tag updateTag(Tag tagToUpdate);

    /**
     * Check the tag to create whether exist for the specific user
     * @param name tag name
     * @param userId user id
     * @return existed or not
     */
    public boolean checkTagExisted(String name, Long userId);

    public Tag getTagByTagId(Long tagId);
}
